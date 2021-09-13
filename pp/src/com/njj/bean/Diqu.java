package com.njj.bean;

import java.io.Serializable;

/**
 * diqu
 * @author 
 */
public class Diqu implements Serializable {
    private Integer qid;

    private Integer cid;

    private String qname;

    private static final long serialVersionUID = 1L;

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Diqu other = (Diqu) that;
        return (this.getQid() == null ? other.getQid() == null : this.getQid().equals(other.getQid()))
            && (this.getCid() == null ? other.getCid() == null : this.getCid().equals(other.getCid()))
            && (this.getQname() == null ? other.getQname() == null : this.getQname().equals(other.getQname()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getQid() == null) ? 0 : getQid().hashCode());
        result = prime * result + ((getCid() == null) ? 0 : getCid().hashCode());
        result = prime * result + ((getQname() == null) ? 0 : getQname().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", qid=").append(qid);
        sb.append(", cid=").append(cid);
        sb.append(", qname=").append(qname);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}