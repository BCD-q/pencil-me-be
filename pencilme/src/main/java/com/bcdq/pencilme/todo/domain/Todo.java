package com.bcdq.pencilme.todo.domain;

import com.bcdq.pencilme.common.BaseTimeEntity;
import com.bcdq.pencilme.category.domain.Category;
import com.bcdq.pencilme.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "todo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    private String contents;

    private LocalDateTime deadline;

    private Boolean isFinished;

    @Builder
    public Todo(Category category, Member member, String title, String contents, LocalDateTime deadline, Boolean isFinished) {
        this.category = category;
        this.member = member;
        this.title = title;
        this.contents = contents;
        this.deadline = deadline;
        this.isFinished = isFinished;
    }

    public void updateTodo(String title, String contents, LocalDateTime deadline, Boolean isFinished) {
        this.title = title;
        this.contents = contents;
        this.deadline = deadline;
        this.isFinished = isFinished;
    }
}
