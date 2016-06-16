package com.hibernate.action;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hibernate.entity.Department;
import com.hibernate.entity.Employee;

public class Mony2One {

	private SessionFactory sessionFactory;

	@Before
	public void before(){
		Configuration configuration=new Configuration();
		sessionFactory = configuration.configure().buildSessionFactory();
	}
	
	@After
	public void after(){
		sessionFactory.close();
	}
	
	@Test
	public void save(){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Department department=new Department();
		department.setName("教学部");
		
		Employee employee=new Employee();
		employee.setName("张三");
		employee.setAddress("成都");
		employee.setDepartment(department);
		
		session.save(department);
		session.save(employee);
		
		tx.commit();
		session.close();
	}
	
}
