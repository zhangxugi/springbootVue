package com.zx.demo.pojo;
/**
 * Created with Intellij IDEA.
 * User: wesley
 * Date: 2015/4/16
 * Time: 18:00
 * 市
 */
public class City {
    private Integer id;

    private String cityid;

    private String city;

    private String father;//对应省的provinceid

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid == null ? null : cityid.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father == null ? null : father.trim();
    }
}