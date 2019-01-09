package cc.ccoder.springbootmail.service;

/**
 * @author ccoder.cc
 * @since 2019-1-9 10:32:36
 */
public interface MailService {

    /**
     * 发送简单邮件
     *
     * @param to      收件人
     * @param subject 邮件标题
     * @param content 邮件内容
     */
    void sendSimpleMail(String to, String subject, String content);
}
