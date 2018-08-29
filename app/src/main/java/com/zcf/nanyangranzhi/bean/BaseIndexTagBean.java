package com.zcf.nanyangranzhi.bean;

import java.io.Serializable;

public class BaseIndexTagBean implements Serializable{

    private String tag;//所属的分类（城市的汉语拼音首字母）

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
