package com.payhere.accountbook.accountbook.service;

import com.payhere.accountbook.accountbook.dto.SignupResponseDto;
import com.payhere.accountbook.accountbook.dto.UserRequestDto;
import com.payhere.accountbook.accountbook.entity.User;
import com.payhere.accountbook.accountbook.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private String email;
    private String password;

    @BeforeEach
    void setUp() {
        email = "test@test.com";
        password = "test123!@#";
    }

    @InjectMocks
    private UserService userService;

    @DisplayName("회원가입 성공")
    @Test
    void testSignup() {
        // given
        User user = User.builder().build();
        given(userRepository.save(any())).willReturn(user);

        // when
        UserRequestDto requestDto = UserRequestDto.builder().email(email).password(password).build();
        SignupResponseDto responseDto = userService.signup(requestDto);

        // then
        assertEquals(user.getUserId(), responseDto.getUserId());
    }

}