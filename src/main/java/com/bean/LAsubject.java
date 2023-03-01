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
@Table(name = "subject")
public class LAsubject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sbid;
	private String sname;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "sbid")
	private List<LAassign> assignments;
	
	public LAsubject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LAsubject(String sname) {
		super();
		this.sname = sname;
	}
	public int getSbid() {
		return sbid;
	}
	public void setSbid(int sbid) {
		this.sbid = sbid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public List<LAassign> getAssignments() {
		return assignments;
	}
	public void setAssignments(List<LAassign> assignments) {
		this.assignments = assignments;
	}
	@Override
	public String toString() {
		return "LAsubject [sbid=" + sbid + ", sname=" + sname + "]";
	}
}
