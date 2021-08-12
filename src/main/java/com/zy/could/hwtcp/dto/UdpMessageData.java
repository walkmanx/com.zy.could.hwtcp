package com.zy.could.hwtcp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Version:zhuoyuan V2.0</p>
 *
 * @author gc
 * @description
 * @date 2021/8/12 上午 10:43
 */
@Data
public class UdpMessageData implements Serializable {

    @JsonProperty(value = "COMMAND")
    private String command;

    @JsonProperty(value = "PARAM")
    private UdpParamData param;

}
