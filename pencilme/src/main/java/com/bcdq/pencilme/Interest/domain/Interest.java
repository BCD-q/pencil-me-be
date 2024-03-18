package com.bcdq.pencilme.Interest.domain;


import com.bcdq.pencilme.Interest.domain.dto.request.InterestReqDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "interest")
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "keyword")
    private String keyword;

    public Interest(String keyword) {
        this.keyword = keyword;
    }

    public static List<Interest> createInterestDTOtoInterestList(InterestReqDto.CreateInterests interestDTO) {
        return interestDTO.getInterest().stream()
                .map(Interest::new)
                .collect(Collectors.toList());
    }
}
