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
public enum CommandEnum {
    /**
     * 命令
     */
    Command("COMMAND","命令"),

    /**
     * 设备发送心跳数据
     */
    DevStatus("DevStatus","心跳");

    private CommandEnum(String code,String name){
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
