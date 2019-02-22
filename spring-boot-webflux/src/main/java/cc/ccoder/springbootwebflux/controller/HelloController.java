package cc.ccoder.springbootwebflux.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

/**
 * @author chencong(ccoder.cc)
 * @since 2019/2/22 16:02
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public Mono<String> hello(){
        return Mono.just("welcome to reactive world");
    }

}
