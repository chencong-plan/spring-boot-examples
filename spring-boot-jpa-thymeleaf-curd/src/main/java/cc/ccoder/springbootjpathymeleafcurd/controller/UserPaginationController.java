package cc.ccoder.springbootjpathymeleafcurd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author chencong(ccoder.cc)
 * @since 2019/3/3 14:26
 */
@Controller
public class UserPaginationController {

    @GetMapping("/page/list")
    public String pageUser(){
        return "page/list";
    }
}
