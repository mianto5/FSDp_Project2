package com.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class LAteacher implements Comparable<LAteacher>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tid;
	private String fname;
	private String lname;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "tid")
	private List<LAassign> assignments;
	
	public LAteacher() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LAteacher(String fname, String lname) {
		super();
		this.fname = fname;
		this.lname = lname;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
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
	public List<LAassign> getAssignments() {
		return assignments;
	}
	public void setAssignments(List<LAassign> assignments) {
		this.assignments = assignments;
	}
	@Override
	public String toString() {
		return "LAteacher [tid=" + tid + ", fname=" + fname + ", lname=" + lname + "]";
	}
	@Override
	public int compareTo(LAteacher o) {
		// TODO Auto-generated method stub
		return getLname().toLowerCase().compareTo(o.getLname().toLowerCase());
	}
}
