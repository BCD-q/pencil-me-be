package com.bcdq.pencilme.todo.domain;

import com.bcdq.pencilme.common.TimeStamped;
import com.bcdq.pencilme.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "todo_tb")
public class Todo extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Todo(Long id, String contents, Member member) {
        this.id = id;
        this.contents = contents;
        this.member = member;
    }
}
