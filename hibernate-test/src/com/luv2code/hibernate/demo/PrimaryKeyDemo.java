package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// save object
			
			// create 3 student objects
			Student tempStudent1 = new Student("Alin1", "Vasile", "alin.vasile@gmail.com");
			Student tempStudent2 = new Student("Alin2", "Vasile", "alin.vasile@gmail.com");
			Student tempStudent3 = new Student("Alin3", "Vasile", "alin.vasile@gmail.com");
			
			// start transaction
			session.beginTransaction();
			
			// save student object
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			
			// commit transaction
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}

}
