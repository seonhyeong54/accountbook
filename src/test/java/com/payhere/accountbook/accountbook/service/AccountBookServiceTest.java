package com.payhere.accountbook.accountbook.service;

import com.payhere.accountbook.accountbook.dto.AccountBookDto;
import com.payhere.accountbook.accountbook.dto.AccountBookRequestDto;
import com.payhere.accountbook.accountbook.dto.AccountBookUpdateRequestDto;
import com.payhere.accountbook.accountbook.entity.AccountBook;
import com.payhere.accountbook.accountbook.entity.User;
import com.payhere.accountbook.accountbook.repository.AccountBookRepository;
import com.payhere.accountbook.accountbook.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AccountBookServiceTest {

    @Mock
    private AccountBookRepository accountBookRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AccountBookService accountBookService;

    private String email = "test@test.com";
    private User user;
    private AccountBook accountBook;
    private Long accoutBookId = 1L;

    @BeforeEach
    void setUp() {
        user = User.builder().email(email).build();
        accountBook = AccountBook.builder().accountBookId(accoutBookId).user(user).build();
    }

    @DisplayName("가계부 저장 성공")
    @Test
    void testSaveAccountBook() {
        // given
        given(userRepository.findByEmail(any())).willReturn(Optional.of(user));
        given(accountBookRepository.save(any())).willReturn(accountBook);

        // when
        AccountBookDto dto = accountBookService.saveAccountBook(email, AccountBookRequestDto.builder().build());

        // then
        assertEquals(accountBook.getAccountBookId(), dto.getAccountBookId());
    }

    @DisplayName("가계부 상세 조회 성공")
    @Test
    void testGetAccountBook() {
        // given
        given(userRepository.findByEmail(any())).willReturn(Optional.of(user));
        AccountBook accountBook = AccountBook.builder().accountBookId(accoutBookId).user(user).build();
        given(accountBookRepository.findByAccountBookIdAndUser(any(), any())).willReturn(Optional.of(accountBook));

        // when
        AccountBookDto accountBookDto = accountBookService.getAccountBook(email, accoutBookId);

        // then
        assertEquals(accountBook.getAccountBookId(), accountBookDto.getAccountBookId());
    }

    @DisplayName("가계부 수정 성공")
    @Test
    void testUpdate() {
        // given
        given(userRepository.findByEmail(any())).willReturn(Optional.of(user));
        given(accountBookRepository.findByAccountBookIdAndUser(any(), any())).willReturn(Optional.of(accountBook));

        // when
        AccountBookUpdateRequestDto updateRequestDto = new AccountBookUpdateRequestDto(2000L, "update");
        AccountBookDto dto = accountBookService.update(email, accoutBookId, updateRequestDto);

        // then
        assertEquals(accountBook.getMemo(), dto.getMemo());
    }

    @DisplayName("가계부 삭제 성공")
    @Test
    void testDelete() {
        // given
        given(userRepository.findByEmail(any())).willReturn(Optional.of(user));
        given(accountBookRepository.findByAccountBookIdAndUser(any(), any())).willReturn(Optional.of(accountBook));

        // when
        accountBookService.delete(email, accoutBookId);

        // then
        assertFalse(accountBook.getActive());
    }

    @DisplayName("가계부 삭제 취소 성공")
    @Test
    void testActivate() {
        // given
        given(userRepository.findByEmail(any())).willReturn(Optional.of(user));
        given(accountBookRepository.findByAccountBookIdAndUser(any(), any())).willReturn(Optional.of(accountBook));

        // when
        AccountBookDto accountBookDto = accountBookService.activate(email, accoutBookId);

        // then
        assertEquals(accountBook.getAccountBookId(), accountBookDto.getAccountBookId());
        assertTrue(accountBook.getActive());
    }


}