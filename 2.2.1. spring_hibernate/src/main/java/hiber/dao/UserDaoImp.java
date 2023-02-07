package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }


    @Override
    public void cleanTable (String name) {
        Session session = sessionFactory.getCurrentSession();
        session.createSQLQuery("DELETE FROM " + name + "\n" +
                "WHERE ID > 0").executeUpdate();
    }

    @Override
    public User getUserFromCar(String model, int serial) {
       Session session = sessionFactory.getCurrentSession();
       Query query = session.createQuery("from User u where (u.car.model=:model ) and (u.car.series=:serial)");
       query.setParameter("model",model);
       query.setParameter("serial", serial);
       User user = (User) query.getSingleResult();
       return user;
    }

}
