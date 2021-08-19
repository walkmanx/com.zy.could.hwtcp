package com.zy.could.hwtcp.service;

import com.zy.could.hwtcp.common.constant.CommandEnum;
import com.zy.could.hwtcp.common.constant.MessageEnum;
import com.zy.could.hwtcp.common.constant.SuccessEnum;
import com.zy.could.hwtcp.common.util.BuildResultUtils;
import com.zy.could.hwtcp.common.util.JsonUtils;
import com.zy.could.hwtcp.dto.UdpMessageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.ip.udp.UnicastSendingMessageHandler;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>Title: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Version:zhuoyuan V2.0</p>
 *
 * @author gc
 * @description
 * @date 2021/8/12 上午 9:54
 */
@Service
@Slf4j
public class UdpBusinessService  {

    @Autowired
    private UnicastSendingMessageHandler unicastSendingMessageHandler;

    /**
     * 发送UDP消息
     * @param message
     */
    public void sendMessage(String message) {
//        log.info("发送UDP消息: {}", message);
        unicastSendingMessageHandler.handleMessage(MessageBuilder.withPayload(message).build());
//        log.info("发送成功");
    }

    /**
     * 处理服务器接收的UDP消息
     * @param message
     */
    public void handle(String message){
        // 进行业务处理
        if(!StringUtils.isEmpty(message)){
            try {
                // 包头用前4个字节表示数据包长度
                String head = message.substring(0,4);
                // 包体是数据包主体
                String body = message.substring(4,message.length());
                if(!StringUtils.isEmpty(body)){
                    UdpMessageData data = JsonUtils.parse(body.trim(), UdpMessageData.class);
                    log.info("[接收到的心跳消息]：" + JsonUtils.toJsonString(data));

                    this.sendMessage(BuildResultUtils.buildDevStatusResult(SuccessEnum.SUCCESS.getCode(), MessageEnum.DevStatus_Success.getName(), CommandEnum.PostRequest.getCode()));
                }
            }catch (Exception e){
                this.sendMessage(BuildResultUtils.buildDevStatusResult(SuccessEnum.FAIL.getCode(),MessageEnum.DevStatus_FAIL.getName() + ":" + e.getMessage(),CommandEnum.PostRequest.getCode()));
            }
        }
    }



}
