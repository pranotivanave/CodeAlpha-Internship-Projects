package main;

import org.hibernate.Session;

import controller.BankingController;
import util.HibernateUtil;
public class BankingApp {



	

	    public static void main(String[] args) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        
	        // Initialize the controller
	        BankingController bankingController = new BankingController(session);
	        
	        // Show the menu and handle user input
	        bankingController.showMenu();

	        HibernateUtil.closeFactory();
	        session.close();
	    }
	}


