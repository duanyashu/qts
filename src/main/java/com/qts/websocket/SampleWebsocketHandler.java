package com.qts.websocket;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 采样操作台处理器
 * @author: duanyashu
 * @time: 2020-07-03 14:18
 */
public class SampleWebsocketHandler extends TextWebSocketHandler {

    public final static String USER_KEY="userId";

    private static Map<String,WebSocketSession> userMap = new HashMap<>();

    /**
     * 链接成功
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        //获取用户id
        String userId   = (String) session.getAttributes().get(USER_KEY);
        userMap.put(userId,session);
        super.afterConnectionEstablished(session);
        System.out.println("连接成功");
    }

    /**
     * 接收到信息
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
    }

    /**
     * websocket发送数据
     * @param userId
     * @param data
     */
    public static void sendByUserId(String userId,String data){
            try {
                WebSocketSession session = userMap.get(userId);
                if (session!=null){
                    //websocket发送数据的方法
                    session.sendMessage(new TextMessage(data));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

}
