package cc.ccoder.springbootmail.service.impl;

import cc.ccoder.springbootmail.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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
}
