package com.payhere.accountbook.accountbook.entity;

import com.payhere.accountbook.accountbook.dto.AccountBookRequestDto;
import com.payhere.accountbook.accountbook.dto.AccountBookUpdateRequestDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountBook extends BaseDateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountBookId;

    @Column(nullable = false)
    private Long money;

    private String memo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(columnDefinition = "boolean default true")
    private Boolean active;

    public static AccountBook of(User user, AccountBookRequestDto requestDto) {
        return AccountBook.builder()
                .money(requestDto.getMoney())
                .memo(requestDto.getMemo())
                .active(true)
                .user(user)
                .build();
    }

    public AccountBook activate() {
        active = true;
        return this;
    }

    public AccountBook update(AccountBookUpdateRequestDto requestDto) {
        money = requestDto.getMoney();
        memo = requestDto.getMemo();
        return this;
    }

    public void delete() {
        active = false;
    }
}
