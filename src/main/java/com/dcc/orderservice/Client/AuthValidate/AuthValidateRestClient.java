package com.dcc.orderservice.Client.AuthValidate;


import com.dcc.orderservice.Constant.ErrorCode.ErrorCode;
import com.dcc.orderservice.Client.AuthValidate.Request.AuthValidateRequest;
import com.dcc.orderservice.Client.AuthValidate.Response.AuthValidateResponse;
import com.dcc.sdkcentral.config.RestClientExecutor;
import com.dcc.sdkcentral.config.RestClientExecutorFactory;
import com.dcc.sdkcentral.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthValidateRestClient {

    private static final String AUTH_CLIENT_NAME = "auth";

    private final RestClientExecutorFactory restClientExecutorFactory;


    public boolean authValidate(AuthValidateRequest request) {
        if (request == null || request.getBearerToken() == null || request.getBearerToken().isBlank()) {
            throw new BusinessException(ErrorCode.INVALID_CREDENTIAL.getCode(), ErrorCode.INVALID_CREDENTIAL.getMessage());
        }

        RestClientExecutor restClientExecutor = restClientExecutorFactory.getExecutor(AUTH_CLIENT_NAME);
        AuthValidateResponse response = restClientExecutor.execute(
                HttpMethod.POST,
                "/auth/validate",
                request,
                AuthValidateResponse.class
        );
        AuthValidateResponse.AuthValidateResponseData data = Objects.requireNonNull(response, "auth validation response")
                .getData();
        if (data == null || !data.isValid()) {
            throw new BusinessException(ErrorCode.INVALID_CREDENTIAL.getCode(), ErrorCode.INVALID_CREDENTIAL.getMessage());
        }
        return true;
    }


}
