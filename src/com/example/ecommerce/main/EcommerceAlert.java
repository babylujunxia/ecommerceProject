package com.example.ecommerce.main;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class TestJavaMail {

    public static void main(String[] args) {
        
        MailBean mb = new MailBean();
        mb.setHost("service.netease.com");                       
        mb.setUsername("bjcorp");           
        mb.setPassword("NetCorp*^|Ecommerce888");  
        mb.setFrom("bjcorp@service.netease.com");    
        mb.setTo("bjcorp@service.netease.com");      
        mb.setSubject("测试_JavaMail");              
        mb.setContent("彩票投注对比预警"+Ecommerce.getInstance(largeComparetion)+"请登录http://10.120.198.2/ecommerceAlert.do查看");  

        SendMail sm = new SendMail();
        
        
        if(sm.sendMail(mb))                               
            System.out.println("");
        else
            System.out.println("发送失败!");
    }
    
}