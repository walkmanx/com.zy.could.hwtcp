package com.zy.could.hwtcp.service;

import lombok.extern.slf4j.Slf4j;
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

    /**
     * 处理服务器接收的TCP消息
     * @param message
     */
    public void handle(String message){
        log.info("处理Tcp消息业务逻辑执行");
    }

}
