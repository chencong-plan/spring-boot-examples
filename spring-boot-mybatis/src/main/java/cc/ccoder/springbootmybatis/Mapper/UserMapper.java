package cc.ccoder.springbootmybatis.Mapper;

import cc.ccoder.springbootmybatis.enums.SexEnum;
import cc.ccoder.springbootmybatis.pojo.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 因为在SpringBoot的启动类上面已经写了@MapperScan("")进行扫描了mapper类存在的包，在此不需要使用@Mapper注解
 * @author ccoder.cc
 * @since 2019/1/18 15:53
 */
public interface UserMapper {

    /**
     * 查询所有
     * @return
     */
    @Select("select * from user")
    @Results({
            @Result(property = "sex",column = "sex",javaType = SexEnum.class),
            @Result(property = "createTime",column = "create_time")
    })
    List<User> getAll();
}
