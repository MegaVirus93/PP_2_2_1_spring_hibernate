package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
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

        userService.listUsers().forEach(user -> System.out.println(user + ": " + user.getUserCar().toString()));

        String model = tesla.getModel();

        System.out.printf("The car \"%s\" belongs to a %s\n", model,
                userService.getUserByCar(model).getFirstName());

        context.close();
    }
}
