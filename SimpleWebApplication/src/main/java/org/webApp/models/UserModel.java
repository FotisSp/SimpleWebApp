package org.webApp.models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.webApp.entities.User;

/**
 * @author Fotis Spanopoulos
 *
 */
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
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getUsers(String query) {
		Session session = null;
		Transaction transaction = null;
		List<Object[]> users = new ArrayList<Object[]>();
		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			users = session.createNativeQuery(query).list();
			
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return users;
	}
	
	public Object[] getSingleUser(String query) {
		Object[] user = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			user = (Object[]) session.createNativeQuery(query).getSingleResult();
			
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return user;
	}
	
	@SuppressWarnings("rawtypes")
	public void deleteUser(String query) {		// TODO maybe change to return something
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			NativeQuery q = session.createNativeQuery(query);
			q.executeUpdate();
			
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
	}
}