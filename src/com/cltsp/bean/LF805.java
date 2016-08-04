package com.cltsp.bean;

/**
 * Created by leacher on 2016/7/23.
 */
public class LF805 {
    /*JavaBean设计原则
    * 1.公有类
    * 2.属性私有
    * 3.无参公有构造方法
    * 4.getter,setter方法*/

    private String minBp; //低压
    private String maxBp; //高压
    private String Hr;//心率
    private String datetime; //日期时间
    private String macAddress; //mac地址
    private String SN; //产品SN

    public LF805() {
    }

    public String getMinBp() {
        return minBp;
    }

    public String getMaxBp() {
        return maxBp;
    }

    public String getHr() {
        return Hr;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public String getSN() {
        return SN;
    }

    public void setMinBp(String minBp) {
        this.minBp = minBp;
    }

    public void setMaxBp(String maxBp) {
        this.maxBp = maxBp;
    }

    public void setHr(String hr) {
        Hr = hr;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public void setSN(String SN) {
        this.SN = SN;
    }
}
