package hiber.dao;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);

   List<User> listUsers();

   void cleanTable(String name);
   User getUserFromCar(String model, int serial);
}
