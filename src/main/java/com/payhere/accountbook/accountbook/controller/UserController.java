package com.payhere.accountbook.accountbook.controller;

import com.payhere.accountbook.accountbook.dto.ResultDto;
import com.payhere.accountbook.accountbook.dto.SignupResponseDto;
import com.payhere.accountbook.accountbook.dto.UserRequestDto;
import com.payhere.accountbook.accountbook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResultDto signup(@RequestBody @Valid UserRequestDto requestDto) {
        SignupResponseDto signup = userService.signup(requestDto);
        return ResultDto.success(signup);
    }







}
