package com.example.zuul.filter;

import com.example.common.entity.UserContext;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import static com.example.common.entity.UserContext.CORRELATION_ID;


/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 16:27 on 2020/6/4
 * @version V0.1
 * @classNmae FilterUtils
 */

@Component
public class FilterUtils {
    public static final String PRE_FILTER_TYPE ="pre";

    public String getCorrelationId() {
        RequestContext ctx = RequestContext.getCurrentContext();
        if(ctx.getRequest().getHeader(CORRELATION_ID)!=null){
            return ctx.getRequest().getHeader(CORRELATION_ID);
        }else{
            return ctx.getZuulRequestHeaders().get(CORRELATION_ID);
        }
    }

    public void setCorrelationId(String id) {
        RequestContext ctx = RequestContext.getCurrentContext();

        ctx.addZuulRequestHeader(CORRELATION_ID,id);
    }
}
