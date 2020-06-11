package com.example.licenses.event;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 自定义消息通道
 */
public interface CustomChannel {

    @Input("inboundOrgChanges")
  SubscribableChannel organization();
}
