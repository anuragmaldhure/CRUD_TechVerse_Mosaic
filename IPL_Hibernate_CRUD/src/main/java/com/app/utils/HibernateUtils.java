package com.app.utils;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static SessionFactory factory;
	
	static {
		System.out.println("in static block");
		
		factory = new Configuration() //creates empty config
				.configure()			//reads all props from hib cfg xml
				.buildSessionFactory();		//create singleton SF from loaded
	}
	
	public static SessionFactory getFactory() {
		return factory;
	}
	
}
