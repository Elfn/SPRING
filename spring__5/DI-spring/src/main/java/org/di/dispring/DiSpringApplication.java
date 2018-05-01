package org.di.dispring;

import org.di.dispring.beans.ExplDataSource;
import org.di.dispring.beans.SecondDataSource;
import org.di.dispring.services.GreetingService;
import org.di.dispring.services.GreetingServiceFactory;
import org.di.dispring.services.PrimaryGermanGreetingService;
import org.di.dispring.services.PrimaryProcessImpl;
import org.di.dispring.controllers.ConstructorInjectionController;
import org.di.dispring.controllers.GetterInjectionController;
import org.di.dispring.controllers.MyController;
import org.di.dispring.controllers.PropertyInjectionController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.di.dispring")
public class DiSpringApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(DiSpringApplication.class, args);

		MyController cont = (MyController) ctx.getBean("myController");
		ConstructorInjectionController consCont = (ConstructorInjectionController) ctx.getBean("constructorInjectionController");
		GetterInjectionController getterCont = (GetterInjectionController) ctx.getBean("getterInjectionController");
		PropertyInjectionController propCont = (PropertyInjectionController) ctx.getBean("propertyInjectionController");
//		PrimaryProcessImpl primaryProcess = (PrimaryProcessImpl) ctx.getBean("primaryProcessImpl");
		GreetingServiceFactory factory = (GreetingServiceFactory) ctx.getBean("greetingServiceFactory");
		ExplDataSource eds = (ExplDataSource) ctx.getBean(ExplDataSource.class);
		SecondDataSource sds = (SecondDataSource) ctx.getBean(SecondDataSource.class);

//		System.out.println(cont.greeting()+"\n"+consCont.Aposition("AACONS")+"\n"+getterCont.Aposition("AAGET")+"\n"+propCont.Aposition("AAPROP"));
		System.out.println("DataSource ->"+eds.getPassword()+"\n"+eds.getUsername());
		System.out.println("Second DataSource ->"+sds.getPassword()+"\n"+sds.getUsername());

	}
}
