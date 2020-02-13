package spring.intro;

import spring.intro.config.AppConfig;
import spring.intro.model.User;
import spring.intro.service.UserService;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user = new User();
        user.setEmail("parker@gmail.com");
        user.setPassword("parker");
        userService.add(user);
        user.setEmail("jones@gmail.com");
        user.setPassword("jones");
        userService.add(user);

        System.out.println(userService.getAll());
    }
}
