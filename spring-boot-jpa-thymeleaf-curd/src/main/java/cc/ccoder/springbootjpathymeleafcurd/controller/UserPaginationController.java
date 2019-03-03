package cc.ccoder.springbootjpathymeleafcurd.controller;

import cc.ccoder.springbootjpathymeleafcurd.common.Page;
import cc.ccoder.springbootjpathymeleafcurd.pojo.User;
import cc.ccoder.springbootjpathymeleafcurd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chencong(ccoder.cc)
 * @since 2019/3/3 14:26
 */
@Controller
public class UserPaginationController {

    @Autowired
    private UserService userService;

    /**
     * 跳转到需要分页的页面的方法
     * @return
     */
    @GetMapping("/page/list")
    public String pageUser(){
        return "page/list";
    }


    /**
     * 返回分页数据
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("/page/user")
    @ResponseBody
    public Page<User> findUser(int pageNum,int pageSize){
        Page<User> userPage = userService.findUserByPage(pageNum,pageSize);
        return userPage;
    }



}
