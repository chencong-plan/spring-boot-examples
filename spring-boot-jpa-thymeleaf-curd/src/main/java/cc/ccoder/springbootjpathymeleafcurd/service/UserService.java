package cc.ccoder.springbootjpathymeleafcurd.service;

import cc.ccoder.springbootjpathymeleafcurd.pojo.User;

import java.util.List;

public interface UserService {

    List<User> getUserList();

    User findUserById(long id);

    void save(User user);

    void update(User user);

    void delete(long id);

}
