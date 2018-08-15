package cc.ccoder.springbootjpathymeleafcurd.dao;

import cc.ccoder.springbootjpathymeleafcurd.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findById(long id);

    Long deleteById(long id);
}
