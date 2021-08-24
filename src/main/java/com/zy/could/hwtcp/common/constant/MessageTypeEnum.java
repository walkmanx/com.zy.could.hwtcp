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
public enum MessageTypeEnum {
    /**
     * 心跳消息
     */
    HEARTBEAT("heartbeat","心跳消息"),
    /**
     * 打卡消息
     */
    CLOCKIN("clockIn","打卡消息");

    private MessageTypeEnum(String code, String name){
        this.code = code;
        this.name = name;
    }

    public static MessageTypeEnum getMessageTypeEnum(String code){
        for (MessageTypeEnum messageTypeEnum : MessageTypeEnum.values()){
            if(messageTypeEnum.getCode().equals(code)){
                return messageTypeEnum;
            }
        }
        return null;
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
