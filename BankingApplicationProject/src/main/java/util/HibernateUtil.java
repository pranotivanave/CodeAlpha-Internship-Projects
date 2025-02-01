package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Account;
import model.User;

public class HibernateUtil {
	
	private static SessionFactory factory;

    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).addAnnotatedClass(Account.class).buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }

    public static void closeFactory() {
        factory.close();
    }
}


