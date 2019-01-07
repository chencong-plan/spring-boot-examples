package cc.ccoder.springbootexcel.service;


import cc.ccoder.springbootexcel.pojo.User;

import java.util.List;

/**
 * @author ccoder.cc
 * @since 2019-1-7 15:21:01
 */
public interface UserService {
    /**
     * 通过id获取user
     *
     * @param id id
     * @return user
     */
    User findById(Long id);

    /**
     * 获取所有user信息
     * @return list
     */
    List<User> findUsers();

}
