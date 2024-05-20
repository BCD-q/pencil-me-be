package com.bcdq.pencilme.analysis.service;

import com.bcdq.pencilme.analysis.dto.response.AnalysisResponse;
import com.bcdq.pencilme.category.domain.Category;
import com.bcdq.pencilme.category.repository.CategoryRepository;
import com.bcdq.pencilme.member.domain.Member;
import com.bcdq.pencilme.todo.domain.Todo;
import com.bcdq.pencilme.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnalysisService {

    private final TodoRepository todoRepository;
    private final CategoryRepository categoryRepository;

    public List<AnalysisResponse> readAnalysis(Member member) {
        List<AnalysisResponse> analysisResponses = new ArrayList<>();

        log.info("전체 목표 점검 = {}", calculateGoal(todoRepository.findAllByMember(member)));
        analysisResponses.add(AnalysisResponse.of("오늘", calculateGoal(todoRepository.findAllByMember(member))));

        log.info("주요 목표 점검 = {}", calculateGoal(todoRepository.findAllByMemberAndIsImportant(member, true)));
        analysisResponses.add(AnalysisResponse.of("주요 목표", calculateGoal(todoRepository.findAllByMember(member))));

        log.info("===============");
        List<Category> categories = categoryRepository.findAllByMember(member);
        for (Category category : categories) {
            analysisResponses.add(AnalysisResponse.of(category.getName(), calculateGoal(todoRepository.findAllByCategoryAndMember(category, member))));
            log.info("그룹 {} 목표 점검 = {}", category.getName(), calculateGoal(todoRepository.findAllByCategoryAndMember(category, member)));
        }
        return analysisResponses;
    }

    private double calculateGoal(List<Todo> todos) {
        log.info("완료된 할 일의 수 = {}", todos.stream().filter(Todo::getIsFinished).count());
        log.info("전체 할 일의 수 = {}", todos.size());
        return (double) todos.stream().filter(Todo::getIsFinished).count() / (double) todos.size() * 100;
    }

}
