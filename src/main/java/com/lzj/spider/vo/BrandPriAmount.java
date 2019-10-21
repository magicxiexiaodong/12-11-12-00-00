package com.lzj.spider.vo;

import java.io.Serializable;

public class BrandPriAmount implements Serializable {
    private String pictureUrl;
    private String title;
    private double payAmt;

    public String getPictureUrl() {
        return this.pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPayAmt() {
        return this.payAmt;
    }

    public void setPayAmt(double payAmt) {
        this.payAmt = payAmt;
    }
}