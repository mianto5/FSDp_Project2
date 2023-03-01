package com.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "class")
public class LAclass {
	
	@Id
	private String cid;
	private String cname;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cid")
	private List<LAstudent> students;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cid")
	private List<LAassign> assignements;
	
	public LAclass() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LAclass(String cid, String cname) {
		super();
		this.cid = cid;
		this.cname = cname;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public List<LAstudent> getStudents() {
		return students;
	}
	public void setStudents(List<LAstudent> students) {
		this.students = students;
	}
	/*public List<LAassign> getAssignements() {
		return assignements;
	}
	public void setAssignements(List<LAassign> assignements) {
		this.assignements = assignements;
	}*/
	@Override
	public String toString() {
		return "LAclass [cid=" + cid + ", cname=" + cname + "]";
	}
}
