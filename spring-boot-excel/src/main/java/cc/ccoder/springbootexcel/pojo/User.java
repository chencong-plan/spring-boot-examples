package cc.ccoder.springbootexcel.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <B>系统名称: </B>收付款系统<BR>
 * <B>模块名称: </B><BR>
 * <B>中文类名: </B><BR>
 * <B>概要说明: </B><BR>
 *
 * @author chencong@jytpay.com
 * @since 2019/1/7 15:15
 */
@Entity
@Data
public class User {
    /**
     * 主键
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 用户名
     */
    @Column(nullable = false, unique = true)
    private String username;
    /**
     * 密码
     */
    @Column(nullable = false)
    private String password;
    /**
     * 角色 0 普通，1 管理员
     */
    @Column(nullable = false)
    private int role;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 时间
     */
    private Date createTime;
}
