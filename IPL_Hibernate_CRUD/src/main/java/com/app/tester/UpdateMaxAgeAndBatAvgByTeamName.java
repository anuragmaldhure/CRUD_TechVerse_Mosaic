package com.app.tester;

import static com.app.utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.app.dao.TeamDao;
import com.app.dao.TeamDaoImp;


public class UpdateMaxAgeAndBatAvgByTeamName {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in)) {
			// create dao instance
			TeamDao dao = new TeamDaoImp();
//			System.out.println("Exisiting teams :");
//			dao.getAllTeamIdsAndAbbreviations().forEach(x -> System.out.println(x.getId()+ "  ->  " + x.getAbbreviation()));
//			
//			System.out.println("Enter the id of the team to get details of:");
//			System.out.println(dao.getTeamDetailsByTeamId(sc.nextLong()));
			
			System.out.println("Enter team name followed by new max age and batting avg requirement");
			System.out.println(dao.updateMaxAgeAndBatAvgByTeamName(sc.next(), sc.nextInt(),sc.nextDouble()));
		} // JVM : sf.close() ---> DBCP : cleaned up !
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
