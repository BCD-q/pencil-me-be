package com.bcdq.pencilme.member.controller;

import com.bcdq.pencilme.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Member 관련 Controller
 * 사용자 조회, 가입, 수정, 탈퇴 요청
 *
 * @author Juwon Lee
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
}
