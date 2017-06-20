package com.retail.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SignUpMailer{ 
	
	private String from = "aditya24thakare@gmail.com";
	private String password = "@3friends";
	private String sub = "Retail verification";
	
	
	
          public boolean send(String to, String ran){
          boolean status;
          String msg = "Please click on the below link to verify your account \n" +"http://localhost:8080/api/user/signup/verify?val="+ran;
          //Get properties object    
          Properties props = new Properties();    
          props.put("mail.smtp.host", "smtp.gmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class",    
                    "javax.net.ssl.SSLSocketFactory");    
          props.put("mail.smtp.auth", "true");    
          props.put("mail.smtp.port", "465");    
          //get Session   
          Session session = Session.getDefaultInstance(props,    
           new javax.mail.Authenticator() {    
           protected PasswordAuthentication getPasswordAuthentication() {    
           return new PasswordAuthentication(from,password);  
           }    
          });    
          //compose message    
          try {    
           MimeMessage message = new MimeMessage(session);    
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
           message.setSubject(sub);    
           message.setText(msg);    
           //send message  
           Transport.send(message);
           status = true;
           System.out.println("message sent successfully");    
          } catch (MessagingException e) {
        	  status = false;
        	  throw new RuntimeException(e);
        	  }
          return status;
             
    }  
}  
