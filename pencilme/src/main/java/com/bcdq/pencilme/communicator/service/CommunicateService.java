package com.bcdq.pencilme.communicator.service;

import com.bcdq.pencilme.communicator.dto.request.CommunicateTodoRequest;
import com.bcdq.pencilme.communicator.dto.request.TodoRequest;
import com.bcdq.pencilme.communicator.dto.response.CommunicateTodoResponse;
import com.bcdq.pencilme.member.domain.Member;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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
     * @return CommunicateTodoResponse 할 일 생성 요청으로 생성된 DTO
     */
    public CommunicateTodoResponse createTodo(TodoRequest todoRequest, Member currentMember) throws JsonProcessingException {
        CommunicateTodoRequest communicateTodoRequest = CommunicateTodoRequest.of(todoRequest, currentMember);

        String response = postAPI(baseurl, endpoint, communicateTodoRequest);
        JsonNode jsonNode = toJsonNode(response);
        return objectMapper.treeToValue(jsonNode, CommunicateTodoResponse.class);
    }

    /**
     * POST 요청 전송 메서드
     *
     * @param baseurl  서버의 baseurl
     * @param endpoint 서버의 endpoint
     * @param body     요청에 담을 body
     * @return String 응답으로 받은 Json 문자열
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
}
