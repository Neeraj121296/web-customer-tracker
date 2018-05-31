package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.entity.Customer;

//to handle exceptional translation
@Repository
public class CustomerDAOImpl implements CustomerDAO {

	
	//need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override

	public List<Customer> getCustomers() {
		
		//get the current hibernate session
		Session session=sessionFactory.getCurrentSession();
		
		//create a query
		Query<Customer> theQuery=session
				.createQuery("from Customer order by firstName",
				Customer.class);
		//execute query and get the result list
		List<Customer> customers=theQuery.getResultList();
		
		
		
		//return the results
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		//get current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		//save/update the customer..finally
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {
		//get the current hibernate session
		Session session=sessionFactory.getCurrentSession();
		
		//now retrieve from database using the primary key
		Customer theCustomer=session.get(Customer.class,theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		//get the current hibernate session
		Session session=sessionFactory.getCurrentSession();
		
		//delete the object with primary key
		
		Query theQuery=session.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		theQuery.executeUpdate();
				
		
	}


	
	
}
