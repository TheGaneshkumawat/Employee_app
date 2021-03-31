package com.example.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.cruddemo.entity.Employee;


@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	@Autowired
	EntityManager entityManager;
	
	
	@Override
	public List<Employee> findAll() {
		
		Session session = entityManager.unwrap(Session.class);
		
		
		Query<Employee> query = session.createQuery("from Employee",Employee.class);
		
		List<Employee> list = query.getResultList();
		
		return list;
	}
	
	@Override
	public Employee findById(int id) {
		Session session = entityManager.unwrap(Session.class);
		Employee emp = session.get(Employee.class,id);
		return emp;
	}

	@Override
	public void save(Employee employee) {
		Session session = entityManager.unwrap(Session.class);		
		session.saveOrUpdate(employee);
		
	}

	@Override
	public void deleteById(int empId) {		
		Session session = entityManager.unwrap(Session.class);			
		Query query = session.createQuery("delete from Employee where id=:id");
		query.setParameter("id", empId);
		query.executeUpdate();		
		
	}

}
