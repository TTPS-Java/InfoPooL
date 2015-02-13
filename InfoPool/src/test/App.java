package test;

import objetos.MailMail;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class App {

	@Around("execution(* DAOhiberJPA.SolicitudDAOhiberJPA.recuperarPorConductor(..))")
	public Object mandarMail(ProceedingJoinPoint joinPoint) {
		Object o = null;
		try {
			o = joinPoint.proceed();
		} catch (Throwable e) {

		}
		System.out.println("aspect");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"Spring-Mail.xml");

		MailMail mm = (MailMail) context.getBean("mailMail");
		mm.sendMail("from@no-spam.com", "josefine2_94@hotmail.com",
				"Testing123", "Testing only \n\n Hello Spring Email Sender");
		return o;
	}
}