package com.bcdq.pencilme.communicator.service;

import com.bcdq.pencilme.communicator.dto.request.CommunicateTodoRequest;
import com.bcdq.pencilme.communicator.dto.request.TodoRequest;
import com.bcdq.pencilme.communicator.dto.response.CommunicateTodoResponse;
import com.bcdq.pencilme.member.domain.Member;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommunicateTodoService {
    private final WebClient.Builder webClientBuilder;
    private final ObjectMapper objectMapper;

    @Value("${aiServer.url}")
    private String baseurl;

    @Value("{aiServer.endpoint}")
    private String endpoint;

    public CommunicateTodoResponse createTodo(TodoRequest aiRequest, Member currentMember) throws JsonProcessingException {
        CommunicateTodoRequest todoApiRequest = CommunicateTodoRequest.of(aiRequest, currentMember);
        String res = postAPI(baseurl, endpoint, todoApiRequest);
        CommunicateTodoResponse aiResponse = parseJson(res);
        return aiResponse;
    }

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

    private String postAPI(String baseurl, String endpoint, Object body) {
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

    private String parseText(JsonNode source, String subject) {
        return source.has(subject) ? source.get(subject).asText() : "";
    }
}
