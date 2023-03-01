package com.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "assign")
public class LAassign {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int aid;
	private String cid;
	private int sbid;
	private int tid;
	
	public LAassign() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LAassign(String cid, int sbid, int tid) {
		super();
		this.cid = cid;
		this.sbid = sbid;
		this.tid = tid;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public int getSbid() {
		return sbid;
	}
	public void setSbid(int sbid) {
		this.sbid = sbid;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	@Override
	public String toString() {
		return "LAassign [aid=" + aid + ", cid=" + cid + ", sbid=" + sbid + ", tid=" + tid + "]";
	}
}
