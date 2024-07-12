package com.bcdq.pencilme.analysis.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AnalysisResponse {
    private String name;
    private double percentage;

    @Builder(access = AccessLevel.PRIVATE)
    private AnalysisResponse(String name, double percentage) {
        this.name = name;
        this.percentage = percentage;
    }

    public static AnalysisResponse of(String name, double percentage) {
        return AnalysisResponse.builder()
                .name(name)
                .percentage(percentage)
                .build();
    }
}
