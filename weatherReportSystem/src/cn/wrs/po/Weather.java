package cn.wrs.po;

import java.util.Date;

public class Weather {
    private Integer id;

    private String weathercond;

    private String temperature;

    private String tips;

    private Date ctime;

    private Integer cityId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWeathercond() {
        return weathercond;
    }

    public void setWeathercond(String weathercond) {
        this.weathercond = weathercond == null ? null : weathercond.trim();
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature == null ? null : temperature.trim();
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips == null ? null : tips.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}