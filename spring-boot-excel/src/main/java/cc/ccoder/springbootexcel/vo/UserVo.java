package cc.ccoder.springbootexcel.vo;

import cc.ccoder.springbootexcel.annotion.DateField;
import cc.ccoder.springbootexcel.annotion.EnumFiled;
import cc.ccoder.springbootexcel.annotion.EnumMap;
import cc.ccoder.springbootexcel.annotion.ExcelFiled;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ccoder.cc
 * @since 2019-1-7 15:40:01
 */
@Data
public class UserVo {

    @ExcelFiled(name = "主键编号")
    private Long id;
    /**
     * 用户名
     */
    @ExcelFiled(name = "用户名")
    private String username;
    /**
     * 密码
     */
    @ExcelFiled(name = "密码")
    private String password;
    /**
     * 角色 0 普通，1 管理员
     */
    @EnumFiled(name = "用户角色", keyValueMap = {
            @EnumMap(k = "0", v = "普通用户"),
            @EnumMap(k = "1", v = "管理员")
    })
    private int role;
    /**
     * 邮箱
     */
    @ExcelFiled(name = "邮箱")
    private String email;
    /**
     * 金额
     */
    @ExcelFiled(name = "金额")
    private BigDecimal amount;

    /**
     * 年龄
     */
    @ExcelFiled(name = "年龄")
    private Integer age;

    /**
     * 导出到excel中时间格式,你可以试试 yyyyMMddHHmmss
     */
    @DateField(name = "创建时间", datePattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
