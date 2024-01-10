package com.app.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

//id , name, abbreviation,owner,max_age,batting_avg,wickets_taken
//one , parent, inverse
@Entity
@Table(name = "teams")
public class Team extends BaseEntity {
	@Column(length = 100)
	private String name;
	@Column(length = 10)
	private String abbreviation;
	@Column(length = 20)
	private String owner;
	@Column(name = "max_age")
	private int maxAge;// max age of the player allowed in this team
	@Column(name = "batting_avg")
	private double battingAvg;
	@Column(name = "wickets_taken")
	private int wicketsTaken;
	
	//Bi - directional relationship 
	//teams : one, parent, inverse(non owning side = FK on players table)
	//Team 1 --->* Players
	@OneToMany(mappedBy = "myTeam", 
			cascade = javax.persistence.CascadeType.ALL,
			orphanRemoval = true)										
	private List<Player> playersList = new ArrayList<>();
	

	public List<Player> getPlayers() {
		return playersList;
	}

	public void setPlayers(List<Player> players) {
		this.playersList = players;
	}

	public Team() {
		// TODO Auto-generated constructor stub
	}
	
	public Team(Integer teamId, String abbreviation) {
		setId(teamId);
		this.abbreviation = abbreviation;
	}

	public Team(int teamId, String name, String abbreviation, String owner, int maxAge, double battingAvg,
			int wicketsTaken) {
		super();

		this.name = name;
		this.abbreviation = abbreviation;
		this.owner = owner;
		this.maxAge = maxAge;
		this.battingAvg = battingAvg;
		this.wicketsTaken = wicketsTaken;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getabbreviation() {
		return abbreviation;
	}

	public void setabbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	public double getBattingAvg() {
		return battingAvg;
	}

	public void setBattingAvg(double battingAvg) {
		this.battingAvg = battingAvg;
	}

	public int getWicketsTaken() {
		return wicketsTaken;
	}

	public void setWicketsTaken(int wicketsTaken) {
		this.wicketsTaken = wicketsTaken;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	//helper methods : add player, remove player
	
	public void addPlayer(Player player) {
		playersList.add(player);	//Team 	 -> Player
		player.setMyTeam(this);		//Player -> Team
	}
	
	public void removePlayer(Player player) {
		playersList.remove(player);	//Team 	 X-> Player
		player.setMyTeam(null);		//Player X-> Team
	}

	@Override
	public String toString() {
		return "Team [teamId=" + getId() + ", name=" + name + ", abbreviation=" + abbreviation + ", owner=" + owner
				+ ", maxAge=" + maxAge + ", battingAvg=" + battingAvg + ", wicketsTaken=" + wicketsTaken + "]";
	}

}
