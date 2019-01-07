package cc.ccoder.springbootexcel.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ccoder.cc
 * @since 2019-1-7 14:52:51
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DateField{
    public abstract String name();
    public abstract String datePattern();
}