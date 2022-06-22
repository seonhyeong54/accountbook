package com.payhere.accountbook.accountbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ResultDto {
    private int code;
    private String message;
    private Object data;
    public static ResultDto success(Object data) {
        return ResultDto.builder()
                .code(200)
                .message("success")
                .data(data)
                .build();
    }
    public static ResultDto success() {
        return ResultDto.builder()
                .code(200)
                .message("success")
                .build();
    }
}
