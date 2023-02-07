package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        for (int i = 0; i < 5; i++) {
            Car car = new Car("Car" + i, i);
            User user = new User("User" + i, "Lastname" + i, "user" + i + "@mail.ru");
            user.setCar(car);
            userService.add(user);
        }
        //очистка таблиц по имени таблицы
        //userService.cleanTable("users");
        //userService.cleanTable("cars");


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            if (user.getCar() != null) {
                System.out.println("Сar = " + user.getCar().getModel());
            }
            System.out.println();
        }
        //при наличии 2х и более пар исключение NonUniqueResultException
        System.out.println(userService.getUserFromCar("Car1",1).getFirstName());
        context.close();
    }
}
