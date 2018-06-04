package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {
	
	public static void displayStudents(List<Student> theStudents) {
		for (Student s : theStudents) {
			System.out.println("Student email " + s.getEmail());
		}
	}

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// save object
			
			// start transaction
			session.beginTransaction();

			// query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			// display students
			displayStudents(theStudents);

			// query students with last name
			List<Student> theStudents2 = session.createQuery("from Student s where s.lastName='Vasile'").getResultList();
			
			// display students2
			displayStudents(theStudents2);
			
			// query with where and or
			List<Student> theStudents3 = session.createQuery("from Student s where s.lastName='Vasile' OR s.firstName='Daffy'")
					.getResultList();
			
			// display students3
			displayStudents(theStudents3);
			
			// query with where and or
			List<Student> theStudents4 = session.createQuery("from Student s where s.email LIKE '%le@gmail.com'")
					.getResultList();
			
			// display students3
			displayStudents(theStudents4);
			
			// commit
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}
	}

}
