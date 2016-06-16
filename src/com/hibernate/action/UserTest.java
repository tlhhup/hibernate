package com.hibernate.action;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hibernate.entity.Role;
import com.hibernate.entity.User;

public class UserTest {

	private SessionFactory sessionFactory;

	@Before
	public void before() {
		Configuration configuration = new Configuration();
		sessionFactory = configuration.configure().buildSessionFactory();
	}

	@Test
	public void saveRole() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Role role = new Role();
		role.setName("超级管理员");
		session.save(role);
		tx.commit();
		session.close();
	}

	@Test
	public void deleteRole() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Role role = new Role();
		role.setId(1);
		session.delete(role);
		tx.commit();
		session.close();
	}

	@Test
	public void saveUser() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Role role = new Role();
		role.setName("普通管理员");
		session.save(role);
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);
		User user = new User();
		user.setName("张三");
		user.setAddress("成都");

		user.setRoles(roles);

		session.save(user);
		tx.commit();
		session.close();
	}

	@Test
	public void firstCache(){
		Session session = sessionFactory.openSession();
		session.get(User.class, 1);
		session.get(User.class, 1);
		session.close();
	}
	
	@Test
	public void secendQueryCache(){
		Session session1 = sessionFactory.openSession();
		String hql="from User";
		Query query = session1.createQuery(hql);
		query.setCacheable(true);
		query.list();
		session1.close();
		
		//清除缓存
		sessionFactory.evict(User.class);
		
		Session session2 = sessionFactory.openSession();
		query = session2.createQuery(hql);
		query.setCacheable(true);
		query.list();
		session2.close();
	}
	
	@Test
	public void secendCache(){
		Session session1 = sessionFactory.openSession();
		session1.get(User.class, 1);
		session1.close();
		
		Session session2 = sessionFactory.openSession();
		session2.get(User.class, 1);
		session2.close();
	}

	@After
	public void after() {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}

}
