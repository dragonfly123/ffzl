package org.dragonfei.sertest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by longfei on 16-7-3.
 */
public class TestUserInfo {
    public static void main(String[] args) throws IOException {
        UserInfo info = new UserInfo();
        info.buildUserID(100).buildUserName("this is a  simple test");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(info);
        os.flush();
        os.close();
        byte[] b = bos.toByteArray();

        System.out.println(String.format("the jdk serializable length is :%d",b.length));
        bos.close();

        System.out.println("------------------------------------------------");
        System.out.println("the byte array serializable length is : "+info.codeC().length);
    }
}
