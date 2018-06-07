package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// start transaction
			session.beginTransaction();
			
			int theId = 2;
			InstructorDetail tempInstructoDetail = session.get(InstructorDetail.class, theId);

			// remove associated object before deleting from DB
			// break bi-directional link
			tempInstructoDetail.getInstructor().setInstructorDetail(null);
			
			session.delete(tempInstructoDetail);

			// commit transaction
			session.getTransaction().commit();
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			// handle conn leak
			session.close();
			factory.close();
		}
	}

}
