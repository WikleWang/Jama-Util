package com.amt.jama.util.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

/**
 * FastJSON工具类
 *
 * @author WangWei
 */
public class FastJsonUtils {

    private static final SerializeConfig config;

    static {
        config = new SerializeConfig();
        // 使用和json-lib兼容的日期输出格式
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer());
        // 使用和json-lib兼容的日期输出格式
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer());
    }

    private static final SerializerFeature[] features = {
            // 输出空置字段
            SerializerFeature.WriteMapNullValue,
            // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullListAsEmpty,
            // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullNumberAsZero,
            // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullBooleanAsFalse,
            // 字符类型字段如果为null，输出为""，而不是null
            SerializerFeature.WriteNullStringAsEmpty
    };


    /**
     * 按照Features的格式，将Object转JSON字符串
     *
     * @param object 对象
     * @return String
     */
    public static String convertObjectToJSON(Object object) {
        return JSON.toJSONString(object, config, features);
    }

    /**
     * 按照No Features的格式，将Object转JSON字符串
     *
     * @param object 对象
     * @return String
     */
    public static String toJSONNoFeatures(Object object) {
        return JSON.toJSONString(object, config);
    }


    /**
     * 将JSON字符串，转换成Object对象
     *
     * @param text 字符串
     * @return Object
     */
    public static Object toBean(String text) {
        return JSON.parse(text);
    }

    /**
     * 将JSON字符串，转换成clazz的对象
     *
     * @param text  字符串
     * @param clazz clazz
     * @param <T>   clazz对象
     * @return T
     */
    public static <T> T toBean(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    /**
     * 转换为数组
     *
     * @param text 字符串
     * @return T
     */
    public static <T> Object[] toArray(String text) {
        return toArray(text, null);
    }

    /**
     * 转换为数组
     *
     * @param text  字符串
     * @param clazz clazz
     * @return Object[]
     */
    public static <T> Object[] toArray(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz).toArray();
    }

    /**
     * 转换为List
     *
     * @param text  字符串
     * @param clazz clazz
     * @return List<T>
     */
    public static <T> List<T> toList(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }

    /**
     * 将string转化为序列化的json字符串
     *
     * @param text 字符串
     * @return Object
     */
    public static Object textToJson(String text) {
        return JSON.parse(text);
    }

    /**
     * 转换JSON字符串为对象
     *
     * @param jsonData 字符串
     * @param clazz    clazz
     * @return Object
     */
    public static Object convertJsonToObject(String jsonData, Class<?> clazz) {
        return JSONObject.parseObject(jsonData, clazz);
    }

    /**
     * 将map转化为string
     *
     * @param map map对象
     * @return String
     */
    public static <K, V> String collectToString(Map<K, V> map) {
        return JSONObject.toJSONString(map);
    }

    /**
     * json字符串转化为map
     *
     * @param text 字符串
     * @return Map<K, V>
     */
    @SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> stringToCollect(String text) {
        return (Map<K, V>) JSONObject.parseObject(text);
    }
}