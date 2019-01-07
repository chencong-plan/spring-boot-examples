package cc.ccoder.springbootexcel.dao;

import cc.ccoder.springbootexcel.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ccoder.cc
 * @since 2019-1-7 15:20:35
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * findById
     *
     * @param id id
     * @return user
     */
    User findById(long id);
}
