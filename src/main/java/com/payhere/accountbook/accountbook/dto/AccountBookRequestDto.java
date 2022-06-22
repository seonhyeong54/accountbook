package com.payhere.accountbook.accountbook.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class AccountBookRequestDto {

    @NotNull
    private Long money;

    private String memo;
}
