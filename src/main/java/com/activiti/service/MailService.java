package com.activiti.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.activiti.model.Mail;


@Service
public class MailService {

  private final static String from = "bingyanl@126.com";

  @Autowired
  private JavaMailSender emailSender;
  
  @Autowired
  private SpringTemplateEngine templateEngine;

  private static final Logger logger = LoggerFactory.getLogger(MailService.class);
  
  public void mail(String[] to, String subject, Map<String, Object> model, String fragments) {
    Mail mail = new Mail();
    mail.setFrom(from);
    mail.setTo(to);
    mail.setSubject(subject);
    mail.setModel(model);
    try {
      sendSimpleMessage(mail, fragments);
    } catch (MessagingException e) {
      logger.error(e.getMessage());
    } catch (IOException e) {
      logger.error(e.getMessage());
    }
  }
  
  public void sendSimpleMessage(Mail mail, String fragments) throws MessagingException, IOException {
    MimeMessage message = emailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message,
        MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

    Context context = new Context();
    context.setVariables(mail.getModel());
    String html = templateEngine.process(fragments, context);

    helper.setTo(mail.getTo());
    helper.setText(html, true);
    helper.setSubject(mail.getSubject());
    helper.setFrom(mail.getFrom());

    emailSender.send(message);
  }
  

}
