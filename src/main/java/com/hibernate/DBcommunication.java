package com.hibernate;

import java.util.Collections;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.bean.LAassign;
import com.bean.LAclass;
import com.bean.LAstudent;
import com.bean.LAsubject;
import com.bean.LAteacher;

public class DBcommunication {

	private static SessionFactory factory = HibernateConfig.getSessionFactory();
	
	// LAstudent methods
	
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
		Collections.sort(studentList);
		return studentList;
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
	
	// LAclass methods
	
	public boolean addClass(LAclass cl){
		boolean success = true;
		Session session = factory.openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			session.save(cl);
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
	
	public boolean deleteClassById(String cid) {
		boolean success = true;
		Session session = factory.openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			LAclass cl = session.get(LAclass.class, cid);
			if (cl!=null)
				session.delete(cl);
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
	
	public List<LAclass> getAllClasses() {
		Session session = factory.openSession();
		CriteriaQuery<LAclass> cq = session.getCriteriaBuilder().createQuery(LAclass.class);
		cq.from(LAclass.class);
		List<LAclass> classList = session.createQuery(cq).getResultList();
		session.close();
		Collections.sort(classList);
		return classList;
	}
	
	// LAteacher methods
	
	public boolean addTeacher(LAteacher te){
		boolean success = true;
		Session session = factory.openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			session.save(te);
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
	
	public boolean deleteTeacherById(int tid) {
		boolean success = true;
		Session session = factory.openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			LAteacher te = session.get(LAteacher.class, tid);
			if (te!=null)
				session.delete(te);
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
	
	public List<LAteacher> getAllTeachers() {
		Session session = factory.openSession();
		CriteriaQuery<LAteacher> cq = session.getCriteriaBuilder().createQuery(LAteacher.class);
		cq.from(LAteacher.class);
		List<LAteacher> teacherList = session.createQuery(cq).getResultList();
		session.close();
		Collections.sort(teacherList);
		return teacherList;
	}
	
	public String getNameOfTeachById(int tid) {
		Session session = factory.openSession();
		LAteacher t = session.get(LAteacher.class, tid);
		session.close();
		return t.getLname()+" "+t.getFname();
	}
	
	// LAsubject methods
	
	public boolean addSubject(LAsubject sb){
		boolean success = true;
		Session session = factory.openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			session.save(sb);
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
	
	public boolean deleteSubjectById(int sbid) {
		boolean success = true;
		Session session = factory.openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			LAsubject sb = session.get(LAsubject.class, sbid);
			if (sb!=null)
				session.delete(sb);
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
	
	public List<LAsubject> getAllSubjects() {
		Session session = factory.openSession();
		CriteriaQuery<LAsubject> cq = session.getCriteriaBuilder().createQuery(LAsubject.class);
		cq.from(LAsubject.class);
		List<LAsubject> subjectList = session.createQuery(cq).getResultList();
		session.close();
		Collections.sort(subjectList);
		return subjectList;
	}
	
	public String getNameOfSubById(int sbid) {
		Session session = factory.openSession();
		LAsubject sb = session.get(LAsubject.class, sbid);
		session.close();
		return sb.getSname();
	}
	
	// LAassign methods
	
	public List<LAassign> getAllAssignments() {
		Session session = factory.openSession();
		CriteriaQuery<LAassign> cq = session.getCriteriaBuilder().createQuery(LAassign.class);
		cq.from(LAassign.class);
		List<LAassign> assignList = session.createQuery(cq).getResultList();
		session.close();
		return assignList;
	}
	
	public boolean addAssignment(LAassign as){
		boolean success = true;
		Session session = factory.openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			session.save(as);
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
	
	public LAassign getAssignmentById(int aid) {
		Session session = factory.openSession();
		LAassign a = session.get(LAassign.class, aid);
		session.close();
		return a;
	}
	
	public boolean updateAssignment(LAassign a) {
		boolean success = true;
		Session session = factory.openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			session.update(a);
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
	
	public boolean deleteAssignById(int aid) {
		boolean success = true;
		Session session = factory.openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			LAassign as = session.get(LAassign.class, aid);
			if (as!=null)
				session.delete(as);
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
	
}
