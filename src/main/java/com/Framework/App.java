package com.Framework;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	Properties P;
    	P = new Properties();
		 FileInputStream fis;
		try {
			fis = new FileInputStream("Data/EmailProperties.xml");
			 P.loadFromXML(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String timeStamp = sdf.format(date);
    	//String timeStamp =new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	String subject = P.getProperty("subject")+" - "+timeStamp;
         String to = P.getProperty("recipients");//change accordingly  
         String from = P.getProperty("from"); 
         String host = P.getProperty("SMTP_HOST_NAME");//or IP address  
     
        //Get the session object  
         Properties properties = System.getProperties();  
         properties.setProperty("mail.smtp.host", host);  
         Session session = Session.getDefaultInstance(properties);  
     
        //compose the message  
         try{  
        	 Message  message = new MimeMessage(session);  
            message.setFrom(new InternetAddress(from));  
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
            message.setSubject(subject); 
            
            String content = "";
            try {
                BufferedReader in = new BufferedReader(new FileReader("target/CucumberReport.html"));
                String str;
                while ((str = in.readLine()) != null) {
                    content +=str;
                }
                in.close();
            } catch (IOException e) {
            }
            
            message.setContent(
            		content,
                   "text/html");
     
            // Send message  
            Transport.send(message);  
            System.out.println("message sent successfully....");  
     
         }catch (MessagingException mex) {mex.printStackTrace();}  
      }  
		
		
    }

