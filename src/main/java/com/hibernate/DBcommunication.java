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
	
	public static LAstudent getStudentById(int stid) {
		Session session = factory.openSession();
		LAstudent st = session.get(LAstudent.class, stid);
		session.close();
		return st;
	}
	
	//update function
	
	//delete function
	
	public List<LAstudent> getAllStudents() {
		List<LAstudent> studentList;
		try{
			Session session = factory.openSession();
			CriteriaQuery<LAstudent> cq = session.getCriteriaBuilder().createQuery(LAstudent.class);
			cq.from(LAstudent.class);
			studentList = session.createQuery(cq).getResultList();
			session.close();
			/*for(LAstudent student: studentList) {
				System.out.println(student);
			}*/
		}catch (Exception e) {
			studentList = null;
		}
		return studentList;
	}
	
	/*@SuppressWarnings("unchecked")
	public List<LAstudent> getAllStudents(){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      List<LAstudent> studentList = null;
	      try{
	         tx = session.beginTransaction();
	         studentList = session.createQuery("FROM student").list();
	         for (Iterator iterator1 = employees.iterator(); iterator1.hasNext();){
	            Employee employee = (Employee) iterator1.next(); 
	         }
	         tx.commit();
	      }catch (Exception e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return studentList;
	   }*/
	
	/*public List<LAstudent> getAllStudents() {
		Session session = factory.openSession();
	    return session.createQuery("SELECT a FROM student a", LAstudent.class).getResultList();
	}*/
	
	/*@SuppressWarnings("unchecked")
	public List<LAstudent> getAllStudents() {

        Transaction transaction = null;
        List<LAstudent> studentList = null;
        try (Session session = factory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object

            studentList = session.createQuery("from student").getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return studentList;
    }*/
	
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
