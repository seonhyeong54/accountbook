package com.payhere.accountbook.accountbook.controller;

import com.payhere.accountbook.accountbook.dto.AccountBookDto;
import com.payhere.accountbook.accountbook.dto.AccountBookRequestDto;
import com.payhere.accountbook.accountbook.dto.AccountBookUpdateRequestDto;
import com.payhere.accountbook.accountbook.dto.ResultDto;
import com.payhere.accountbook.accountbook.service.AccountBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AccountBookController {

    private final AccountBookService accountBookService;

    @GetMapping("/accountbook")
    public Page<AccountBookDto> accountBookList(HttpServletRequest req,
                                                @PageableDefault(size = 5, sort = "accountBookId", direction = Sort.Direction.DESC)
                                Pageable pageable) {
        return accountBookService.getAccountBookList(req.getRemoteUser(), pageable);
    }

    @PostMapping("/accountbook")
    public ResultDto saveAccountBook(HttpServletRequest req,
                                     @RequestBody @Valid AccountBookRequestDto requestDto) {
        AccountBookDto accountBookDto = accountBookService.saveAccountBook(req.getRemoteUser(), requestDto);
        return ResultDto.success(accountBookDto);
    }

    @GetMapping("/accountbook/{accountBookId}")
    public ResultDto accountBook(HttpServletRequest req,
                                 @PathVariable("accountBookId") Long accountBookId) {
        AccountBookDto accountBookDto = accountBookService.getAccountBook(req.getRemoteUser(), accountBookId);
        return ResultDto.success(accountBookDto);
    }

    @PutMapping("/accountbook/{accountBookId}")
    public ResultDto updateAccountBook(HttpServletRequest req,
                                       @PathVariable("accountBookId") Long accountBookId,
                                       @RequestBody @Valid AccountBookUpdateRequestDto requestDto) {
        AccountBookDto accountBookDto = accountBookService.update(req.getRemoteUser(),accountBookId, requestDto);
        return ResultDto.success(accountBookDto);
    }

    @DeleteMapping("/accountbook/{accountBookId}")
    public ResultDto deleteAccountBook(HttpServletRequest req,
                                       @PathVariable("accountBookId") Long accountBookId) {
        accountBookService.delete(req.getRemoteUser(), accountBookId);
        return ResultDto.success();
    }

    @PutMapping("/accountbook/{accountBookId}/active")
    public ResultDto activateAccountBook(HttpServletRequest req,
                                         @PathVariable("accountBookId") Long accountBookId) {
        AccountBookDto accountBookDto = accountBookService.activate(req.getRemoteUser(),accountBookId);
        return ResultDto.success(accountBookDto);
    }

}
