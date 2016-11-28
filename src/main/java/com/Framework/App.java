package com.Framework;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
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


/**
 * Hello world!
 *
 */
public class App 
{
  
	public static void main( String[] args ) throws Exception
    {
    	Properties P,TestDataproperties = new Properties();
    	 FileInputStream fis;
    	try {
			fis = new FileInputStream("Data/TestProperties.xml");
			TestDataproperties.loadFromXML(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String BrowserType=TestDataproperties.getProperty("BrowserType");
    	String URL=TestDataproperties.getProperty("URL");
    	
    	String filename="target/CucumberReport.html";
    	P = new Properties();
	
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
            
            String content="Hi All,"  + System.lineSeparator() + System.lineSeparator() +
            		"Please find the Automation execution summary for as an attachment and Below are the Execution Details" + System.lineSeparator() +System.lineSeparator() + 
            		"Browser Name =" +BrowserType 
            		+ System.lineSeparator() + System.lineSeparator() +"URL =" +URL + System.lineSeparator() + System.lineSeparator() +"Thank you," + System.lineSeparator() + System.lineSeparator() + "GQA Automation team."
            		+ System.lineSeparator() + System.lineSeparator() + "Note: Please Use Chrome browser for attached result view for better experiance";
            
        
            BodyPart messageBodyPart = new MimeBodyPart();
          
            // Now set the actual message
            messageBodyPart.setText(content);
            
            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
          
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart,"text/html");

            Transport.send(message);  
            System.out.println("message sent successfully....");  
     
         }catch (MessagingException mex) {mex.printStackTrace();}  
      }  
		
		
    }

