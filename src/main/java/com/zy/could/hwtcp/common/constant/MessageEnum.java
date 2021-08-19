package com.zy.could.hwtcp.common.constant;

/**
 * <p>Title: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Version:zhuoyuan V2.0</p>
 *
 * @author gc
 * @description
 * @date
 */
public enum MessageEnum {
    /**
     * 服务器接收心跳数据成功
     */
    DevStatus_Success("DevStatusSuccess","服务器接收心跳数据成功"),
    /**
     * 服务器接收心跳数据失败
     */
    DevStatus_FAIL("DevStatusFail","服务器接收心跳数据失败");

    private MessageEnum(String code, String name){
        this.code = code;
        this.name = name;
    }

    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
