package com.bcdq.pencilme.communicator.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * AI 서버를 통한 할 일 생성 요청을 담은 DTO
 *
 * @author Juwon Lee
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoRequest {

    @NotBlank
    @Schema(description = "입력한 할 일의 내용을 입력해주세요", example = "내일 저녁 6시에 친구랑 잠실종합운동장역 앞에서 만나기로 했어. 친구랑 만난 후에는 조용필 콘서트를 볼거야.")
    private String memberDialog;
}
