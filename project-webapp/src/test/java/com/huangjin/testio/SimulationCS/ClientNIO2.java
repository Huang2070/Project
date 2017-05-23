package com.huangjin.testio.SimulationCS;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by huang on 2017-5-23.
 */
public class ClientNIO2 {
    public static void main(String[] args) {
        client();
    }

    public static void client() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = null;
        BufferedInputStream in = new BufferedInputStream(System.in);

        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("localhost", 8080));


            if(socketChannel.finishConnect()) {
                while(true) {
                    byte[] bytes = new byte[10];
                    int bytesReads = in.read(bytes);

                    String info = "";
                    while(bytesReads != -1) {
                        info = new String(bytes).substring(0, bytesReads);
                        if((info != null && !info.equals(""))) {
                            if(info.substring(0, 4).equals("exit")) {
                                System.exit(0);
                            } else {
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

                        bytesReads = in.read(bytes);
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(socketChannel!=null) {
                    socketChannel.close();
                    in.close();
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
