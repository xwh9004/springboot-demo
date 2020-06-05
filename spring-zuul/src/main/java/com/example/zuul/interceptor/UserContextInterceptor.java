package com.example.zuul.interceptor;

import com.example.common.util.UserContext;
import com.example.common.util.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 9:46 on 2020/6/5
 * @version V0.1
 * @classNmae UserContextInterceptor
 */
@Slf4j
public class UserContextInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest,
                                        byte[] bytes,
                                        ClientHttpRequestExecution clientHttpRequestExecution)
            throws IOException {

        HttpHeaders headers = httpRequest.getHeaders();
        headers.add(UserContext.CORRELATION_ID, UserContextHolder.getContext().getCorrelationId());
        headers.add(UserContext.AUTH_TOKEN, UserContextHolder.getContext().getAuthToken());
        log.info("{} CORRELATION_ID={}",UserContextInterceptor.class.getPackage().getName(),
                UserContextHolder.getContext().getCorrelationId());
        return  clientHttpRequestExecution.execute(httpRequest,bytes);
    }
}
