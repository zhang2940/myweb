package com.example.myweb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint("/webSocket/{sid}")
@Component
public class WebSocket {

    @Resource
    private RedisUtil redisUtil;

    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static AtomicInteger onlineNum = new AtomicInteger();

    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象。
    private static ConcurrentHashMap<String, Session> sessionPools = new ConcurrentHashMap<>();

//    发送消息
    public void sendMsg(Session session, String message) throws IOException {
        if (session!=null){
            synchronized (session){
                session.getBasicRemote().sendText(message);
            }
        }
    }

//    给指定用户发送消息
    public void sendMsgToUser(String username,String message) throws IOException {
        Session session = sessionPools.get(username);
        if (session!=null){
            sendMsg(session,message);
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "sid")String username){
        sessionPools.put(username,session);
        addOnlineCount();
        logger.info(username+"连接成功");
    }

    @OnClose
    public void onClose(@PathParam(value = "sid")String username){
        sessionPools.remove(username);
        onlineNum.decrementAndGet();
        logger.info(username+"断开连接");
    }

    @OnMessage
    public void onMessage(String message) throws IOException{
        message = "客户端：" + message + ",已收到";
        for (Session session: sessionPools.values()) {
            try {
                sendMsg(session, message);
            } catch(Exception e){
                e.printStackTrace();
                continue;
            }
        }
    }


    public static void addOnlineCount(){
        onlineNum.incrementAndGet();
    }
}
