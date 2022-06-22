package com.payhere.accountbook.accountbook.service;

import com.payhere.accountbook.accountbook.dto.AccountBookDto;
import com.payhere.accountbook.accountbook.dto.AccountBookRequestDto;
import com.payhere.accountbook.accountbook.dto.AccountBookUpdateRequestDto;
import com.payhere.accountbook.accountbook.entity.AccountBook;
import com.payhere.accountbook.accountbook.entity.User;
import com.payhere.accountbook.accountbook.repository.AccountBookRepository;
import com.payhere.accountbook.accountbook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AccountBookService {

    private final AccountBookRepository accountBookRepository;
    private final UserRepository userRepository;
    public Page<AccountBookDto> getAccountBookList(String email, Pageable pageable) {
        User user = findUser(email);
        return accountBookRepository.findByUserAndActive(pageable, user, true).map(AccountBookDto::of);
    }
    @Transactional
    public AccountBookDto saveAccountBook(String email, AccountBookRequestDto requestDto) {
        User user = findUser(email);
        AccountBook accountBook = AccountBook.of(user, requestDto);
        AccountBook savedAccountBook = accountBookRepository.save(accountBook);
        return AccountBookDto.of(savedAccountBook);
    }
    public AccountBookDto getAccountBook(String email, Long accountBookId) {
        User user = findUser(email);
        AccountBook accountBook = accountBookRepository.findByAccountBookIdAndUser(accountBookId, user)
                .orElseThrow(() -> new IllegalArgumentException("조회할 수 없는 내역입니다."));
        return AccountBookDto.of(accountBook);
    }
    @Transactional
    public AccountBookDto update(String email, Long accountBookId, AccountBookUpdateRequestDto requestDto) {
        User user = findUser(email);
        AccountBook accountBook = accountBookRepository.findByAccountBookIdAndUser(accountBookId, user)
                .orElseThrow(() -> new IllegalArgumentException("수정할 수 없는 내역입니다."));
        AccountBook updatedAccountBook = accountBook.update(requestDto);
        return AccountBookDto.of(updatedAccountBook);
    }
    @Transactional
    public void delete(String email, Long accountBookId) {
        User user = findUser(email);
        AccountBook accountBook = accountBookRepository.findByAccountBookIdAndUser(accountBookId, user)
                .orElseThrow(() -> new IllegalArgumentException("삭제할 수 없는 내역입니다."));
        accountBook.delete();
    }
    @Transactional
    public AccountBookDto activate(String email, Long accountBookId) {
        User user = findUser(email);
        AccountBook accountBook = accountBookRepository.findByAccountBookIdAndUser(accountBookId, user)
                .orElseThrow(() -> new IllegalArgumentException("복구할 수 없는 내역입니다."));
        AccountBook activatedAccountBook = accountBook.activate();
        return AccountBookDto.of(activatedAccountBook);
    }

    private User findUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("존재하지 않은 사용자입니다."));
    }
}
