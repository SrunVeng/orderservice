package com.dcc.orderservice.Service;


import com.dcc.orderservice.Client.AuthValidate.Request.AuthValidateRequest;

public interface AuthValidateService {

    boolean authValidate(AuthValidateRequest token);


}
