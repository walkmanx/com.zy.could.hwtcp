package com.zy.could.hwtcp.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import java.util.Date;

/**
 * @author Administrator
 */
public class JsonUtils {
    private static SerializeConfig mapping = new SerializeConfig();
    private static String dateFormat = "yyyy-MM-dd HH:mm:ss";

    public JsonUtils() {
    }

    public static Object toJsonObject(Object object) {
        return JSON.toJSON(object);
    }

    public static String toJsonString(Object object) {
        return JSON.toJSONString(object, new SerializerFeature[]{SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteMapNullValue});
    }

    public static String toJsonString2(Object object) {
        return JSON.toJSONString(object, mapping, new SerializerFeature[]{SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteMapNullValue});
    }

    public static <E> E parse(String text, Class<E> clazz) {
        return JSON.parseObject(text, clazz);
    }

    static {
        mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormat));
    }
}