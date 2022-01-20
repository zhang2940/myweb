package com.example.myweb.config;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel channel=SocketChannel.open();
        channel.connect(new InetSocketAddress("localhost",8080));
        System.out.println("waiting.........");
        Thread.sleep(10000);
        channel.write(Charset.defaultCharset().encode("hello"));

    }
}
