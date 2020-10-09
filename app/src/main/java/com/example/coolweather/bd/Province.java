package com.example.coolweather.bd;

import org.litepal.crud.DataSupport;

//LitePal中的每一个实体类都必须要继承自DataSupport类的

public class Province extends DataSupport {

    private int id; //每个实体类都有的字段

    private String provinceName;//记录省份名字

    private int provinceCode;//记录省的代号

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }
}
