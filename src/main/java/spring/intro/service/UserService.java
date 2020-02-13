package spring.intro.service;

import spring.intro.model.User;

import java.util.List;

public interface UserService {
    User add(User user);

    User findByEmail(String email);

    List<User> getAll();
}
