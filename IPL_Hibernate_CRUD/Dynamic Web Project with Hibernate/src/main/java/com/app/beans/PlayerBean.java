package com.app.beans;

import java.time.LocalDate;
import java.time.Period;

import org.hibernate.HibernateException;

import com.app.daos.PlayerDao;
import com.app.daos.PlayerDaoImpl;
import com.app.daos.TeamDao;
import com.app.daos.TeamDaoImp;
import com.app.entities.*;

public class PlayerBean {
	private int teamId;
	private String fn;
	private String ln;
	private String dob;//WC cannot translate from string to date
	private double avg;
	private int wickets; 
	//dependencies : daos
	private TeamDao teamDao;
	private PlayerDao playerDao;
	
	public PlayerBean(){
		//create team dao instance
		teamDao = new TeamDaoImp();
		playerDao = new PlayerDaoImpl();
		System.out.println("Created player bean and Player DAO...");
	}
	
	//add a B.L. method to validate and add player
	public String validateAndAddPlayer() throws HibernateException {
		System.out.println("in validate n add");
		//get selected team details	
		Team team = teamDao.getTeamDetailsByTeamId(teamId);
		System.out.println(team);
		if(team!=null) {
			//team : persistent
			//string dob --> LocalDate parsing
			LocalDate date = LocalDate.parse(dob);
			//validate player details
			int age = Period.between(date, LocalDate.now()).getYears();
			if(age<team.getMaxAge() && avg>team.getBattingAvg()) {
				//create consistent player instance
				Player newPlayer = new Player(fn, ln, date, avg, wickets);
				//valid plater 
				return playerDao.addPlayerToTeam(team.getId(), newPlayer);
			}
			else {
				return "Invalid details of the player...";
			}
		}
		return "Team not found! Invalid details team Id...";
	}

	//setters required for data biding from form in jsp(view)
	
	
	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getFn() {
		return fn;
	}

	public void setFn(String fn) {
		this.fn = fn;
	}

	public String getLn() {
		return ln;
	}

	public void setLn(String ln) {
		this.ln = ln;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public int getWickets() {
		return wickets;
	}

	public void setWickets(int wickets) {
		this.wickets = wickets;
	}

	public TeamDao getTeamDao() {
		return teamDao;
	}

	public void setTeamDao(TeamDao teamDao) {
		this.teamDao = teamDao;
	}

	public PlayerDao getPlayerDao() {
		return playerDao;
	}

	public void setPlayerDao(PlayerDao playerDao) {
		this.playerDao = playerDao;
	}
}
