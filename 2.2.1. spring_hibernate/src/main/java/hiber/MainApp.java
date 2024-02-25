package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car mazda = new Car("Mazda", 3);
        Car tesla = new Car("Tesla", 3);
        Car ferrari = new Car("Ferrari", 488);
        Car bmw = new Car("BMW", 7);

        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        user2.setUserCar(mazda);
        user3.setUserCar(tesla);
        user1.setUserCar(ferrari);
        user4.setUserCar(bmw);

        userService.add(user2);
        userService.add(user3);
        userService.add(user1);
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println(user.getUserCar());
            System.out.println();
        }

        System.out.printf("The car \"%s\" belongs to a %s\n", tesla.getModel(),
                userService.getUserByCar(tesla.getModel()).getFirstName());

        context.close();
    }
}
