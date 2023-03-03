package com.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.bean.LAstudent;

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
	
}
