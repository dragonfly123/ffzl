package org.dragonfei.ffzl.utils.json;

import com.alibaba.fastjson.JSON;

/**
 * Created by longfei on 16-4-24.
 */
public  abstract class JsonUtils {
    public static <T> T parse(String str,Class<T> clazz){
        return JSON.parseObject(str,clazz);
    }
}
