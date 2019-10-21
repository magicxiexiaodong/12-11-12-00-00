package com.lzj.spider.vo;

import java.io.Serializable;
import java.util.List;

public class DataWarInfo implements Serializable {
    private static final long serialVersionUID = -8992770547773822319L;
    private String updateTime;
    private List<BrandPriAmount> bpas;

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<BrandPriAmount> getBpas() {
        return this.bpas;
    }

    public void setBpas(List<BrandPriAmount> bpas) {
        this.bpas = bpas;
    }
}