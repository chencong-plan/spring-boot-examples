package cc.ccoder.springbootmybatis.Mapper;

import cc.ccoder.springbootmybatis.pojo.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author ccoder.cc
 * @since 2019/1/18 16:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void getAll() {
        List<User> userList = userMapper.getAll();
        System.out.println(userList.toString());
    }
}