package com.zy.could.hwtcp.dto;

import lombok.Data;

/**
 * <p>Title: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Version:zhuoyuan V2.0</p>
 *
 * @author gc
 * @description
 * @date 2021/8/23 上午 11:22
 */
@Data
public class HwMessageDto {

    /**
     * heart 心跳信息, ClockIn 打卡信息
     */
    private String messageType;

    /**
     * 设备信息
     */
    private HwDeviceDto deviceDto;

    /**
     * 人脸识别终端打卡信息
     */
    private TcpParamData tcpParamData;
}
