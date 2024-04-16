package com.bcdq.pencilme.interest_mapping.domain;

import com.bcdq.pencilme.interest.domain.Interest;
import com.bcdq.pencilme.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 관심사 매핑 Entity
 *
 * @author Wonjeong Kim
 */
@Entity
@Getter
@Table(name = "interest_map")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InterestMapping {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "member_id")
        private Member member;

        @ManyToOne
        @JoinColumn(name = "interest_id")
        private Interest interest;

        @Builder
        private InterestMapping(Member member, Interest interest) {
            this.member = member;
            this.interest = interest;
        }

        public void updateMember(Member member) {
            this.member = member;
        }
}
