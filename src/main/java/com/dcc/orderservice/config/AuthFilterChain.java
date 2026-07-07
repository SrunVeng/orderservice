package com.dcc.orderservice.config;

import com.dcc.orderservice.Service.AuthValidateService;
import com.dcc.orderservice.Client.AuthValidate.Request.AuthValidateRequest;
import com.dcc.sdkcentral.exception.BusinessException;
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
        requestValidate.setToken(request.getHeader("Authorization"));
        try {
            authValidateService.authValidate(requestValidate);
        } catch (BusinessException ex) {
            writeUnauthorizedResponse(response, ex);
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        String uri = request.getRequestURI();
        return "OPTIONS".equalsIgnoreCase(request.getMethod())
                || "/health".equals(uri)
                || "/actuator/health".equals(uri)
                || "/error".equals(uri);
    }

    private void writeUnauthorizedResponse(HttpServletResponse response, BusinessException ex) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter()
                .write("{\"dev_error_code\":\""
                        + ex.getDevCode()
                        + "\",\"dev_message\":\""
                        + ex.getDevMsg()
                        + "\"}");
    }

}
