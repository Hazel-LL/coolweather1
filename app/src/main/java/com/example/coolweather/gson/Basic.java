package com.example.coolweather.gson;

import com.google.gson.annotations.SerializedName;

//@Serializedname注解的方式来让JSON字段和Java字段之间建立映射关系
public class Basic {
    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update {
        @SerializedName("loc")
        public String updateTime;//天气更新时间
    }
}
