package com.zy.could.hwtcp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.ip.udp.UnicastSendingMessageHandler;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * <p>Title: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Version:zhuoyuan V2.0</p>
 *
 * @author gc
 * @description
 * @date 2021/8/12 上午 9:53
 */
@Service
@Slf4j
public class UdpSendService {

    @Autowired
    private UnicastSendingMessageHandler unicastSendingMessageHandler;

    /**
     * 发送UDP消息
     * @param message
     */
    public void sendMessage(String message) {
        log.info("发送UDP: {}", message);
        unicastSendingMessageHandler.handleMessage(MessageBuilder.withPayload(message).build());
        log.info("发送成功");
    }
}
