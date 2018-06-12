package com.luv2code.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	// inject hibernate session factory
	@Autowired
	SessionFactory sessionFactory; // sessionFactory bean inside .xml
	
	@Override
	public List<Customer> getCustomers() {
		// get session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// query customers
		Query<Customer> theQuery = currentSession.createQuery(
				"from Customer order by lastName", Customer.class);
		
		// execute
		List<Customer> customers = theQuery.getResultList();
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int customerId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Customer customer = currentSession.get(Customer.class, customerId);
		
		return customer;
	}

	@Override
	public void deleteCustomer(int customerId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query q = currentSession.createQuery("delete from Customer where id=:customerId");
		q.setParameter("customerId", customerId);
		
		q.executeUpdate();
	}

}
