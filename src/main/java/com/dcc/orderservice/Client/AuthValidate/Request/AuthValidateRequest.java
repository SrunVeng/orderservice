package com.dcc.orderservice.Client.AuthValidate.Request;


import lombok.Data;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AuthValidateRequest {

    private String token;
}
