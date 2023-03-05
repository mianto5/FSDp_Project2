package com.hibernate;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.bean.LAstudent;
import com.bean.LAteacher;

public class DBcommunication {

	private static SessionFactory factory = HibernateConfig.getSessionFactory();
	
	public boolean addStudent(LAstudent st){
		boolean success = true;
		Session session = factory.openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			session.save(st);
			transaction.commit();
		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			success = false;
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return success;
	}
	
	public LAstudent getStudentById(int stid) {
		Session session = factory.openSession();
		LAstudent st = session.get(LAstudent.class, stid);
		session.close();
		return st;
	}
	
	public boolean updateStudent(LAstudent st) {
		boolean success = true;
		Session session = factory.openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			session.update(st);
			transaction.commit();
		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			success = false;
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return success;
	}
	
	public boolean deleteStudentById(int stid) {
		boolean success = true;
		Session session = factory.openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			LAstudent st = session.get(LAstudent.class, stid);
			if (st!=null)
				session.delete(st);
			transaction.commit();
		}catch (HibernateException e) {
			if (transaction!=null)
				transaction.rollback();
			success = false;
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return success;
	}
	
	public List<LAstudent> getAllStudents() {
		Session session = factory.openSession();
		CriteriaQuery<LAstudent> cq = session.getCriteriaBuilder().createQuery(LAstudent.class);
		cq.from(LAstudent.class);
		List<LAstudent> studentList = session.createQuery(cq).getResultList();
		session.close();
		return studentList;
	}
	
	
	// přepsat!!
	public static List<LAteacher> getAllTeachers() {
		Session session = factory.openSession();
		CriteriaQuery<LAteacher> cq = session.getCriteriaBuilder().createQuery(LAteacher.class);
		cq.from(LAteacher.class);
		List<LAteacher> teacherList = session.createQuery(cq).getResultList();
		for(LAteacher teacher: teacherList) {
			System.out.println(teacher);
		}
		return teacherList;
	}
	
}
