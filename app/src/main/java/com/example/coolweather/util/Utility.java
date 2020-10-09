package com.example.coolweather.util;

import android.text.TextUtils;

import com.example.coolweather.bd.City;
import com.example.coolweather.bd.County;
import com.example.coolweather.bd.Province;
import com.example.coolweather.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {

    /**
     * 解析和处理服务器返回的省级数据
     */
    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                //将所有的省级数据解析出来，并组装成实体类对像
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    //将该实体类对象存入数据库
                    province.save();
                }
                return true;//解析成功
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;//解析失败
    }

    /**
     * 解析和处理服务器返回的市级数据
     */
    public static boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i < allCities.length(); i++) {
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     */
    public static boolean handleCountyResponse(String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++) {
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    //县级天气信息
                    county.setWeatherId(countyObject.getString("weather_id"));
                    //所属的市级代号
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /*
    * 可以看到，我们提供了 hand leprovincesresponse()、 handlecitlesresponse(),
    *  Lecountiesresponse()这3个方法，
    * 分别用于解析和处理服务器返回的省级、市级和县级数据。处理的方式都是类似的，
    * 先使用 Jsonarray和 Jsonobject%将数据解析出来，然后组装成实体类对象，
    * 再调用save()方法将数据存储到数据库当中。由于这里的JSON数据结构比较简单，
    * 我们就不使用GSON来进行解析了。
    * */

    /**
     * 添加一个用于解析天气JSON数据的方法
     * 将返回的JS0N数据解析成 Weather实体类
    * */
    public static Weather handleWeatherResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent, Weather.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}