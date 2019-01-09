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

    /**
     * 发送简单邮件，支持html
     *
     * @param to      收件人
     * @param subject 邮件标题
     * @param content 邮件内容
     * @param isHtml  是否支持html，true表示需要创建一个multipart message
     */
    void sendSimpleHtmlMail(String to, String subject, String content, boolean isHtml);

    /**
     * 发送带有附件的邮件
     *
     * @param to       收件人
     * @param subject  邮件标题
     * @param content  邮件内容
     * @param filePath 邮件附件文件路径
     */
    void sendAttachmentsMail(String to, String subject, String content, String filePath);

    /**
     * 发送带有静态资源的邮件,一般指图片
     *
     * @param to      收件人
     * @param subject 邮件标题
     * @param content 邮件内容
     * @param rscPath 静态资源路径
     * @param rscId
     */
    void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
}
