package com.zy.could.hwtcp.config;

import com.zy.could.hwtcp.common.constant.CommandEnum;
import com.zy.could.hwtcp.common.util.SpringContextUtil;
import com.zy.could.hwtcp.service.UdpBusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.ip.dsl.Udp;
import org.springframework.integration.ip.tcp.TcpReceivingChannelAdapter;
import org.springframework.integration.ip.tcp.TcpSendingMessageHandler;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
import org.springframework.integration.ip.tcp.serializer.ByteArrayRawSerializer;
import org.springframework.integration.ip.udp.UnicastSendingMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;

import java.util.Map;

/**
 * <p>Title: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Version:zhuoyuan V2.0</p>
 *
 * @author gc
 * @description
 * @date 2021/8/12 上午 9:43
 */
@Configuration
@Slf4j
public class UdpConfiguration {

    @Value("${udp.port}")
    private Integer udpPort;

    @Value("${tcp.port}")
    private Integer tcpPort;

    /**
     * UDP消息接收服务
     * @return
     */
    @Bean
    public IntegrationFlow integrationFlow() {
        log.info("UDP服务启动成功，端口号为: {}", udpPort);
        return IntegrationFlows.from(Udp.inboundAdapter(udpPort)).channel("udpChannel").get();
    }

    /**
     * 转换器
     * @param message
     * @return
     */
    @Transformer(inputChannel="udpChannel",outputChannel="udpFilter")
    public String transformer(Message<?> message) {
        //把接收的数据转化为字符串
        return new String((byte[])message.getPayload());
    }

    /**
     * 过滤器
     * @param message
     * @param headers
     * @return
     */
    @Filter(inputChannel="udpFilter",outputChannel="udpRoute")
    public boolean filter(String message,@Headers Map<String, Object> headers) {
        // 获取来源Id
        String id = headers.get("id").toString();
        // 获取来源IP，可以进行IP过滤
        String ip = headers.get("ip_address").toString();
        // 获取来源Port
        String port = headers.get("ip_port").toString();
        // 信息数据过滤
        /*if (message.indexOf("-") < 0) {
            // 没有-的数据会被过滤
            return false;
        }*/
        return true;
    }

    /**
     * 路由分发处理器
     * @param message
     * @return
     */
    @Router(inputChannel="udpRoute")
    public String routing(String message, @Headers Map<String, Object> headers) {
        //当接收数据包含心跳命令时
        if(message.contains(CommandEnum.Command.getCode()) && message.contains(CommandEnum.DevStatus.getCode())) {
            return "DevStatus";
        }
        else {
            return "udpRoute2";
        }
    }


    @ServiceActivator(inputChannel="DevStatus")
    public void udpMessageHandle(String message) {
//        log.info("接收到心跳数据:" +message);
        UdpBusinessService udpBusinessService = SpringContextUtil.getBean(UdpBusinessService.class);
        udpBusinessService.handle(message);
    }


    @ServiceActivator(inputChannel="udpRoute2")
    public void udpMessageHandle2(String message) {
//        log.info("接收到非心跳数据:" +message);
    }

    @Bean
    @ServiceActivator(inputChannel = "udpOut")
    public UnicastSendingMessageHandler unicastSendingMessageHandler() {
        UnicastSendingMessageHandler unicastSendingMessageHandler = new UnicastSendingMessageHandler("localhost", udpPort);
        return unicastSendingMessageHandler;
    }

    /**
    @Bean
    public TcpNetServerConnectionFactory getServerConnectionFactory() {
        TcpNetServerConnectionFactory serverConnectionFactory = new TcpNetServerConnectionFactory(tcpPort);
        serverConnectionFactory.setSerializer(new ByteArrayRawSerializer());
        serverConnectionFactory.setDeserializer(new ByteArrayRawSerializer());
        serverConnectionFactory.setLookupHost(false);
        return serverConnectionFactory;
    }


    @Bean
    public TcpReceivingChannelAdapter getReceivingChannelAdapter() {
        TcpReceivingChannelAdapter receivingChannelAdapter = new TcpReceivingChannelAdapter();
        receivingChannelAdapter.setConnectionFactory(getServerConnectionFactory());
        receivingChannelAdapter.setOutputChannelName("tcpIn");
        return receivingChannelAdapter;
    }

    @ServiceActivator(inputChannel="tcpIn")
    public void messageHandle(Message<?> message) {
        log.info("接收到设备TCP消息：" );
        System.out.println(new String((byte[])message.getPayload()));
    }

    @Bean
    @ServiceActivator(inputChannel = "tcpOut")
    public TcpSendingMessageHandler getSendChannelAdapter() {
        TcpSendingMessageHandler outGate = new TcpSendingMessageHandler();
        outGate.setConnectionFactory(getServerConnectionFactory());
        return outGate;
    }
    */
}
