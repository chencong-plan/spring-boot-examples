package cc.ccoder.springbootmybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cc.ccoder.Mapper")
public class SpringBootMybatisApplication {
    /*这里使用了@MapperScan("") 就不需要在mapper类上使用@Mapper注解*/
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisApplication.class, args);
    }

}

