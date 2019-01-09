package cc.ccoder.springbootmail.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author ccoder.cc
 * @since 2019-1-9 10:41:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

//    @Test
    public void sendSimpleMail() {
        mailService.sendSimpleMail("chencong@jytpay.com","邮件标题","简单邮件内容");
    }

    @Test
    public void sendSimpleHtmlMail(){
        String content = "<html>\n" +
                "<head>\n" +
                "    <title>测试html邮件</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h3>Hello World</h3>\n" +
                "    <h4>这是一个html邮件</h4>\n" +
                "    <a href=\"www.ccoder.cc\">聪聪不匆匆</a>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendSimpleHtmlMail("chencong@jytpay.com","邮件标题",content,true);
    }
}