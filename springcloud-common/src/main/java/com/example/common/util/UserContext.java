package com.example.common.util;

import lombok.Data;

@Data
public class UserContext {


    public static final String CORRELATION_ID = "tmx-correlation-id";

    public static final String USER_ID = "tmx-user-id";

    public static final String AUTH_TOKEN = "Authorization";

    public static final String ORG_ID = "tmx-org-id";
    private String correlationId;

    private String userId;

    private String orgId;

    private String authToken;
}
