package com.app.tester;
import org.hibernate.*;
import static com.app.utils.HibernateUtils.getFactory;


public class TestHibernate {
	
	public static void main(String[] args) {
		try(SessionFactory sf = getFactory()){
			System.out.println("hibernate up and running...");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
