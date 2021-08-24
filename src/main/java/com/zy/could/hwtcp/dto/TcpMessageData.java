package com.zy.could.hwtcp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * <p>Title: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Version:zhuoyuan V2.0</p>
 *
 * @author gc
 * @description
 * @date 2021/8/24 上午 10:29
 */
@Data
public class TcpMessageData {

    @JsonProperty(value = "COMMAND")
    private String command;

    @JsonProperty(value = "PARAM")
    private TcpParamData param;
}
