package spring.intro.dao;

import spring.intro.model.User;

import java.util.List;

public interface UserDao {
    User add(User user);

    User findByEmail(String email);

    List<User> getAll();

    User getById(Long userId);
}
