package com.learn.util;

/**
 * Created by Administrator on 2017/6/3.
 */

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class SendEmailUtil  {

    //获取用于发送邮件的Session
   public static Session  getSession() throws MessagingException {
       //创建连接对象 连接到邮件服务器
       Properties props = new Properties();

       //设置发送邮件的基本参数
       //发送邮件服务器
       props.put("mail.smtp.host", "smtp.163.com");
       props.put("mail.store.protocol" , "smtp");//设置协议
       //发送端口
       props.put("mail.smtp.port", "25");
       props.put("mail.smtp.auth", "true");

       Authenticator authenticator = new Authenticator() {
           @Override
           protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication("13718975414@163.com", "7218729ABC");
           }
       };
       Session session = Session.getDefaultInstance(props, authenticator);
       return session;
   }

    //发送邮件
    public static void send(String email, String sb) throws MessagingException{
        Session session = getSession();

        try {
            Message msg = new MimeMessage(session);
            //设置message属性
            msg.setFrom(new InternetAddress("13718975414@163.com"));
           //InternetAddress[] addrs = {new InternetAddress(receiver)};
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            msg.setSubject("贵宾王庆宇先生");
            msg.setSentDate(new Date());
            msg.setContent(sb, "text/html;charset=utf-8");

            //开始发送
            Transport.send(msg);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
