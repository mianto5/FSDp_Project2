package com.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class LAstudent implements Comparable<LAstudent>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stid;
	private String fname;
	private String lname;
	private String cid;
	
	public LAstudent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LAstudent(String fname, String lname, String cid) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.cid = cid;
	}
	public int getStid() {
		return stid;
	}
	public void setStid(int stid) {
		this.stid = stid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	@Override
	public String toString() {
		return "LAstudent [stid=" + stid + ", fname=" + fname + ", lname=" + lname + ", cid=" + cid + "]";
	}
	@Override
	public int compareTo(LAstudent o) {
		// TODO Auto-generated method stub
		return getLname().toLowerCase().compareTo(o.getLname().toLowerCase());
	}
}
