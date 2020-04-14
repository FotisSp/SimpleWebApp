package com.apache.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.apache.entities.User;

public class UserModel {

	protected SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public boolean create(User u) {
		boolean result = true;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(u);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			result = false;
		} finally {
			session.close();
		}
		return result;
	}

}