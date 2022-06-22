package com.payhere.accountbook.accountbook.controller;

import com.payhere.accountbook.accountbook.dto.ResultDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResultDto home() {
        return ResultDto.success();
    }
}
