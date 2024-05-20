package com.bcdq.pencilme.communicator.service;

import com.bcdq.pencilme.communicator.dto.request.CommnuicateMotivationRequest;
import com.bcdq.pencilme.communicator.dto.request.CommunicateSummaryRequest;
import com.bcdq.pencilme.communicator.dto.request.CommunicateTodoRequest;
import com.bcdq.pencilme.communicator.dto.request.TodoRequest;
import com.bcdq.pencilme.communicator.dto.response.CommunicateCommonResponse;
import com.bcdq.pencilme.communicator.dto.response.CommunicateMotivationResponse;
import com.bcdq.pencilme.communicator.dto.response.CommunicateSummaryResponse;
import com.bcdq.pencilme.communicator.dto.response.CommunicateTodoResponse;
import com.bcdq.pencilme.config.security.JwtAuthenticationFilter;
import com.bcdq.pencilme.config.security.TokenProvider;
import com.bcdq.pencilme.member.domain.Member;
import com.bcdq.pencilme.member.repository.MemberRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    private final MemberRepository memberRepository;
    private final WebClient.Builder webClientBuilder;
    private final ObjectMapper objectMapper;
    private final TokenProvider tokenProvider;

    @Value("${aiServer.url}")
    private String baseurl;

    @Value("${aiServer.createTodo}")
    private String createTodo;

    @Value("${aiServer.createMotivation}")
    private String createMotivation;

    @Value("${aiServer.readSummary}")
    private String readSummary;

    @Value("${aiServer.createSummary}")
    private String createSummary;

    /**
     * AI 서버를 통한 할 일 생성 요청 메서드
     *
     * @param todoRequest AI 서버를 통한 할 일 생성 요청을 위한 DTO
     * @param memberId 현재 로그인한 사용자의 식별자
     * @return CommunicateTodoResponse 할 일 생성 요청으로 생성된 DTO
     */
    public CommunicateTodoResponse createTodo(TodoRequest todoRequest, Long memberId, HttpServletRequest request) throws JsonProcessingException {
        Member member = findById(memberId);
        CommunicateTodoRequest communicateTodoRequest = CommunicateTodoRequest.of(todoRequest, member);

        String response = postAPI(baseurl, createTodo, communicateTodoRequest, request);
        JsonNode jsonNode = toJsonNode(response);
        return objectMapper.treeToValue(jsonNode, CommunicateTodoResponse.class);
    }

    /**
     * AI 서버를 통한 포스팅 요약 메서드
     *
     * @param url 요약할 포스팅의 url
     * @return CommunicateSummaryResponse 포스팅 요약 요청으로 생성된 DTO
     */
    public CommunicateSummaryResponse readSummary(String url) throws IOException {
        String response = getAPI(baseurl, readSummary, url);
        JsonNode result = objectMapper.readTree(response).get("result").get("data");
        String title = result.get("title").asText();
        String contents = result.get("contents").asText();
        return CommunicateSummaryResponse.of(title, contents);
    }

    public List<CommunicateMotivationResponse> createMotivation(int start, CommnuicateMotivationRequest commnuicateMotivationRequest) throws JsonProcessingException {
        WebClient webClient = webClientBuilder
                .baseUrl(baseurl)
                .build();

        String response =  webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(createMotivation)
                        .queryParam("start", start).build())
                .bodyValue(commnuicateMotivationRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JsonNode result = objectMapper.readTree(response).get("result").get("data");
        return objectMapper.readValue(result.toString(), new TypeReference<>() {
        });
    }

    /**
     * AI 서버를 통한 포스팅 요약 메서드
     *
     * @param url 요약할 포스팅의 url
     * @return CommunicateSummaryResponse 포스팅 요약 요청으로 생성된 DTO
     */
    public CommunicateSummaryResponse createSummary(String url, Member member, HttpServletRequest request) throws IOException {
        log.info("baseurl = {}, endpoint = {}", baseurl, createSummary);
        CommunicateSummaryRequest communicateSummaryRequest = CommunicateSummaryRequest.of(member);
        WebClient webClient = webClientBuilder
                .baseUrl(baseurl)
                .build();

        String response = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(createSummary)
                        .queryParam("url", url).build())
                .header("token", tokenProvider.resolveToken(request))
                .bodyValue(communicateSummaryRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JsonNode result = objectMapper.readTree(response).get("result").get("data");
        String title = result.get("title").asText();
        String contents = result.get("contents").asText();
        Long categoryId = result.get("categoryId").asLong();
        return CommunicateSummaryResponse.of(title, contents, categoryId);
    }

    /**
     * GET 요청 전송 메서드
     *
     * @param baseurl  서버의 baseurl
     * @param endpoint 서버의 endpoint
     * @param parameter 요청에 담을 parameter
     * @return String 응답으로 받은 Json 문자열
     */
    private String getAPI(String baseurl, String endpoint, String parameter) {
        log.info("baseurl = {}, endpoint = {}, body = {}", baseurl, endpoint, parameter);
        WebClient webClient = webClientBuilder
                .baseUrl(baseurl)
                .build();

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(endpoint)
                        .queryParam("url", parameter).build()
                )
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    /**
     * POST 요청 전송 메서드
     *
     * @param baseurl  서버의 baseurl
     * @param endpoint 서버의 endpoint
     * @param body     요청에 담을 body
     * @return String 응답으로 받은 Json 문자열
     */
    private String postAPI(String baseurl, String endpoint, Object body, HttpServletRequest request) {
        log.info("baseurl = {}, endpoint = {}, body = {}", baseurl, endpoint, body);
        WebClient webClient = webClientBuilder
                .baseUrl(baseurl)
                .build();

        return webClient.post()
                    .uri(endpoint)
                    .header("token", tokenProvider.resolveToken(request))
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
    }

    /**
     * JsonNode 타입 변환 메서드
     * Null 값인 필드를 빈 문자열로 변환하여 저장 후 반환
     *
     * @param response 문자열 타입의 Json 응답
     * @return JsonNode 변환된 Json 응답
     */
    private JsonNode toJsonNode(String response) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(response);
        if (jsonNode.isObject()) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            objectNode.fields().forEachRemaining(entry -> {
                if (entry.getValue().isNull()) {
                    entry.setValue(objectMapper.getNodeFactory().textNode(""));
                }
            });
        }
        return jsonNode;
    }

    /**
     * 회원 DB 조회 메서드
     *
     * @param memberId 조회할 회원의 id 값
     * @return Member 조회된 회원
     */
    private Member findById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(RuntimeException::new);
    }
}
