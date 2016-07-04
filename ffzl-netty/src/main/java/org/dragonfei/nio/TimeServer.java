package org.dragonfei.nio;

import java.io.IOException;

/**
 * Created by longfei on 16-7-2.
 */
public class TimeServer {
    public static void main(String[] args) throws IOException{
        int port = 8080;
        if(args != null && args.length > 0){
            try{
                port = Integer.valueOf(args[0]);
            } catch (Exception e){

            }

        }
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer,"NIO-MultiplexerTimerServer").start();
    }
}
