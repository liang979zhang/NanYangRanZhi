package com.zcf.nanyangranzhi.bean;

import java.io.Serializable;

public abstract  class BaseIndexPinyinBean extends BaseIndexTagBean implements IIndexTargetInterface ,Serializable {

    private String pyCity;//城市的拼音

    public String getPyCity() {
        return pyCity;
    }

    public void setPyCity(String pyCity) {
        this.pyCity = pyCity;
    }
}
