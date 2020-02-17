package spring.intro.controllers;

import spring.intro.config.AppConfig;
import spring.intro.model.User;
import spring.intro.model.UserResponseDto;
import spring.intro.service.UserService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/inject")
    public void inject() {
        User user = new User();
        user.setEmail("parker@gmail.com");
        user.setPassword("parker");
        userService.add(user);
        user.setEmail("jones@gmail.com");
        user.setPassword("jones");
        userService.add(user);
        user.setEmail("tramp@gmail.com");
        user.setPassword("tramp");
        userService.add(user);
        user.setEmail("jackson@gmail.com");
        user.setPassword("jackson");
        userService.add(user);
    }

    @GetMapping(value = "/get/{userId}")
    public UserResponseDto get(@PathVariable(name = "userId") Long userId) {
        User user = userService.getById(userId);
        UserResponseDto dto = new UserResponseDto();
        dto.setEmail(user.getEmail());
        return dto;
    }

    @RequestMapping(value = "/get/", method = RequestMethod.GET)
    public List<UserResponseDto> getAll() {
        List<UserResponseDto> dtoList = new ArrayList<>();
        for (User user: userService.getAll()) {
            UserResponseDto dto = new UserResponseDto();
            dto.setEmail(user.getEmail());
            dtoList.add(dto);
        }
        return dtoList;
    }
}
