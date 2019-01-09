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
        mailService.sendSimpleMail("chencong@jytpay.com", "邮件标题", "简单邮件内容");
    }

    //    @Test
    public void sendSimpleHtmlMail() {
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
        mailService.sendSimpleHtmlMail("chencong@jytpay.com", "邮件标题", content, true);
    }

    //    @Test
    public void sendAttachmentsMail() {
        String filePath = "G:\\app\\depLogs\\cgservice\\cg-service.log";
        mailService.sendAttachmentsMail("chencong@jytpay.com", "邮件标题", "测试邮件附件", filePath);
    }

    @Test
    public void sendInlineResourceMail() {
        String rscId = "neo006";
        String content = "<html><body>这是有图片的邮件：<img src='cid:" + rscId + "' ></body></html>";
        String imgPath = "F:\\11.jpg";
        mailService.sendInlineResourceMail("chencong@jytpay.com", "这是一封带有静态图片的邮件", content, imgPath, rscId);
    }
}