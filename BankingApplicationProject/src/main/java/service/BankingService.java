package service;

import java.util.Random;
import java.util.UUID;

import org.hibernate.Session;

import dao.AccountDao;
import dao.UserDao;
import model.Account;
import model.User;

public class BankingService {
	
	    private UserDao userDao;
	    private AccountDao accountDao;

	    public BankingService() {
	        this.userDao = new UserDao();
	        this.accountDao = new AccountDao();
	    }

	    public void createUser(Session session, String name, String email) {
	        try {
	            session.beginTransaction();
	            User user = new User(name, email); // Assuming you have a constructor
	            session.save(user);
	            session.getTransaction().commit(); // Commit transaction to persist user
	            System.out.println("User created successfully!");
	        } catch (Exception e) {
	            session.getTransaction().rollback(); // Rollback in case of error
	            e.printStackTrace();
	        }
	    }

	   
	    public void createAccount(Session session, int userId, String accountType, double balance) {
	        try {
	            session.beginTransaction();
	            
	            // Ensure user exists first
	            User user = session.get(User.class, userId);
	            if (user != null) {
	                // Generate a unique 12-digit account number
	                String accountNumber = generateAccountNumber(session);  // Generates account number

	                // Create an account with the unique account number
	                Account account = new Account(accountNumber, accountType, balance, user); // Pass accountNumber here
	                session.save(account); // Save the account with the account number
	                session.getTransaction().commit(); // Commit the transaction
	                System.out.println("Account created successfully!");
	            } else {
	                System.out.println("User not found!");
	                session.getTransaction().rollback();
	            }
	        } catch (Exception e) {
	            session.getTransaction().rollback(); // Rollback in case of error
	            e.printStackTrace();
	        }
	    }



	    private String generateAccountNumber(Session session) {
	        Random random = new Random();
	        String accountNumber;
	        boolean isUnique = false;
	        do {
	            accountNumber = String.format("%012d", 100000000000L + random.nextInt(900000000));
	            Account existingAccount = accountDao.getAccountByNumber(session, accountNumber);
	            if (existingAccount == null) {
	                isUnique = true;
	            }
	        } while (!isUnique);
	        return accountNumber;
	    }




	    public void deposit(Session session, String accountNumber, double amount) {
	        Account account = accountDao.getAccountByNumber(session, accountNumber);
	        if (account != null) {
	            account.setBalance(account.getBalance() + amount);
	            accountDao.updateAccount(session, account);
	            System.out.println("Deposit successful!");
	        } else {
	            System.out.println("Account not found!");
	        }
	    }

	    public void withdraw(Session session, String  accountNumber, double amount) {
	        Account account = accountDao.getAccountByNumber(session, accountNumber);
	        if (account != null) {
	            if (account.getBalance() >= amount) {
	                account.setBalance(account.getBalance() - amount);
	                accountDao.updateAccount(session, account);
	                System.out.println("Withdrawal successful!");
	            } else {
	                System.out.println("Insufficient balance!");
	            }
	        } else {
	            System.out.println("Account not found!");
	        }
	    }

	    public void checkBalance(Session session, String accountNumber) {
	        Account account = accountDao.getAccountByNumber(session, accountNumber);
	        if (account != null) {
	            System.out.println("Current balance: " + account.getBalance());
	        } else {
	            System.out.println("Account not found!");
	        }
	    }
	}


