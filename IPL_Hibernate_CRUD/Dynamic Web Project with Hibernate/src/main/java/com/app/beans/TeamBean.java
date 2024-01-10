package com.app.beans;

import java.util.List;

import com.app.daos.TeamDao;
import com.app.daos.TeamDaoImp;
import com.app.entities.Team;

public class TeamBean {
	//dependency : team dao
	private TeamDao teamDao;
	
	public TeamBean() {
		//create team dao instance
		teamDao = new TeamDaoImp();
		System.out.println("Created team bean and Team DAO");
	}
	
	//Add B.L. method to get team details
	public List<Team> getTeamDetails() 
	{	
		System.out.println("in get Team details");
		return teamDao.getAllTeamIdsAndAbbreviations();
	}
}
