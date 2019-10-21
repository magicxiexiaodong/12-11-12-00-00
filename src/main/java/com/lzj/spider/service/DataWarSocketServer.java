package com.lzj.spider.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
public class DataWarSocketServer {
    private static Logger log = LoggerFactory.getLogger(DataWarSocketServer.class);
    private static int onlineCount = 0;
    public static CopyOnWriteArraySet<DataWarSocketServer> dataWarSet = new CopyOnWriteArraySet();
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        dataWarSet.add(this);
        addOnlineCount();
        log.info("有新连接加入！当前在线人数为" + getOnlineCount());
        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            log.error("websocket IO异常");
        }
    }

    @OnClose
    public void onClose() {
        dataWarSet.remove(this);
        subOnlineCount();
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("来自客户端的消息:" + message);

        for (DataWarSocketServer item : dataWarSet)
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static void sendInfo(String message) throws IOException {
        log.info(message);
        for (DataWarSocketServer item : dataWarSet)
            try {
                item.sendMessage(message);
            } catch (IOException e) {
            }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        onlineCount += 1;
    }

    public static synchronized void subOnlineCount() {
        onlineCount -= 1;
    }
}