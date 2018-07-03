package com.zcf.nanyangranzhi.bean;

public abstract  class BaseIndexPinyinBean extends BaseIndexTagBean implements IIndexTargetInterface {

    private String pyCity;//城市的拼音

    public String getPyCity() {
        return pyCity;
    }

    public void setPyCity(String pyCity) {
        this.pyCity = pyCity;
    }
}
