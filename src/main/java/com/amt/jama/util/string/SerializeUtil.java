package com.amt.jama.util.string;

import com.amt.jama.util.redis.RedisUtil;
import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

/**
 * @Author: Pandy
 * @Date: 2019/2/23 14:17
 * @Version 1.0
 */
public class SerializeUtil {

    private static Logger log = Logger.getLogger(RedisUtil.class);

    public static byte[] serialize(Object object) throws Exception{
        ObjectOutputStream oos = null;
        ByteArrayOutputStream bao = null;
        try {
            //序列化
            bao = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bao);
            oos.writeObject(object);
            return bao.toByteArray();
        } catch (Exception e) {
            log.error("Error on serialize for " + object + " because of " + e.getMessage());
            throw e;
        }
    }

    public static Object unSerialize(byte[] bytes) throws Exception {
        ByteArrayInputStream bais = null;
        try {
            //反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            log.error("Error on unSerialize for " + Arrays.toString(bytes) + " because of " + e.getMessage());
            throw e;
        }
    }
}
