package com.huangjin.testio;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
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
    public void testChannel1() {
        try {
            RandomAccessFile aFile = new RandomAccessFile("C:\\Users\\huang\\Desktop\\aaa.txt", "rw");
            FileChannel inChannel = aFile.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(48);

            int bytesRead = inChannel.read(buf);
            while (bytesRead != -1) {
                buf.flip();

                while(buf.hasRemaining()){
                    System.out.print((char) buf.get());
                }

                buf.clear(); //如果不clear，则buf一直是满的，写不进去东西，造成channel里面东西读不出来
                bytesRead = inChannel.read(buf);
            }

            //buf.flip();
            buf.put((byte)83);
            buf.put((byte)85);

            System.out.println("\n");
            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }
            aFile.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testChannel() {
        try {
            RandomAccessFile aFile = new RandomAccessFile("C:\\Users\\huang\\Desktop\\aaa.txt", "rw");

            FileChannel channel = aFile.getChannel();

            //ByteBuffer buffer = ByteBuffer.allocate(10);
            CharBuffer buffer = CharBuffer.allocate(10);

            buffer.put("中过人");
            //int bufferSize = channel.read(buffer);

            buffer.flip();

            while(buffer.hasRemaining()) {
                System.out.print(buffer.get());
            }
            buffer.clear();

        } catch (Exception e) {
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
    public void testSelector1() throws Exception {
        Selector selector = Selector.open();
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_READ);

        while(true) {
            int readyChannels = selector.select();
            if(readyChannels == 0)
                continue;

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

    @Test
    public void testSelector() {

    }

    @Test
    public void testScatter() throws Exception {
        ByteBuffer header = ByteBuffer.allocate(10);
        ByteBuffer body = ByteBuffer.allocate(100);

        ByteBuffer[] bufferArray = {header, body};

        RandomAccessFile aFile = new RandomAccessFile("C:\\Users\\huang\\Desktop\\aaa.txt", "rw");

        FileChannel channel = aFile.getChannel();

        channel.read(bufferArray);

        header.flip();
        body.flip();

        while(header.hasRemaining()) {
            //System.out.print((char)header.get());
            channel.write(header);
        }

        System.out.println("\n");
        while(body.hasRemaining()) {
            //System.out.print((char)body.get());
            channel.write(body);
        }
        int [][] array = null;
    }


    @Test
    public void testSocketChannel() throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("www.baidu.com", 80));
        socketChannel.configureBlocking(false);

        ByteBuffer buffer = ByteBuffer.allocate(100);
        int bytesRead = socketChannel.read(buffer);

        buffer.flip();

        while(buffer.hasRemaining()) {
            System.out.println(bytesRead);
            System.out.println((char)buffer.get());
        }

        socketChannel.close();
    }

}
