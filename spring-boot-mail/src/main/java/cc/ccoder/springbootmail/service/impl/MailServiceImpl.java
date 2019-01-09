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
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

/**
 * @author ccoder.cc
 * @since 2019-1-9 10:33:37
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    private final TemplateEngine templateEngine;

    @Value("${mail.fromMail.address}")
    private String from;

    @Autowired
    public MailServiceImpl(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
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
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, isHtml);
            mailSender.send(message);
            log.info("简单邮件已发送,邮件收件人:{},邮件发送人:{},邮件内容:{}", to, from, content);
        } catch (Exception e) {
            log.info("简单邮件发送发生异常,邮件收件人:{},邮件发送人:{},邮件内容:{},异常信息:", to, from, content, e);

        }
    }

    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);

            FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            messageHelper.addAttachment(fileName, fileSystemResource);

            mailSender.send(message);
            log.info("简单邮件已发送,邮件收件人:{},邮件发送人:{},邮件内容:{}", to, from, content);
        } catch (Exception e) {
            log.info("简单邮件发送发生异常,邮件收件人:{},邮件发送人:{},邮件内容:{},异常信息:", to, from, content, e);
        }
    }

    @Override
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);

            FileSystemResource fileSystemResource = new FileSystemResource(new File(rscPath));
            messageHelper.addInline(rscId, fileSystemResource);

            mailSender.send(message);
            log.info("简单邮件已发送,邮件收件人:{},邮件发送人:{},邮件内容:{}", to, from, content);
        } catch (Exception e) {
            log.info("简单邮件发送发生异常,邮件收件人:{},邮件发送人:{},邮件内容:{},异常信息:", to, from, content, e);
        }
    }

    @Override
    public void sendTemplateMail(Map<String, Object> params) {
        String to = (String) params.get("to");
        String id = (String) params.get("id");
        String name  = (String) params.get("name");
        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", id);
        context.setVariable("name",name);
        String emailContent = templateEngine.process("emailTemplate", context);
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject("这是一个模板邮件");
            messageHelper.setText(emailContent, true);
            mailSender.send(message);
            log.info("简单邮件已发送,邮件收件人:{},邮件发送人:{},邮件内容:{}", params.get("to"), from, emailContent);
        } catch (Exception e) {
            log.info("简单邮件发送发生异常,邮件收件人:{},邮件发送人:{},邮件内容:{},异常信息:", to, from, emailContent, e);

        }

    }
}
