package com.app.dao;

import java.util.List;

import com.app.entities.Team;

public interface TeamDao {
	
	//add a method to add new team	
	String addNewTeam(Team newTeam);
	
	//Display  team id n abbreviation of all the teams - no inputs required
	List<Team> getAllTeamIdsAndAbbreviations();
	
	//Delete team details   ->   i/p : team id
	String deleteTeamDetails(Long id);
	
	//Display team details by specified team id
	//	i/p : team id
	//	o/p : team details or null
	Team getTeamDetailsByTeamId(Long teamId);
	
	//	Update max age n batting avg requirement of a specific 
	//	team by it's name (team name is unique)
	//	i/p team name , new max age n batting avg
	//	Hint : JPQL (select) , before tx.commit : invoke setters(2)
	String updateMaxAgeAndBatAvgByTeamName(String name, int maxAge, double battingAvg);
	
	//Display all those teams who need , player's max age < specific age n min no of wickets taken > specified wickets. 
	//Sort the results as per desc order of max age
	//	i/p : age , no of wickets
	//	Hint : JPQL with order by desc
	List<Team> sortByDescOrderOfMaxAgeDependingOnLessThanMaxAgeAndMinWickets(int maxAgeLimit, int minWicketsReq);
	
}
