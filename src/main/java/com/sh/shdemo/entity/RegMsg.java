package com.sh.shdemo.entity;

import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Validated
@Entity
public class RegMsg implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "车牌号不能为空")
    private String cph;
    @NotEmpty(message = "用户名不能为空")
    private String username;
    @NotEmpty(message = "车辆颜色不能为空")
    private String color;
    @NotEmpty(message = "发动机id不能为空")
    private String fdjID;
    @NotEmpty(message = "品牌不能为空")
    private String brand;
    @NotEmpty(message = "机架id不能为空")
    private String jjID;
    @NotEmpty(message = "车型号不能为空")
    private String cxh;
    @NotEmpty(message = "注册时间不能为空")
    private String rtime;
    @NotEmpty(message = "车种类不能为空")
    private String ctype;
    @NotEmpty(message = "第一次不能为空")
    private String firstDate;
    @NotEmpty(message = "图片不能为空")
    private String imgPath;
    private String state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCph() {
        return cph;
    }

    public void setCph(String cph) {
        this.cph = cph;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFdjID() {
        return fdjID;
    }

    public void setFdjID(String fdjID) {
        this.fdjID = fdjID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getJjID() {
        return jjID;
    }

    public void setJjID(String jjID) {
        this.jjID = jjID;
    }

    public String getCxh() {
        return cxh;
    }

    public void setCxh(String cxh) {
        this.cxh = cxh;
    }

    public String getRtime() {
        return rtime;
    }

    public void setRtime(String rtime) {
        this.rtime = rtime;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(String firstDate) {
        this.firstDate = firstDate;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
