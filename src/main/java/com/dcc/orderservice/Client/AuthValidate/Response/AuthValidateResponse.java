package com.dcc.orderservice.Client.AuthValidate.Response;


import lombok.Data;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

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
