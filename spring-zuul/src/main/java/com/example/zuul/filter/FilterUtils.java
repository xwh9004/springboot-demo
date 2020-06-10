package com.example.zuul.filter;

import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import static com.example.common.util.UserContext.*;


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
    public static final String POST_FILTER_TYPE ="post";
    public static final String ROUTE_FILTER_TYPE ="route" ;

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

    public String getOrgId(){
        RequestContext ctx = RequestContext.getCurrentContext();
        if(ctx.getRequest().getHeader(ORG_ID)!=null){
            return ctx.getRequest().getHeader(ORG_ID);
        }else{
            return ctx.getZuulRequestHeaders().get(ORG_ID);
        }
    }

    public void setOrgId(String orgId){
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(ORG_ID,  orgId);
    }

    public String getServiceId(){
        RequestContext ctx = RequestContext.getCurrentContext();
        //We might not have a service id if we are using a static, non-eureka route.
        if (ctx.get("serviceId")==null) return "";
        return ctx.get("serviceId").toString();
    }

    public final String getAuthToken(){
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.getRequest().getHeader(AUTH_TOKEN);
    }
}
