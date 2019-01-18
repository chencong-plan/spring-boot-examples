package cc.ccoder.springbootmybatis.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ccoder.cc
 * @since 2019/1/18 15:47
 */
@Data
public class User {

    private Integer id;
    private String username;
    private String password;
    private Integer role;
    private String email;
    private BigDecimal amount;
    private String age;
    private Date createTime;
}
