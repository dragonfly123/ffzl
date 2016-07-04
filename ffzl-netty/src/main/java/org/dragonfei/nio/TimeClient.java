package org.dragonfei.nio;

/**
 * Created by longfei on 16-7-2.
 */
public class TimeClient {
    public static void main(String[] args) {
        int port = 8080;
        if(args != null && args.length > 0){
            port = Integer.valueOf(args[0]);
        }

        new Thread(new TimeClientHandle(null,port),"TimeClient").start();
    }
}
