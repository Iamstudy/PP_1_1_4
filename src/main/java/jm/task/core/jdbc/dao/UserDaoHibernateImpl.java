package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    Session session;
    Transaction transaction;
    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.createNativeQuery("CREATE TABLE IF NOT EXISTS base.user" +
                " (id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR(45), " +
                "lastname VARCHAR(45), " +
                "age TINYINT, " +
                "PRIMARY KEY (id))").executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.createNativeQuery("DROP TABLE IF EXISTS base.user").executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.delete(session.get(User.class, id));
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = (List<User>)  getSessionFactory().openSession().createQuery("From User").list();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.createNativeQuery("TRUNCATE TABLE base.user").executeUpdate();
        transaction.commit();
        session.close();
    }
}
