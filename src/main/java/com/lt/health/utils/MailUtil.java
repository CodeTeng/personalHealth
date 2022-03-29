package com.lt.health.utils;

import com.lt.health.entity.dto.MailDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.util.Arrays;

/**
 * @description: 邮件工具类
 * @author: 狂小腾
 * @date: 2022/3/27 23:11
 */
@Slf4j
@Component
public class MailUtil {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送邮件
     *
     * @param mail 发送内容
     * @return 发送成功或失败信息
     */
    public String sendMail(MailDTO mail) {
        try {
            if (mail.isHtml()) {
                MimeMessage mimeMessage = mailSender.createMimeMessage();
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
                messageHelper.setFrom(from);
                messageHelper.setSubject(mail.getSubject());
                messageHelper.setText(mail.getContent(), true);
                messageHelper.setTo(mail.getReceivers());
                mailSender.send(mimeMessage);
                log.info("HTML邮件发送成功！收件人---{}---", Arrays.asList(mail.getReceivers()));
            } else {
                // 创建邮件对象
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                // 发件人
                mailMessage.setFrom(from);
                // 收件人
                mailMessage.setTo(mail.getReceivers());
                // 邮件主题
                mailMessage.setSubject(mail.getSubject());
                // 邮件内容
                mailMessage.setText(mail.getContent());
                mailSender.send(mailMessage);
                log.info("普通邮件发送成功! 收件人---{}---", Arrays.asList(mail.getReceivers()));
            }
            return "邮件发送成功！";
        } catch (Exception e) {
            log.error("邮件发送失败--> {}", e.getMessage());
            return "邮件发送失败！";
        }
    }
}
