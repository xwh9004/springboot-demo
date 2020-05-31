package com.example.licenses.support;

import lombok.Data;

@Data
public class UserContext {


    public static final String CORRELATION_ID = "correlation_id";

    public static final String USER_ID = "user_id";

    public static final String AUTH_TOKEN = "auth_token";

    public static final String ORG_ID = "org_id";
    private String correlationId;

    private String userId;

    private String orgId;

    private String authToken;
}
