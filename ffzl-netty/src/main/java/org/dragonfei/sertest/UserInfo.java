package org.dragonfei.sertest;

import org.msgpack.annotation.Message;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * Created by longfei on 16-7-3.
 */

@Message
public class UserInfo implements Serializable {

    private final static long serialVersionUID = 1111l;

    private String userName;
    private int userID;

    public UserInfo buildUserName(String userName){
        this.userName = userName;
        return this;
    }

    public UserInfo buildUserID(int userID){
        this.userID = userID;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public final void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserID() {
        return userID;
    }

    public final void setUserID(int userID){
        this.userID = userID;
    }
    transient ByteBuffer buffer = ByteBuffer.allocate(1024);
    public byte[] codeC(){
        buffer.clear();
        byte[] value = this.userName.getBytes();
        buffer.putInt(value.length);
        buffer.put(value);
        buffer.putInt(this.userID);
        buffer.flip();
        value = null;
        byte[] result = new byte[buffer.remaining()];
        buffer.get(result);
        return result;
    }
}
