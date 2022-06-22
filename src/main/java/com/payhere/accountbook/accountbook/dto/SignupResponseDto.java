package com.payhere.accountbook.accountbook.dto;

import com.payhere.accountbook.accountbook.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupResponseDto {
    private Long userId;
    private String email;
    public static SignupResponseDto of(User savedUser) {
        return SignupResponseDto.builder()
                .userId(savedUser.getUserId())
                .email(savedUser.getEmail())
                .build();
    }
}
