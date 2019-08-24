package com.zx.demo.controller;

import org.springframework.stereotype.Component;


import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;


/**
 *  * socket连接
 * <p>
 *  
 */

@Component
@ServerEndpoint("/websocket/{shopId}")//此注解相当于设置访问URL

public class WebSocket {
    private Session session;
    private static CopyOnWriteArraySet<WebSocket> webSockets =new CopyOnWriteArraySet<>();
    private static Map<String,Session> sessionPool = new HashMap<String,Session>();
    @OnOpen
    public void onOpen(Session session,@PathParam(value="shopId")String shopId){
        this.session=session;
        webSockets.add(this);
        sessionPool.put(shopId, session);
        System.out.println("【websockect消息】有新的连接，总数为"+webSockets.size());
    }
    @OnClose
    public void onClose(){
        webSockets.remove(this);
        System.out.println("【websockect消息】断开连接，总数为"+webSockets.size());
    }
    //发送消息
    @OnMessage
    public void onMessage(String message) throws IOException {
        System.out.println("【websocket消息】收到客户端消息:"+message);
       // sendAllMessage(message);
        this.session.getBasicRemote().sendText(message);
    }
    //广播消息
    public static void sendAllMessage(String message){
        System.err.println("进来没"+message);
        for(WebSocket webSocket:webSockets){
            System.out.println("【websocket消息】广播消息:"+message);
            try {
                webSocket.onMessage(message);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }
    //单点消息
    public static void sendOneMessage(String shopId,String message){
        System.out.println("aaaaaaaaaaaaaa");
        Session session=sessionPool.get("shopId");
        if(session!=null){
            try {
                session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
//    public static void sendInfo(String Message) throws IOException{
//        sendAllMessage(Message);
//    }
//    public static void main(String[] args) {
//        try {
//            sendInfo("哈哈哈哈哈 觉得封建士大夫");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
