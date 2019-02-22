package cc.ccoder.springbootwebflux.controller;

import cc.ccoder.springbootwebflux.SpringBootWebfluxApplicationTests;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.Assert.*;

/**
 * @author chencong(ccoder.cc)
 * @since 2019/2/22 16:06
 */
@RunWith(SpringRunner.class)
@WebFluxTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired
    private WebTestClient client;

    @Test
    public void hello() {
        client.get()
                .uri("/hello")
                .exchange()
                .expectStatus()
                .isOk();
    }
}