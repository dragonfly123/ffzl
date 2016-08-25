package org.dragonfei.ffzl.params.jdis;

import redis.clients.jedis.Jedis;

/**
 * Created by longfei on 16/8/24.
 */
public class Test {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost",6379);
        String hello = jedis.get("hello");
        System.out.println(hello);
    }
}
