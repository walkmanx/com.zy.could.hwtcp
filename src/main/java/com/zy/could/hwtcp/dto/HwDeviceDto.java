package com.zy.could.hwtcp.dto;

import lombok.Data;

/**
 * <p>Title: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Version:zhuoyuan V2.0</p>
 *
 * @author gc
 * @description
 * @date 2021/8/23 上午 11:27
 */
@Data
public class HwDeviceDto {
    /**
     * 设备序列号
     */
    private String sn;

    /**
     * 设备人脸模板算法版本
     */
    private String alg_edition;

    /**
     * 设备软件版本
     */
    private String edition;

    /**
     * 设备型号
     */
    private String type;

    /**
     * 默认网关
     */
    private String gateway;

    /**
     * 设备当前的IP地址
     */
    private String ip;

    /**
     * 设备MAC地址
     */
    private String mac;

    /**
     *
     */
    private Boolean managerlock;

    /**
     *
     */
    private Integer managernum;

    /**
     * 最大用户数量
     */
    private Integer max_employee;

    /**
     * 最大的记录条数
     */
    private Integer max_facerecord;

    /**
     * 最大用户人脸注册数量
     */
    private Integer max_faceregist;

    /**
     *
     */
    private Integer max_managernum;

    /**
     *
     */
    private Integer max_other;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 子网掩码
     */
    private String netmask;

    /**
     * 实际用户数量
     */
    private Integer real_employee;

    /**
     * 实际记录条数
     */
    private Integer real_facerecord;

    /**
     * 实际使用的用户人脸注册数量
     */
    private Integer real_faceregist;

    /**
     *
     */
    private Integer real_other;

    /**
     * 时间
     */
    private String time;

    /**
     *
     */
    private Integer volume;

    /**
     *
     */
    private Integer wiegand;
}
