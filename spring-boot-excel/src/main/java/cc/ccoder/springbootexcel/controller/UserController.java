package cc.ccoder.springbootexcel.controller;

import cc.ccoder.springbootexcel.pojo.User;
import cc.ccoder.springbootexcel.service.UserService;
import cc.ccoder.springbootexcel.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ccoder.cc
 * @since 2019-1-7 15:14:55
 */
@RestController
public class UserController extends BaseController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 下旬指定id用户信息
     *
     * @param id id
     * @return 返回user
     */
    @ResponseBody
    @RequestMapping("/user/{id}")
    public User userInfo(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    /**
     * 下载指定user数据
     *
     * @param id id
     */
    @ResponseBody
    @RequestMapping("excel/user/{id}")
    public void downloadUser(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        List<UserVo> userVoList = Arrays.asList(userVo);
        writeXLS(userVoList, UserVo.class, user.getUsername() + "的信息");
    }

    /**
     * 下载所有user数据
     */
    @ResponseBody
    @RequestMapping("excel/user")
    public void downloadUser() {
        List<User> userList = userService.findUsers();
        List<UserVo> userVoList = new ArrayList<>();
        for (User user : userList) {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user, userVo);
            userVoList.add(userVo);
        }
        writeXLS(userVoList, UserVo.class, "所有用户信息");
    }
}
