package com.example.licenses.handler;

import com.example.common.entity.OrganizationChangeModel;
import com.example.licenses.event.CustomChannel;
import com.example.licenses.repository.OrganizationRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 16:20 on 2020/6/11
 * @version V0.1
 * @classNmae OrganizationChangeHandler
 */
@Slf4j
@EnableBinding( CustomChannel.class )
public class OrganizationChangeHandler {
    @Autowired
    private OrganizationRedisRepository organizationRedisRepository;

    @StreamListener( "inboundOrgChanges" )  //自定义监听通道
    public void loggerSink(OrganizationChangeModel orgChange) {
        log.debug("Received a message of type " + orgChange.getType());
        switch (orgChange.getAction()) {
            case "GET":
                log.debug("Received a GET event from the organization service for organization id {}", orgChange.getOrganizationId());
                break;
            case "SAVE":
                log.debug("Received a SAVE event from the organization service for organization id {}", orgChange.getOrganizationId());
                break;
            case "UPDATE":
                log.debug("Received a UPDATE event from the organization service for organization id {}", orgChange.getOrganizationId());
                organizationRedisRepository.deleteOrganization(orgChange.getOrganizationId());
                break;
            case "DELETE":
                log.debug("Received a DELETE event from the organization service for organization id {}", orgChange.getOrganizationId());
                organizationRedisRepository.deleteOrganization(orgChange.getOrganizationId());
                break;
            default:
                log.info("Received an UNKNOWN event from the organization service of type {}", orgChange.getType());
                break;
        }
    }

}
