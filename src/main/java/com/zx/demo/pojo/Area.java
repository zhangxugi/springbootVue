package com.zx.demo.pojo;
/**
 * Created with Intellij IDEA.
 * User: wesley
 * Date: 2015/4/16
 * Time: 18:00
 * 县
 */
public class Area {
    private Integer id;

    private String areaid;

    private String area;

    private String father;//对应市或区的cityid

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid == null ? null : areaid.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father == null ? null : father.trim();
    }
}