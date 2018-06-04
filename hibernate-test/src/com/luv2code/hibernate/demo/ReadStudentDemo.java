package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// save object
			
			// create student object
			Student tempStudent = new Student("Daffy", "Duck", "daffy@gmail.com");
			
			// start transaction
			session.beginTransaction();
			
			// save student object
			session.save(tempStudent);
			
			// commit transaction
			session.getTransaction().commit();
			
			// get a new session
			session = factory.getCurrentSession();
			
			// start a transaction
			session.beginTransaction();
			
			// retrieve student based on id
			Student myStudent = session.get(Student.class, tempStudent.getId());
			
			System.out.println("Student " + myStudent.getEmail());
			
			// commit
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}
	}

}
