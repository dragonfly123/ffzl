package org.dragonfei.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.rmi.server.ExportException;

/**
 * Created by longfei on 16-7-3.
 */
public class ReadCompletuibHandler implements CompletionHandler<Integer,ByteBuffer> {

    private AsynchronousSocketChannel channel;

    public ReadCompletuibHandler(AsynchronousSocketChannel channel){
        if(this.channel == null){
            this.channel = channel;
        }
    }
    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        attachment.flip();
        byte[] body = new byte[attachment.remaining()];
        attachment.get(body);
        try{
            String req = new String(body,"UTF-8");
            System.out.println("The time server receive order :"+req);
            String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(req)?new java.util.Date(System.currentTimeMillis()).toString():"BAD ORDER";
            doWrite(currentTime);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try{
            this.channel.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void doWrite(String currentTime){
        if(currentTime !=  null && currentTime.trim().length() > 0){
            byte[] bytes = currentTime.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    if(attachment.hasRemaining()){
                        channel.write(attachment,attachment,this);
                    }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    try {
                        channel.close();
                    } catch (Exception e){

                    }
                }
            });
        }
    }
}
