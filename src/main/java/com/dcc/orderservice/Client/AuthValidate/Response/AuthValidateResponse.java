package com.dcc.orderservice.Client.AuthValidate.Response;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AuthValidateResponse {

    private String devErrorCode;
    private String devErrorMessage;
    private AuthValidateResponseData data;


    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Data
    public static class AuthValidateResponseData {
        private boolean valid;
        private String userName;
    }


}
