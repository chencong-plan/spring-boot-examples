package cc.ccoder.springbootmybatis.enums;

/**
 * @author ccoder.cc
 * @since 2019/1/18 16:04
 */
public enum SexEnum {

    /**
     * 男
     */
    MAN(1,"男"),

    /**
     * 女
     */
    WOMAN(0,"女");

    private Integer code;
    private String msg;

    SexEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
