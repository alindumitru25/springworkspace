package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {
	
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
			
			// get student
			//Student myStudent = session.get(Student.class, 1);
			
			// delete
			//session.delete(myStudent);
			
			// delete with alternate
			session.createQuery("delete from Student where id=2").executeUpdate();
			
			
			// commit
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}
	}

}
