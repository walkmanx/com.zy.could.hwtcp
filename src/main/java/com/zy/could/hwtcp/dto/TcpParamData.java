package com.zy.could.hwtcp.dto;

import lombok.Data;

/**
 * <p>Title: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Version:zhuoyuan V2.0</p>
 *
 * @author gc
 * @description
 * @date 2021/8/24 上午 10:30
 */
@Data
public class TcpParamData {

    /**
     * 设备编号
     */
    private String sn;

    /**
     * 考勤编号
     */
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 是否通过（1-通过 0-不通过）
     */
    private String pass;

    /**
     * 识别方式
     */
    private String identifyType;

    /**
     * 认证方式
     */
    private String recogType;

    /**
     * 用户是否更新
     */
    private Boolean isUserUpdate;

    /**
     * 分数
     */
    private String score;

    /**
     *阈值
     */
    private String threshold;

    /**
     *打卡时间
     */
    private String time;

    /**
     *设备型号
     */
    private String type;

    /**
     *人员照片（ Base64编码字符串）
     */
    private String capturejpg;

    /**
     *算法版本
     */
    private String alg_edition;

    /**
     *体温
     */
    private String animalHeat;

    /**
     *是否佩戴口罩(1:佩戴，2:未佩戴)
     */
    private String wearMask;




}
