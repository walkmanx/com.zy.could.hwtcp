package com.zy.could.hwtcp.config;

import Com.FirstSolver.Splash.FaceReaderProtocolCodecFactory;
import com.zy.could.hwtcp.handle.TcpHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

/**
 * <p>Title: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Version:zhuoyuan V2.0</p>
 *
 * @author gc
 * @description
 * @date 2021/8/19 上午 10:09
 */
@Configuration
@Slf4j
public class TcpConfiguration {

    @Value("${hwtcp.tcp.port}")
    private Integer tcpPort;

    @Autowired
    private TcpHandler tcpHandler;

    @Bean
    public IoAcceptor ioAcceptor() throws Exception {
        IoAcceptor acceptor=new NioSocketAcceptor();
        acceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new FaceReaderProtocolCodecFactory(true, null)));
        acceptor.setHandler(tcpHandler);
        acceptor.getSessionConfig().setReadBufferSize(1024);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 100);
        acceptor.bind(new InetSocketAddress(tcpPort));
        log.info("TCP服务启动成功，端口号为: {}", tcpPort);
        return acceptor;
    }
}
