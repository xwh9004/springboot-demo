package com.example.organization.events.source;

import com.example.common.util.UserContext;
import com.example.common.util.UserContextHolder;
import com.example.organization.entity.OrganizationChangeModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 16:59 on 2020/6/10
 * @version V0.1
 * @classNmae SimpleSourceBean
 */
@Slf4j
@Component
public class SimpleSourceBean {

    private Source source;

    @Autowired
    public SimpleSourceBean(Source source){
        this.source = source;
    }

    public void publishOrgChange(String action,String orgId){
        log.debug("sending Kafka message {} for organization id ={}",action,orgId);
        OrganizationChangeModel changeModel = new OrganizationChangeModel(
                OrganizationChangeModel.class.getTypeName(),
                action,orgId, UserContextHolder.getContext().getCorrelationId());

        source.output().send(MessageBuilder.withPayload(changeModel).build());
    }

}
