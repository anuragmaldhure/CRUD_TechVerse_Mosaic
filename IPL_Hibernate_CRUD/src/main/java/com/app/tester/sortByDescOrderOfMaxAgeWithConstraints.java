package com.app.tester;

import static com.app.utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.app.dao.TeamDao;
import com.app.dao.TeamDaoImp;

public class sortByDescOrderOfMaxAgeWithConstraints {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in)) {
			// create dao instance
			TeamDao dao = new TeamDaoImp();
			System.out.println("Enter the max Age Limit and min Wickets required:");
			dao.sortByDescOrderOfMaxAgeDependingOnLessThanMaxAgeAndMinWickets(sc.nextInt(), sc.nextInt())
				.forEach(x -> System.out.println(x));
			
		} // JVM : sf.close() ---> DBCP : cleaned up !
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
