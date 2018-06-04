package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {
	
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
			int studentId = 1;
			// start transaction
			session.beginTransaction();
			
			Student myStudent = session.get(Student.class, studentId);
			
			myStudent.setFirstName("Jabuba");
			
			// commit
			session.getTransaction().commit();
			
			// start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			session.createQuery("update Student set email='foo@gmail.com'")
					.executeUpdate();
			
			// commit
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}
	}

}
