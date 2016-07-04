package org.dragonfei.sertest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

/**
 * Created by longfei on 16-7-3.
 */
public class PerformTestUserInfo {
    public static void main(String[] args) throws IOException {
        UserInfo info = new UserInfo();
        info.buildUserID(100).buildUserName("welcome to netty");
        int loop = 1000000;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        bos = new ByteArrayOutputStream();
        os = new ObjectOutputStream(bos);
        long starttime = System.currentTimeMillis();
        for(int i = 0; i < loop; i++){
            bos.reset();
            os.writeObject(info);
            os.flush();
            byte[] b = bos.toByteArray();
        }

        long endtime = System.currentTimeMillis();
        os.close();
        bos.close();

        System.out.println(String.format("The jdk serializable cost time is :%d ms",endtime-starttime));

        System.out.println("--------------------------------");
        starttime = System.currentTimeMillis();
        for(int i = 0; i < loop;i++){
            byte[] b = info.codeC();
        }
        endtime = System.currentTimeMillis();;
        System.out.println(String.format("The byte array serializable cost time is :%d ms",endtime-starttime));

    }
}
