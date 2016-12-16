package com.huangjin.testio;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by huang on 2016-11-22.
 */
public class TestNIO {
    public static void main(String[] args) {

    }

    @Test
    public void testBuffer() {
        CharBuffer buffer = CharBuffer.allocate(10);

        System.out.println("初始状态");
        System.out.println("position:" + buffer.position());
        System.out.println("limit:" + buffer.limit());
        System.out.println("capacity:" + buffer.capacity());

        buffer.put("a");
        buffer.put("b");
        buffer.put("c");
        System.out.println("\n加入三个元素后");
        System.out.println("position:" + buffer.position());
        System.out.println("limit:" + buffer.limit());
        System.out.println("capacity:" + buffer.capacity());

        buffer.flip();
        System.out.println("\n执行flip()方法");
        System.out.println("position:" + buffer.position());
        System.out.println("limit:" + buffer.limit());
        System.out.println("capacity:" + buffer.capacity());

        System.out.println("\n取出第一个元素");
        System.out.println("第一个元素：" + buffer.get());
        System.out.println("position:" + buffer.position());
        System.out.println("limit:" + buffer.limit());
        System.out.println("capacity:" + buffer.capacity());

        buffer.clear();
        System.out.println("\n执行flip()方法");
        System.out.println("position:" + buffer.position());
        System.out.println("limit:" + buffer.limit());
        System.out.println("capacity:" + buffer.capacity());
    }

    @Test
    public void testChannel() {
        try {
            RandomAccessFile aFile = new RandomAccessFile("C:\\Users\\huang\\Desktop\\aaa.txt", "rw");
            FileChannel inChannel = aFile.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(48);

            int bytesRead = inChannel.read(buf);
            while (bytesRead != -1) {

                System.out.println("Read " + bytesRead);
                buf.flip();

                while(buf.hasRemaining()){
                    System.out.print((char) buf.get());
                }

                buf.clear();
                bytesRead = inChannel.read(buf);
            }
            aFile.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }


    @Test
    public void testTransferFrom() {
        try {
            RandomAccessFile fromFile = new RandomAccessFile("C:\\Users\\huang\\Desktop\\aaa.txt", "rw");
            FileChannel fromChannel = fromFile.getChannel();

            RandomAccessFile toFile = new RandomAccessFile("C:\\Users\\huang\\Desktop\\bbb.txt", "rw");
            FileChannel toChannel = toFile.getChannel();

            long postion = 0;
            long count = fromChannel.size();

            toChannel.transferFrom(fromChannel, postion, count);

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testSelector() throws Exception {
        Selector selector = Selector.open();
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_READ);

        while(true) {
            int readyChannels = selector.select();
            if(readyChannels == 0) continue;
            Set selectedKeys = selector.selectedKeys();
            Iterator keyIterator = selectedKeys.iterator();
            while(keyIterator.hasNext()) {
                SelectionKey key = (SelectionKey)keyIterator.next();
                if(key.isAcceptable()) {
                    // a connection was accepted by a ServerSocketChannel.
                } else if (key.isConnectable()) {
                    // a connection was established with a remote server.
                } else if (key.isReadable()) {
                    // a channel is ready for reading
                } else if (key.isWritable()) {
                    // a channel is ready for writing
                }
                keyIterator.remove();
            }
        }
    }

}
