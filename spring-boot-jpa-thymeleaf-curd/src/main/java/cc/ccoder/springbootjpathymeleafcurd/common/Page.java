package cc.ccoder.springbootjpathymeleafcurd.common;

import lombok.Data;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author chencong(ccoder.cc)
 * @since 2019/3/3 14:55
 */

@Data
public class Page<T>{
    /**
     * 显示数据
     */
    private List<T> result;

    /**
     * 总页数
     */
    private long totalPages;

    /**
     * 总共数据
     */
    private long totalCount;

    /**
     * 当前页数
     */
    private long pageNum;

    /**
     * 每页显示大小
     */
    private long pageSize;

    //还有一点 你看这里频繁修改了属性后 响应的getter 和  setter 方法都要做修改 是不是很麻烦呢
    // 那么现在有个工具就是直接使用注解的方式 你只关心修改属性就行了 相应的getter和 setter方法都在【编译时】自动完成
    // 这个注解的工具叫做lombok 里面有好几种注解 每一个注解有什么用 你可以后面自行搜索  实际开发当中很常用
    // 从刚刚的文档注释 可以看出这个@Data生成的方式有这几种
    //Getter
    //Setter
    //RequiredArgsConstructor
    //ToString
    //EqualsAndHashCode
    // 有问题不？
}
