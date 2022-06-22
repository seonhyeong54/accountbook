package com.payhere.accountbook.accountbook.service;

import com.payhere.accountbook.accountbook.entity.User;
import com.payhere.accountbook.accountbook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("존재하지 않는 사용자입니다.")
        );
        if (user != null) {
            Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) user.getAuthorities();
            authorities.add(new SimpleGrantedAuthority("user"));
            return user;
        }
        return null;
    }
}
