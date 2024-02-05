package com.bcdq.pencilme.common;

import lombok.Getter;

@Getter
public enum ResponseType {

    // Member
    회원가입(201,"MEMBER_REGISTERED", "회원 가입 완료"),
    회원로그인(200, "MEMBER_LOGIN", "로그인 완료"),
    회원조회(200, "MEMBER_FOUND","회원 조회 완료"),
    회원수정(200, "MEMBER_UPDATED", "회원 정보 수정 완료"),
    회원탈퇴(200, "MEMBER_DELETED", "회원 탈퇴 완료"),

    // Category
    카테고리생성(201, "CATEGORY_CREATED", "카테고리 생성 완료"),
    카테고리조회(200, "CATEGORY_FOUND", "카테고리 조회 완료"),
    카테고리수정(200, "CATEGORY_UPDATED", "카테고리 수정 완료"),
    카테고리삭제(200, "CATEGORY_DELETED", "카테고리 삭제 완료"),

    // TodoList
    할일생성(201, "TODOLIST_CREATED", "할 일 생성 완료"),
    할일조회(200, "TODOLIST_FOUND", "할 일 조회 완료"),
    할일수정(200, "TODOLIST_UPDATED", "할 일 수정 완료"),
    할일삭제(200, "TODOLIST_DELETED", "할 일 삭제 완료");

    private final int status;
    private final String responseCode;
    private final String responseMessage;

    private ResponseType(int status, String responseCode, String responseMessage) {
        this.status = status;
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }
}
