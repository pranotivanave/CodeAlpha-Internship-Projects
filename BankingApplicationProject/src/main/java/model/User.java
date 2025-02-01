package model;

import java.util.Set;


import jakarta.persistence.*;
@Entity
	@Table(name = "users")
	public class User {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "user_id")
	    private int userId;

	    @Column(name = "name", nullable = false)
	    private String name;

	    @Column(name = "email", nullable = false, unique = true)
	    private String email;

	    public User(String name, String email) {
	        this.name = name;
	        this.email = email;
	    }


		public User() {
			super();
			// TODO Auto-generated constructor stub
		}


		@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private Set<Account> accounts;

	    // Getters and Setters
	    public int getUserId() {
	        return userId;
	    }

	    public void setUserId(int userId) {
	        this.userId = userId;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public Set<Account> getAccounts() {
	        return accounts;
	    }

	    public void setAccounts(Set<Account> accounts) {
	        this.accounts = accounts;
	    }
	}


