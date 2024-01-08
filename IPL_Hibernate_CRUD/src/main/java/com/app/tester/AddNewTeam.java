package com.app.tester;
import org.hibernate.*;

import com.app.dao.TeamDao;
import com.app.dao.TeamDaoImp;
import com.app.entities.Team;

import static com.app.utils.HibernateUtils.getFactory;

import java.util.Scanner;


public class AddNewTeam {
	
	public static void main(String[] args) {
		try(SessionFactory sf = getFactory();Scanner scanner = new Scanner(System.in)){
			
			//create DAO instance
			TeamDao dao = new TeamDaoImp(); 
			
			System.out.println("Enter Team details: name, abbreviation, ownerName, maxAge,"
								+ "battingAverage, wicketsTaken");
			Team newTeam = new Team(scanner.next(), scanner.next(),scanner.next(), scanner.nextInt(),
									scanner.nextDouble(), scanner.nextInt());
			
			System.out.println(dao.addNewTeam(newTeam));
			
		}	//JVM : sf.close() -> DBCP cleaned up!
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
