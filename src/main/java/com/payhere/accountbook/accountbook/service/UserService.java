package com.payhere.accountbook.accountbook.service;

import com.payhere.accountbook.accountbook.dto.SignupResponseDto;
import com.payhere.accountbook.accountbook.dto.UserRequestDto;
import com.payhere.accountbook.accountbook.entity.User;
import com.payhere.accountbook.accountbook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public SignupResponseDto signup(UserRequestDto requestDto) {
        validateEmail(requestDto);
        String encodedPassword = getEncodedPassword(requestDto);
        User newUser = User.of(requestDto, encodedPassword);
        User savedUser = userRepository.save(newUser);
        savedUser.addRole();
        return SignupResponseDto.of(savedUser);
    }
    private void validateEmail(UserRequestDto requestDto) {
        userRepository.findByEmail(requestDto.getEmail()).ifPresent(
                u -> {
                    throw new IllegalArgumentException("이미 존재하는 회원입니다.");
                }
        );
    }
    private String getEncodedPassword(UserRequestDto requestDto) {
        return passwordEncoder.encode(requestDto.getPassword());
    }

}
