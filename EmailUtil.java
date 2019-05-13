package com.qa.nasco.PST_Checkout.util;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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

import com.qa.nasco.PST_Checkout.base.Base;

public class EmailUtil extends Base {
	 
	public static void sendEmailWithAttachment(String reportPath, String Env_Subject, String overallStatus, String timestamp, String filename_attachment, String htmlContent) 
	{
 
		// this will set host of server- you can change based on your requirement 
		prop.put("mail.smtp.host", "10.201.111.97");
		
		prop.put("mail.smtp.user", "raviteja.saladi@nasco.com");
		
		prop.put("mail.smtp.starttls.enable","true");
 
		// set the port of socket factory 
		prop.put("mail.smtp.socketFactory.port", "25");
 
		// set socket factory
		prop.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		
		prop.put("mail.smtp.socketFactory.fallback", "true");

 
		// set the authentication to true
		prop.put("mail.smtp.auth", "true");
 
		// set the port of SMTP server
		prop.put("mail.smtp.port", "25");
 
		// This will handle the complete authentication
		Session session = Session.getDefaultInstance(prop,
 
				new javax.mail.Authenticator() {
 
					protected PasswordAuthentication getPasswordAuthentication() {
 
					return new PasswordAuthentication(prop.getProperty("Email_Username"), prop.getProperty("Email_Password"));
 
					}
 
				});
 
		try {
 
			// Create object of MimeMessage class
			Message message = new MimeMessage(session);
 
			// Set the from address
			message.setFrom(new InternetAddress("dl-pegapst-all@nasco.com"));
 
			// Set the recipient address
			//message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("balakrishnan.subramanium@nasco.com"));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(prop.getProperty("Email_list")));
			//message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("deepak.sahoo@nasco.com"));
			//message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("lindsay.denton@nasco.com"));
			//message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("sravanKumar.modem@nasco.com"));
			
			
            // Add the subject link
			
			message.setSubject(prop.getProperty("Email_Subject")+Env_Subject+"-Checkout-"+overallStatus+"-"+timestamp);
 
			// Create object to add multimedia type content
			BodyPart messageBodyPart1 = new MimeBodyPart();
			
	/*		BodyPart messageBodyPart3 = new MimeBodyPart();*/
 
			// Set the body of email
			
			/*messageBodyPart1.setText(prop.getProperty("Body_Text")+Env_Subject+" checkout execution results on"+timestamp);*/
			
			// set the body html table content
			
			messageBodyPart1.setContent(htmlContent, "text/html");
 
			// Create another object to add another content
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
 
			// Mention the file which you want to send
			String filename = reportPath;
			
			String filename1= filename_attachment;
 
			// Create data source and pass the filename
			DataSource source = new FileDataSource(filename);
 
			// set the handler
			messageBodyPart2.setDataHandler(new DataHandler(source));
 
			// set the file
			messageBodyPart2.setFileName(filename1);
			
			
			
			// Create object of MimeMultipart class
			Multipart multipart = new MimeMultipart();
 
			// add body part 1
			multipart.addBodyPart(messageBodyPart1);
 
			// add body part 2
			multipart.addBodyPart(messageBodyPart2);
			
			
			
			// set the content
			message.setContent(multipart);
			
	/*		message.setContent(htmlContent,"text/html");*/
 
			// finally send the email
			Transport.send(message);
 
			System.out.println("=====Email Sent=====");
 
		} catch (MessagingException e) {
 
			throw new RuntimeException(e);
 
		}
 
	}
 
}