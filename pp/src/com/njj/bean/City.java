package com.njj.bean;

import java.io.Serializable;
import java.util.List;

/**
 * city
 * @author 
 */
public class City implements Serializable {
    private Integer cid;

    private String cname;

    private List<Diqu> diquList;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public List<Diqu> getDiquList() {
        return diquList;
    }

    public void setDiquList(List<Diqu> diquList) {
        this.diquList = diquList;
    }

    @Override
    public String toString() {
        return "City{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", diquList=" + diquList +
                '}';
    }
}