package com.lzj.spider.controller;

import com.lzj.spider.vo.DataWarInfo;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class DataWarController {

    @MessageMapping({"/refresh"})
    @SendTo({"/datawar-room/list"})
    public DataWarInfo refreshDataWar() {
        DataWarInfo info = new DataWarInfo();
        info.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return info;
    }
}