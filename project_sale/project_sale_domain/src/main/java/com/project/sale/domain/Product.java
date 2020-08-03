package com.project.sale.domain;

import com.project.sale.utils.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 产品信息
 */
public class Product {
    private String id;             //主键
    private String productNum;     //编号  唯一
    private String productName;    //名称
    private String cityName;       //出发城市
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private Date departureTime;    //出发时间，不能在页面上直接展示
    private String departureTimeStr;  //可以在页面直接展示
    private double productPrice;   //产品价格
    private String productDesc;    //产品描述
    private Integer productStatus; //状态 0关闭1开启  不能在页面上直接展示
    private String productStatusStr; //可以在页面上直接展示

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public String getDepartureTimeStr() {
        if(departureTime!=null){
            departureTimeStr= DateUtils.date2String(departureTime,"yyyy-MM-dd HH:mm:ss");
        }
        return departureTimeStr;
    }

    public void setDepartureTimeStr(String departureTimeStr) {
        this.departureTimeStr = departureTimeStr;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductStatusStr() {
        if(productStatus!=null){
            if(productStatus==0)
                productStatusStr="关闭";
            if(productStatus==1)
                productStatusStr="开启";
        }
        return productStatusStr;
    }

    public void setProductStatusStr(String productStatusStr) {
        this.productStatusStr = productStatusStr;
    }
}
