package com.lzj.spider.service;

import com.alibaba.fastjson.JSONObject;
import com.lzj.spider.dao.TaobaoAccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SycmService {
    private final Logger log = LoggerFactory.getLogger(SycmService.class);
    private static String cookie;

    @Autowired
    private TaobaoAccountMapper taobaoAccountMapper;
    private final RestTemplate restTemplate;

    public SycmService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public JSONObject getTotalPriAmount() {
        if ((cookie == null) || (cookie.equals(""))) {
            getCookie();
        }
        String url = "https://sycm.taobao.com/ipoll/datawar/getBranchAmtAndFlow.json?limit=30&token=123456789";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.79 Safari/537.36");
        headers.add("cookie", cookie);
        HttpEntity httpEntity = new HttpEntity(null, headers);
        ResponseEntity result = this.restTemplate.postForEntity(url, httpEntity, JSONObject.class, new Object[0]);
        this.log.info(((JSONObject) result.getBody()).toJSONString());
        return (JSONObject) result.getBody();
    }

    private void getCookie() {
        cookie = this.taobaoAccountMapper.getCookie();

        this.log.info("获取cookie：" + cookie);
    }

    public void sendDingding() {
        String url = "https://oapi.dingtalk.com/robot/send?access_token=65efb328b6e27d0d1576dd828af8cf7ad46ff413fc7af99e027922f667d5a4e7";
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        JSONObject obj1 = new JSONObject();
        JSONObject obj2 = new JSONObject();
        obj1.put("content", "@15372598527 店铺cookie失效了，快给我加上!");
        obj2.put("atMobiles", new String[]{"15372598527"});
        obj2.put("isAtAll", Boolean.valueOf(false));
        JSONObject obj = new JSONObject();
        obj.put("msgtype", "text");
        obj.put("text", obj1);
        obj.put("at", obj2);
        System.out.println(obj.toString());
        HttpEntity httpEntity = new HttpEntity(obj.toJSONString(), headers);
        ResponseEntity result = this.restTemplate.postForEntity(url, httpEntity, JSONObject.class, new Object[0]);
    }
}