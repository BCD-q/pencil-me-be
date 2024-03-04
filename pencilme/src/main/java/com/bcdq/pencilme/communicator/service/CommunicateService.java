package com.bcdq.pencilme.communicator.service;

import com.bcdq.pencilme.communicator.dto.request.CommunicateTodoRequest;
import com.bcdq.pencilme.communicator.dto.request.TodoRequest;
import com.bcdq.pencilme.communicator.dto.response.CommunicateTodoResponse;
import com.bcdq.pencilme.member.domain.Member;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;

/**
 * 서버 간 통신 관련 Service
 * 할 일 생성 처리
 *
 * @author Juwon Lee
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CommunicateService {
    private final WebClient.Builder webClientBuilder;
    private final ObjectMapper objectMapper;

    @Value("${aiServer.url}")
    private String baseurl;

    @Value("${aiServer.endpoint}")
    private String endpoint;

    /**
     * AI 서버를 통한 할 일 생성 요청 메서드
     *
     * @param todoRequest AI 서버를 통한 할 일 생성 요청을 위한 DTO
     * @param currentMember 현재 로그인한 사용자
     * @return CommunicateTodoResponse 할 일 생성 요청으로 생성된
     */
    public CommunicateTodoResponse createTodo(TodoRequest todoRequest, Member currentMember) throws JsonProcessingException {
        CommunicateTodoRequest communicateTodoRequest = CommunicateTodoRequest.of(todoRequest, currentMember);
        log.info("memberId = {}", communicateTodoRequest.getMemberId());
        log.info("memberName = {}", communicateTodoRequest.getMemberName());
        log.info("memberEmail = {}", communicateTodoRequest.getMemberEmail());
        log.info("memberStatement = {}", communicateTodoRequest.getMemberStatement());
        log.info("requestedDate = {}", communicateTodoRequest.getRequestedDate());
        String res = postAPI(baseurl, endpoint, communicateTodoRequest);
        return parseJson(res);
    }

    /**
     * Json 응답을 파싱하는 메서드
     * (AI 서버를 통한 할 일 생성 요청 메서드 파싱용)
     *
     * @param response Json 응답
     * @return CommunicateTodoResponse AI 서버 요청을 통해 생성된 할 일 응답 DTO
     */
    private CommunicateTodoResponse parseJson(String response) throws JsonProcessingException {
        JsonNode result = objectMapper.readTree(response).get("result");
        Long memberId = Long.parseLong(parseText(result, "memberId"));
        Long categoryId = Long.parseLong(parseText(result, "categoryId"));
        String title = parseText(result, "title");
        String contents = parseText(result, "contents");
        LocalDateTime deadline = LocalDateTime.parse(parseText(result, "deadline"));

        return CommunicateTodoResponse.builder()
                .memberId(memberId)
                .categoryId(categoryId)
                .title(title)
                .contents(contents)
                .deadline(deadline)
                .build();
    }

    /**
     * POST 요청 전송 메서드
     *
     * @param baseurl 서버의 baseurl
     * @param endpoint 서버의 endpoint
     * @param body 요청에 담을 body
     * @return String 응답으로 받은 Json
     */
    private String postAPI(String baseurl, String endpoint, Object body) {
        log.info("baseurl = {}, endpoint = {}, body = {}", baseurl, endpoint, body);
        WebClient webClient = webClientBuilder
                .baseUrl(baseurl)
                .build();

        return webClient.post()
                .uri(endpoint)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    /**
     * Json 내 텍스트 파싱 메서드
     * 해당하는 key가 존재하면 값 리턴, 그렇지 않다면 공백 문자열 리턴
     *
     * @param source 파싱할 JsonNode
     * @param subject JsonNode의 키 값
     * @return String 파싱된 텍스트
     */
    private String parseText(JsonNode source, String subject) {
        return source.has(subject) ? source.get(subject).asText() : "";
    }
}
