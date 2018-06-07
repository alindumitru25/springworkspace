package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateInstructorDemo {

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
			Instructor tempInstructor =
					new Instructor("Susan", "Public", "sus@luv2code.com");
			InstructorDetail tempInstructorDetail =
					new InstructorDetail("youtube.com", "video");
			
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			// start transaction
			session.beginTransaction();
			
			
			// save both because CASCADE ALL
			session.save(tempInstructor);
			
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
