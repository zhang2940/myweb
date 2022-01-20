package com.example.myweb.config;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] args) throws IOException {
        ByteBuffer byteBuffer=ByteBuffer.allocate(16);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8080));
        List<SocketChannel> lists=new ArrayList<>();
//        接收请求
        while (true){
            SocketChannel accept = serverSocketChannel.accept();
            lists.add(accept);
            System.out.println(lists.size());

            accept.read(byteBuffer);
                byteBuffer.flip();
                while(byteBuffer.hasRemaining()){
                    System.out.println((char)byteBuffer.get());
                }
                byteBuffer.clear();


        }
    }
}
