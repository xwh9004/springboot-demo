package com.example.zuul.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.example.common.util.UserContext.CORRELATION_ID;

/**
 * <p><b>Description:</b>
 * 服务追踪过滤器
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 16:20 on 2020/6/4
 * @version V0.1
 * @classNmae TrackingFilter
 */

@Slf4j
@Component
public class TrackingFilter extends ZuulFilter {

    private static final int FILTER_ORDER = 1;

    private static final boolean SHOULD_FILTER = true;


    @Autowired
    FilterUtils filterUtils;

    @Override
    public String filterType() {
        return FilterUtils.PRE_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    @Override
    public Object run() throws ZuulException {
        if (isCorrelationIdPresent()) {
            log.info("tmx-correlation-id found in tracking filter:{}", filterUtils.getCorrelationId());
        } else {
            filterUtils.setCorrelationId(generateCorrelationId());
            log.info("tmx-correlation-id generate in tracking filter:{}", filterUtils.getCorrelationId());
        }

//        log.info("The organization id from the token is {} " ,getOrganizationId());
//        filterUtils.setOrgId(getOrganizationId());

        RequestContext ctx = RequestContext.getCurrentContext();
        log.info("processing incoming request for {}", ctx.getRequest().getRequestURI());


        return null;
    }



    private String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }

    private boolean isCorrelationIdPresent() {
        RequestContext ctx = RequestContext.getCurrentContext();
        if(ctx.getRequest().getHeader(CORRELATION_ID)!=null){
            return true;
        }
        return false;
    }

    private String getOrganizationId(){
        String result ="";
        if(filterUtils.getAuthToken()!=null){
            String authToken =filterUtils.getAuthToken().replace("Bearer","");
            DecodedJWT decode =JWT.decode(authToken);
            result = decode.getClaim("organizationId").asString();
        }

        return result;
    }
}
