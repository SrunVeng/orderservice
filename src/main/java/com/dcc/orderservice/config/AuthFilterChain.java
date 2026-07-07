package com.dcc.orderservice.config;

import com.dcc.orderservice.Service.AuthValidateService;
import com.dcc.orderservice.Client.AuthValidate.Request.AuthValidateRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Slf4j
@Component
@RequiredArgsConstructor
public class AuthFilterChain extends OncePerRequestFilter {

    private final AuthValidateService authValidateService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        AuthValidateRequest requestValidate = new AuthValidateRequest();
        requestValidate.setBearerToken(request.getHeader("Authorization"));
        authValidateService.authValidate(requestValidate);
        filterChain.doFilter(request, response);
    }


}
