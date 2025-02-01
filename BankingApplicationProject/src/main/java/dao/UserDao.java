package dao;



import org.hibernate.Session;
import org.hibernate.Transaction;

import model.User;
public class UserDao {
	
	public void createUser(Session session, User user) {
        Transaction transaction = session.beginTransaction();
        session.save(user); // Save the user to the database
        transaction.commit();
    }

    public User getUserById(Session session, int userId) {
        return session.get(User.class, userId);
    }
}


