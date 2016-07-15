package org.dragonfei.http.pp;

/**
 * Created by longfei on 16-7-9.
 */
public enum MessageType {
    LOGIN_REQ((byte)3),LOGIN_RESP((byte)4),HEARTBEAT_REQ((byte)5),HEARTBEAT_RESP((byte)6);

    private byte value;
    MessageType(byte value){
        this.value = value;
    }

    public byte value(){
        return value;
    }
}
