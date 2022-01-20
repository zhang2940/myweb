package com.example.myweb.config;

public class ThreadTest implements Runnable{
    @Override
    public void run() {
        System.out.println("收到消息");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        Thread thread = new Thread(threadTest);
        for (int i = 0; i < 20; i++) {
            thread.run();
        }

    }
}
