package com.zy.could.hwtcp.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zy.could.hwtcp.common.constant.MessageTypeEnum;
import com.zy.could.hwtcp.dto.HwDeviceDto;
import com.zy.could.hwtcp.dto.HwMessageDto;
import com.zy.could.hwtcp.dto.TcpMessageData;
import com.zy.could.hwtcp.dto.TcpParamData;
import com.zy.could.hwtcp.handle.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Title: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Version:zhuoyuan V2.0</p>
 *
 * @author gc
 * @description
 * @date 2021/8/18 下午 16:17
 */
@Service
@Slf4j
public class TcpBusinessService {

    @Autowired
    private MessageSender sender;

    /**
     * 处理服务器接收的TCP消息
     * @param message
     */
    public void handle(TcpMessageData data){
        log.info("处理Tcp消息业务逻辑执行");

        HwMessageDto hwMessageDto = new HwMessageDto();
        hwMessageDto.setMessageType(MessageTypeEnum.CLOCKIN.getCode());
        hwMessageDto.setTcpParamData(data.getParam());

        sender.send(JSON.toJSONString(hwMessageDto));
    }

    /**
     *  重启设备
     */
    public String restart(){

        log.info("--------  重启设备 -------");

        JSONObject m = new JSONObject();
        m.put("RETURN","GetRequest");

        JSONObject p = new JSONObject();
        p.put("result","success");
        p.put("command","RestartDevice");

        m.put("PARAM",p);

        return m.toJSONString();
    }





}
