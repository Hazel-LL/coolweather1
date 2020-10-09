package com.example.coolweather.bd;

import org.litepal.crud.DataSupport;

public class County extends DataSupport {

    private int id;

    private String countyName;//记录区县名称

    private String weatherId;//记录该区县的天气信息

    private int cityId;//该区县所属的市级代码

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

}
