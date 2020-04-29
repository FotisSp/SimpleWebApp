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

	/**
	 * Opens a session/transaction with the SQL database to register a user and commits the changes.
	 * 
	 * @param 	u	User Object with the registration data .
	 * @return		True if registration was successful False otherwise.
	 */
	public boolean create(User u) {
		boolean result = true;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.persist(u);
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
	
	/**
	 * Opens a session/transaction with the SQL database to update an already registered user.
	 * 
	 * @param 	u	User Object with the updated data. 
	 * @return		True if update was successful False otherwise.
	 */
	public boolean update(User u) { 
		boolean result = true;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.merge(u);
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
	
	/**
	 * Opens a session/transaction with the SQL database and retrieves 
	 * a List with all the registered users.
	 * 
	 * @param 	query	A native SQL Query to retrieve the users.
	 * @return			A List of Objects that contain the users info.
	 */
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
	
	/**
	 * Opens a session/transaction with the SQL database and retrieves 
	 * a single registered user.
	 * 
	 * @param 	query	A native SQL Query to retrieve the user info.
	 * @return			An Object which contains the user data.
	 */
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
	
	/**
	 * Opens a session/transaction with the SQL database and 
	 * deletes the selected user.
	 *
	 * @param 	query	A native SQL Query to delete the selected user.
	 */
	@SuppressWarnings("rawtypes")
	public void deleteUser(String query) {
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