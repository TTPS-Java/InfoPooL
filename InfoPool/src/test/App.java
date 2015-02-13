package test;

import objetos.MailMail;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = 
             new ClassPathXmlApplicationContext("Spring.xml");
 
    	MailMail mm = (MailMail) context.getBean("mailMail");
        mm.sendMail("from@no-spam.com",
    		   "josefine2_94@hotmail.com",
    		   "Testing123", 
    		   "Testing only \n\n Hello Spring Email Sender");
 
    }
}