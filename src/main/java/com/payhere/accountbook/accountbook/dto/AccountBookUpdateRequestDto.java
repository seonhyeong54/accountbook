package com.payhere.accountbook.accountbook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class AccountBookUpdateRequestDto {

    @NotNull
    private Long money;

    @NotBlank
    private String memo;
}
