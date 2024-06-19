package hiber.dao;

import hiber.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
   void add(User user);
   List<User> listUsers();
   User getUserByCarModelAndSeries(String carModel, String carSeries) throws SQLException;
}
