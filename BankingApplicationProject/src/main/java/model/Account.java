package model;

import jakarta.persistence.*;

	@Entity
	@Table(name = "accounts")
	public class Account {

	    @Id
	   
	    @Column(name = "account_number", unique = true, length = 12)
	    private String accountNumber;


	    @ManyToOne
	    @JoinColumn(name = "user_id", nullable = false)
	    private User user;

	    @Column(name = "account_type", nullable = false)
	    private String accountType;

	    @Column(name = "balance", nullable = false)
	    private double balance;
	    
	    

	    public Account() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Account(String accountNumber, String accountType, double balance,User user) {
	        super();
	        this.accountNumber = accountNumber;
	        this.user = user;
	        this.accountType = accountType;
	        this.balance = balance;
	    }

		public String getAccountNumber() {
			return accountNumber;
		}

		public void setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public String getAccountType() {
			return accountType;
		}

		public void setAccountType(String accountType) {
			this.accountType = accountType;
		}

		public double getBalance() {
			return balance;
		}

		public void setBalance(double balance) {
			this.balance = balance;
		}

		
	   

	}

