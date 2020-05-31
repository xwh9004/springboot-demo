package com.example.licenses.filter;

import lombok.Data;

@Data
public class UserContext {

    static String CORRELATION_ID = "correlation_id";

    static String USER_ID = "user_id";

    static String AUTH_TOKEN = "auth_token";

    static String ORG_ID = "org_id";
    private String correlationId;

    private String userId;

    private String orgId;

    private String authToken;
}
