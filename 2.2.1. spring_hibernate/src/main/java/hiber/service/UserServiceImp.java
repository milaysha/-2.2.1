package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    public User getUserByCarModelAndSeries(String carModel, String carSeries) throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT u\n" +
                    "FROM User u\n" +
                    "JOIN u.car c\n" +
                    "WHERE c.model = :carModel\n" +
                    "AND c.series = :carSeries";
            Query query = session.createQuery(hql);
            query.setParameter("carModel", carModel);
            query.setParameter("carSeries", carSeries);
            List<User> users = query.list();
            return users.get(0);
        }
    }
}




