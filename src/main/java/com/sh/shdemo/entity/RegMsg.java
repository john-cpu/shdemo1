package com.sh.shdemo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RegMsg {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cph;
    private String username;
    private String color;
    private String fdjID;
    private String brand;
    private String jjID;
    private String cxh;
    private String rtime;
    private String ctype;
    private String firstDate;
    private String imgPath;

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
}
