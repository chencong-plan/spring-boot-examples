package cc.ccoder.springbootmail.service.impl;

import cc.ccoder.springbootmail.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author ccoder.cc
 * @since 2019-1-9 10:33:37
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    @Value("${mail.fromMail.address}")
    private String from;

    @Autowired
    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
            log.info("简单邮件已发送,邮件收件人:{},邮件发送人:{},邮件内容:{}", to, from, content);
        } catch (Exception e) {
            log.info("简单邮件发送发生异常,邮件收件人:{},邮件发送人:{},邮件内容:{},异常信息:", to, from, content, e);

        }
    }

    @Override
    public void sendSimpleHtmlMail(String to, String subject, String content, boolean isHtml) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper messageHelper = new MimeMessageHelper(message,true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(content,isHtml);
            mailSender.send(message);
            log.info("简单邮件已发送,邮件收件人:{},邮件发送人:{},邮件内容:{}", to, from, content);
        } catch (Exception e) {
            log.info("简单邮件发送发生异常,邮件收件人:{},邮件发送人:{},邮件内容:{},异常信息:", to, from, content, e);

        }
    }

    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();
        try{
            MimeMessageHelper messageHelper = new MimeMessageHelper(message,true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(content,true);

            FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            messageHelper.addAttachment(fileName,fileSystemResource);

            mailSender.send(message);
            log.info("简单邮件已发送,邮件收件人:{},邮件发送人:{},邮件内容:{}", to, from, content);
        }catch (Exception e){
            log.info("简单邮件发送发生异常,邮件收件人:{},邮件发送人:{},邮件内容:{},异常信息:", to, from, content, e);
        }
    }
}
