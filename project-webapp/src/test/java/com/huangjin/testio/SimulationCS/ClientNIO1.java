package com.huangjin.testio.SimulationCS;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * Created by huang on 2017-5-23.
 */
public class ClientNIO1 {
    public static void main(String[] args) {
        client();
    }

    public static void client() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("localhost", 8080));

            if(socketChannel.finishConnect()) {
                int i=0;
                while(true) {
                    TimeUnit.SECONDS.sleep(2);
                    String info = "I'm " + i++ + "-th information from client1";
                    System.out.println(info);
                    buffer.clear();
                    buffer.put(info.getBytes());
                    buffer.flip();
                    while(buffer.hasRemaining()) {
                        System.out.println(buffer);
                        socketChannel.write(buffer);
                    }
                }
            }
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if(socketChannel!=null) {
                    socketChannel.close();
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
