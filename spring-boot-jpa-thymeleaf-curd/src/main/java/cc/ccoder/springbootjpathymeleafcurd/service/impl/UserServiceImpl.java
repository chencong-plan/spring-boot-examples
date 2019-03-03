package cc.ccoder.springbootjpathymeleafcurd.service.impl;

import cc.ccoder.springbootjpathymeleafcurd.common.Page;
import cc.ccoder.springbootjpathymeleafcurd.dao.UserRepository;
import cc.ccoder.springbootjpathymeleafcurd.pojo.User;
import cc.ccoder.springbootjpathymeleafcurd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    /**
     * 用来处理用户数据分页的方法实现
     * 1、在此使用的是MySQL 的 limit方法进行分页
     * 2、首先查询数据总条数Count(1) count(1)是针对第一列进行统计数据条数 和 Count(*)作用是一样 但是前者效率相对Count(*)高
     * 3、通pageNum 和 pageSize计算需要查询多少行数据
     * 计算公式：(pageNum-1)*pageSize,pageSize  这个我们到数据库中进行验证
     * 4、计算总页数：totalPages
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<User> findUserByPage(Integer pageNum, Integer pageSize) {
        // 不管是否存在数据 pageNum 和 pageSize是不变的 由前端传递过来
        Page<User> page = new Page<>();
        if (pageNum == null || pageSize == null) {
            return page;
        }
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        // 第一步计算总共数据有多少行
        // 查询表中有多少数据 相当于你自己userDao里面写的方法  sql:select count(1) from user;
        // userRepository = userDao
        Long count = userRepository.count();
        //计算总页数 totalPages
        if (count == 0) {
            return page;
        }
        // temp 是余数 而不是 商
        // 斯普哦 temp ==0 ?temp:temp+1 是错误的 一直都是对余数操作
        Long temp = count % pageSize;
        Long totalPages = temp == 0 ? count / pageSize : count / pageSize + 1;
        page.setTotalPages(totalPages);
        page.setTotalCount(count);
        //现在再来查询
        // 我是用的ORM是 jpa里面有分页 但是我这里不用 自己写sql
        //你可以在这里将pageNum pageSize 传递到数据库中查询
        // 也可以在这里转换好 此处我先转换 在查询
        List<User> result = userRepository.findUserByPage((pageNum - 1) * pageSize, pageSize);
        page.setResult(result);
        return page;
    }
}
