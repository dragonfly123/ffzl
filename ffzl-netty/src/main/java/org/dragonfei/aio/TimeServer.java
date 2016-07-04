package org.dragonfei.aio;


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
        AsyncTimeServerHandler timeServerHandler = new AsyncTimeServerHandler(8080);
        new Thread(timeServerHandler,"timerverHanderler").start();
    }
}
