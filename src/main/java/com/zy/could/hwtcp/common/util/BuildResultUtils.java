package com.zy.could.hwtcp.common.util;

import com.alibaba.fastjson.JSONObject;
import com.zy.could.hwtcp.common.constant.CommandEnum;
import org.springframework.util.StringUtils;

/**
 * <p>Title: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Version:zhuoyuan V2.0</p>
 *
 * @author gc
 * @description
 * @date 2021/8/18 上午 11:26
 */
public class BuildResultUtils {

    /**
     * 构建接收心跳回复
     * @return
     */
    public static String buildDevStatusResult(String success,String reason,String command){
        JSONObject result = new JSONObject();
        result.put("RETURN","DevStatus");

        JSONObject param = new JSONObject();
        param.put("result", !StringUtils.isEmpty(success) ? success : "success");
        param.put("reason",reason);
        param.put("command",!StringUtils.isEmpty(command) ? command : CommandEnum.PostRequest.getCode());

        result.put("PARAM",param);

        return result.toJSONString();
    }





}
