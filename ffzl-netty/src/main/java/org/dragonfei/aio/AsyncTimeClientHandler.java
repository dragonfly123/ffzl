package org.dragonfei.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * Created by longfei on 16-7-3.
 */
public class AsyncTimeClientHandler implements CompletionHandler<Void,AsyncTimeClientHandler>,Runnable {

    private AsynchronousSocketChannel channel;
    private String host;
    private int port;
    private CountDownLatch latch;

    public AsyncTimeClientHandler(String host,int port){
        this.host =host;
        this.port = port;
        try {
            channel = AsynchronousSocketChannel.open();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void completed(Void result, AsyncTimeClientHandler attachment) {
        byte[] req = "QUERY TIME ORDER".getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(req.length);
        buffer.put(req);
        buffer.flip();
        channel.write(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                if(attachment.hasRemaining()){
                    channel.write(attachment,attachment,this);
                } else {
                    ByteBuffer readbuffer = ByteBuffer.allocate(1024);
                    channel.read(readbuffer, readbuffer, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer attachment) {
                            attachment.flip();
                            byte[] bytes = new byte[attachment.remaining()];
                            attachment.get(bytes);
                            String body = "";
                            try{
                                body = new String(bytes,"UTF-8");
                                System.out.println("Now is :"+body);
                                latch.countDown();
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {
                            try{
                                channel.close();
                                latch.countDown();
                            } catch (Exception e){

                            }
                        }
                    });
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });
    }

    @Override
    public void failed(Throwable exc, AsyncTimeClientHandler attachment) {
        try{
            channel.close();
            latch.countDown();
        } catch (Exception e){

        }
    }

    @Override
    public void run() {
        latch = new CountDownLatch(1);
        channel.connect(new InetSocketAddress(host,port),this,this);
        try{
            latch.await();
        } catch (Exception e){
            e.printStackTrace();
        }
        try {
            channel.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
