package com.example.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ResponseFilter extends ZuulFilter {

    private static final int FILTER_ORDER = 1;

    private static final boolean SHOULD_FILTER = true;


    @Autowired
    FilterUtils filterUtils;

    @Override
    public String filterType() {
        return FilterUtils.POST_FILTER_TYPE;
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
       RequestContext ctx = RequestContext.getCurrentContext();
       log.info("Adding the correlation id to the outbound headers. {}",filterUtils.getCorrelationId());

       ctx.getResponse().addHeader(CORRELATION_ID,filterUtils.getCorrelationId());

       log.info("Completing outgoing request for {}.",ctx.getRequest().getRequestURI());
        return null;
    }




}
