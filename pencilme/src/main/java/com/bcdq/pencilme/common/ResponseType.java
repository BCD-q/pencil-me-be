package com.bcdq.pencilme.common;

import lombok.Getter;

/**
 * 응답 정의 enum
 * 도메인에 대한 정상, 예외 응답 정의
 *
 * @author Juwon Lee
 */
@Getter
public enum ResponseType {

    // Member
    회원가입(201,"MEMBER_REGISTERED", "회원 가입 완료"),
    회원로그인(200, "MEMBER_LOGIN", "로그인 완료"),
    회원조회(200, "MEMBER_FOUND","회원 조회 완료"),
    회원수정(200, "MEMBER_UPDATED", "회원 정보 수정 완료"),
    회원탈퇴(200, "MEMBER_DELETED", "회원 탈퇴 완료"),

    // Category
    그룹생성(201, "CATEGORY_CREATED", "그룹 생성 완료"),
    그룹조회(200, "CATEGORY_FOUND", "그룹 조회 완료"),
    그룹수정(200, "CATEGORY_UPDATED", "그룹 수정 완료"),
    그룹삭제(200, "CATEGORY_DELETED", "그룹 삭제 완료"),

    // TodoList
    할일생성(201, "TODOLIST_CREATED", "할 일 생성 완료"),
    할일조회(200, "TODOLIST_FOUND", "할 일 조회 완료"),
    할일수정(200, "TODOLIST_UPDATED", "할 일 수정 완료"),
    할일삭제(200, "TODOLIST_DELETED", "할 일 삭제 완료"),

    // Communicator
    할일등록요청완료(201, "TODO_REQUEST_SENT", "일정 등록 요청 완료"),
    요약완료(200, "SUMMARIZE_FINISHED", "요약 완료"),

    // Interest
    관심사전체조회(200, "INTEREST_FIND_ALL", "관심사 전체조회 완료"),
    여러관심사생성(201, "INTEREST_CREATED", "관심사 생성 완료"),
    관심사단건수정(200, "INTEREST_UPDATED", "관심사 수정 완료"),
    여러관심사삭제(200, "INTEREST_DELETED", "관심사 삭제 완료"),

    // Interest Mapping
    여러관심사멤버매핑(200, "RELATE_SUCCESS", "여러 관심사와 멤버간 매핑 성공"),
    사용자의모든관심사찾기(200, "FIND_RELATED_INTEREST", "멤버와 연관되어 있는 관심사를 모두 불러왔습니다."),
    여러관심사매핑해제(200, "DISSOCIATED_SUCCESS", "여러 관심사와 멤버간 매핑을 성공적으로 해제했습니다.");

    private final int status;
    private final String responseCode;
    private final String responseMessage;

    ResponseType(int status, String responseCode, String responseMessage) {
        this.status = status;
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }
}
