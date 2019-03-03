package cc.ccoder.springbootjpathymeleafcurd.dao;

import cc.ccoder.springbootjpathymeleafcurd.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);

    Long deleteById(long id);

    /**
     * ? 代表参数占位符 1代表 对应接口参数列表中第一个参数
     *
     * @param arg0
     * @param arg1
     * @return
     */
    @Query(value = "select * from user limit ?1,?2", nativeQuery = true)
    List<User> findUserByPage(Integer arg0, Integer arg1);
}
