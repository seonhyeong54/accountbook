package com.payhere.accountbook.accountbook.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VerifyResult {

    private boolean success;
    private String email;
}
