package cc.ccoder.springbootexcel.service.impl;

import cc.ccoder.springbootexcel.dao.UserRepository;
import cc.ccoder.springbootexcel.pojo.User;
import cc.ccoder.springbootexcel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ccoder.cc
 * @since 2019-1-7 15:21:29
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    @Override
    public List<User> findUsers() {
        return userRepository.findAll();
    }
}
