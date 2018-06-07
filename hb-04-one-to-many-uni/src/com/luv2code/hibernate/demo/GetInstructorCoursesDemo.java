package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class GetInstructorCoursesDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// start transaction
			session.beginTransaction();
			
			int id = 1;
			Instructor tempInstructor = session.get(Instructor.class, id);
			
			if (tempInstructor != null) {
				System.out.println("Instructor: " + tempInstructor);
				
				System.out.println("Courses: " + tempInstructor.getCourses());
			}
			
			// commit transaction
			session.getTransaction().commit();
		} catch(Exception exc) {
			exc.printStackTrace();
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
