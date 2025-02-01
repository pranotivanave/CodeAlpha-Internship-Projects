package dao;


	
	import org.hibernate.Session;
	import org.hibernate.Transaction;

import model.Account;

	public class AccountDao {

	    public void createAccount(Session session, Account account) {
	        Transaction transaction = session.beginTransaction();
	        session.save(account); // Save the account to the database
	        transaction.commit();
	    }

	    public Account getAccountByNumber(Session session, String accountNumber) {
	        return session.createQuery("FROM Account WHERE accountNumber = :accountNumber", Account.class)
	                      .setParameter("accountNumber", accountNumber)
	                      .uniqueResult();
	    }


	    
	    public void updateAccount(Session session, Account account) {
	        Transaction transaction = session.beginTransaction();
	        session.update(account); // Update account details
	        transaction.commit();
	    }
	}



