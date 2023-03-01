package com.hibernate;

//import java.util.List;

//import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.bean.LAassign;
import com.bean.LAclass;
import com.bean.LAstudent;
import com.bean.LAsubject;
import com.bean.LAteacher;

public class Test {

	private static SessionFactory factory = HibernateConfig.getSessionFactory();
	
	public static void main(String[] args) {
		System.out.println("config loaded");
		/*LAclass c1 = new LAclass();
		c1.setCname("B1 Class");
		c1.setCid("b1");
		
		System.out.println(addClass(c1));*/
		
		LAstudent st1 = new LAstudent();
		st1.setFname("Barry");
		st1.setLname("Allen");
		st1.setCid("b1");
		
		System.out.println(addStudent(st1));
		
		LAsubject sb1 = new LAsubject();
		sb1.setSname("Biology");
		addSubject(sb1);
		
		/*LAteacher t1 = new LAteacher();
		t1.setFname("Bruce");
		t1.setLname("Banner");
		System.out.println(addTeacher(t1));*/
		
		LAassign a1 = new LAassign();
		a1.setCid("a1");
		a1.setSbid(2);
		a1.setTid(1);
		addAssignment(a1);

	}

	public static int addAssignment(LAassign a) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		int aid = (Integer) session.save(a);
		transaction.commit();
		session.close();
		return aid;
	}
	
	public static String addClass(LAclass c) { // lze udělat method overriding/overloading??
		Session session = factory.openSession(); // že bude jen jedna metoda "add", která bude přijímat různé objekty
		Transaction transaction = session.beginTransaction();
		String cid = (String) session.save(c);
		transaction.commit();
		session.close();
		return cid;
	}
	
	public static int addStudent(LAstudent st) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		int stid = (Integer) session.save(st);
		transaction.commit();
		session.close();
		return stid;
	}
	
	public static int addSubject(LAsubject sb) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		int sbid = (Integer) session.save(sb);
		transaction.commit();
		session.close();
		return sbid;
	}
	
	public static int addTeacher(LAteacher t) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		int tid = (Integer) session.save(t);
		transaction.commit();
		session.close();
		return tid;
	}
	
	/*public static Dog getDogById(int id)
	{
		Session session = factory.openSession();
		Dog dog = session.get(Dog.class, id);
		session.close();
		return dog;
	}
	
	public static void updateDog(Dog dog) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(dog);
		transaction.commit();
		session.close();

	}
	
	public static void removeDog(Dog dog) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(dog);
		transaction.commit();
		session.close();

	}
	
	public static List<Dog> getAllDogs()
	{
		Session session = factory.openSession();
		CriteriaQuery<Dog> critQuery = session.getCriteriaBuilder().createQuery(Dog.class);
		critQuery.from(Dog.class);
		List<Dog> dogList = session.createQuery(critQuery).getResultList();
		return dogList;
	}*/
}