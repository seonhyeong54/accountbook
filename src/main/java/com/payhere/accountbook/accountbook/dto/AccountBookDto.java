package com.payhere.accountbook.accountbook.dto;

import com.payhere.accountbook.accountbook.entity.AccountBook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class AccountBookDto {
    private Long accountBookId;
    private Long userId;
    private Long money;
    private String memo;

    public static AccountBookDto of (AccountBook accountBook) {
        return AccountBookDto.builder()
                .accountBookId(accountBook.getAccountBookId())
                .userId(accountBook.getUser().getUserId())
                .money(accountBook.getMoney())
                .memo(accountBook.getMemo())
                .build();
    }
}
