package com.zy.could.hwtcp.handle;

import Com.FirstSolver.Splash.COMMAND_PCIR_TYPE;
import Com.FirstSolver.Splash.PARAM_REPLY_PCIR_TYPE;
import Com.FirstSolver.Splash.REPLY_PCIR_TYPE;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zy.could.hwtcp.dto.TcpMessageData;
import com.zy.could.hwtcp.service.TcpBusinessService;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>Title: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Version:zhuoyuan V2.0</p>
 *
 * @author gc
 * @description
 * @date 2021/8/19 上午 9:52
 */
@Slf4j
@Component
public class TcpHandler extends IoHandlerAdapter {

    /**
     * 存放session的容器
     */
    public static ConcurrentHashMap<String, IoSession> sessionsConcurrentHashMap = new ConcurrentHashMap<String, IoSession>();

    @Autowired
    private TcpBusinessService tcpBusinessService;

    /**
     * session开启
     * @param session
     * @throws Exception
     */
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        log.info("[TCP服务建立]：" + session.getId());
    }

    /**
     * session关闭
     * @param session
     * @throws Exception
     */
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        log.info("[TCP服务关闭]：" + session.getId());
    }

    /**
     * session重连
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        log.info("[TCP服务重连]：" + session.getId());
    }

    /**
     * 收到消息
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String str = message.toString();
        log.info("[TCP收到消息]：" + str);

        TcpMessageData data = JSON.parseObject(message.toString(), TcpMessageData.class);

        String command = data.getCommand();

        if ("RecogniseResult".equals(command)) {

            log.info("设备上传打卡记录：" + JSON.toJSONString(data.getParam()));

            tcpBusinessService.handle(data);

            // 注意：保存数据然后进行回复
            REPLY_PCIR_TYPE M = new REPLY_PCIR_TYPE();
            M.RETURN = "RecogniseResult";
            M.PARAM = new PARAM_REPLY_PCIR_TYPE();
            M.PARAM.result = "success";
            session.write(JSON.toJSONString(M));
        }
        else if("GetRequest".equals(command))
        {
            //测试获取设备信息
            JSONObject m = new JSONObject();
            m.put("RETURN","GetRequest");

            JSONObject p = new JSONObject();
            p.put("result","success");
            p.put("command","GetDeviceInfo");

            m.put("PARAM",p);
            session.write(JSON.toJSONString(m));
        }
        else if("Return".equals(command))
        {
            // 正常发送完命令要 session.closeNow();
            // 连续发送命令过程不要session.closeNow();
            // 同时连续发送命令过程中udp不要发送PostRequest
            //session.write(tbCmd.getText());//测试连续发送命令
            session.closeNow();
        }
        else if("Quit".equals(command))
        {
            session.closeNow();
        }



    }
}
