package com.dcc.orderservice.Service.Impl;

import com.dcc.orderservice.Service.AuthValidateService;
import com.dcc.orderservice.Client.AuthValidate.AuthValidateRestClient;
import com.dcc.orderservice.Client.AuthValidate.Request.AuthValidateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthValidateServiceImpl implements AuthValidateService {

    private final AuthValidateRestClient authValidateRestClient;

    @Override
    public boolean authValidate(AuthValidateRequest request) {
        return authValidateRestClient.authValidate(request);
    }
}
