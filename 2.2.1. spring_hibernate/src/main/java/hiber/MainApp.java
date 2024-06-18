package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarServiceImp;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarServiceImp carServiceImp = context.getBean(CarServiceImp.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      Car car = new Car("tesla", "x");
      carServiceImp.add(car);

      User userx = new User("User4", "Lastname4", "user4@mail.ru");
      car.setUser(userx);
      userx.setCar(car);
      userService.add(userx);

      User userByCarModelAndSeries = userService.getUserByCarModelAndSeries("tesla", "x");

      System.out.println("Id = "+userByCarModelAndSeries.getId());
      System.out.println("First Name = "+userByCarModelAndSeries.getFirstName());
      System.out.println("Last Name = "+userByCarModelAndSeries.getLastName());
      System.out.println("Email = "+userByCarModelAndSeries.getEmail());
      System.out.println("Car = "+userByCarModelAndSeries.getCar());
      System.out.println();

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }
      context.close();
   }
}
