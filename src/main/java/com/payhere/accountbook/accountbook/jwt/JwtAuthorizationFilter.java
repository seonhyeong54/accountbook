package com.payhere.accountbook.accountbook.jwt;

import com.payhere.accountbook.accountbook.entity.User;
import com.payhere.accountbook.accountbook.service.UserDetailServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.security.sasl.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private final UserDetailServiceImpl userDetailService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailServiceImpl userDetailService) {
        super(authenticationManager);
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String bearer = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (bearer == null || !bearer.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String token = bearer.substring("Bearer ".length());
        VerifyResult result = JwtUtil.verify(token);

        if (result.isSuccess()) {
            User user = (User) userDetailService.loadUserByUsername(result.getEmail());
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    user.getEmail(),
                    null,
                    user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        } else {
            throw new AuthenticationException("유효하지 않은 토큰입니다.");
        }

    }
}
