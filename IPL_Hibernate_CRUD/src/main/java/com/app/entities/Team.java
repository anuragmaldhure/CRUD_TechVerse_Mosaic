package com.app.entities;

import javax.persistence.*;

//create table teams (team_id bigint primary key auto_increment,
//						name varchar(100),abbreviation varchar(10),
//							owner varchar(20),max_age int,
//								batting_avg double,wickets_taken int);

@Entity
@Table(name = "teams")
public class Team {
	@Id 				//Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto-generated
	private Long id;
	
	@Column(length = 100, unique = true)
	private String name;
	
	@Column(length = 10, unique = true)
	private String abbreviation;
	
	@Column(length = 20, name = "owner")
	private String ownerName;
	
	@Column(name = "max_age")
	private int maxAge;
	
	@Column(name="batting_avg")
	private double battingAverage;
	
	@Column(name="wickets_taken")
	private int wicketsTaken;
	
	public Team() {
		
	}
	

	public Team(String name, String abbreviation, String ownerName, int maxAge, double battingAverage,
			int wicketsTaken) {
		this.name = name;
		this.abbreviation = abbreviation;
		this.ownerName = ownerName;
		this.maxAge = maxAge;
		this.battingAverage = battingAverage;
		this.wicketsTaken = wicketsTaken;
	}

	public Team(Long id, String abbreviation) {
		this.id = id;
		this.abbreviation = abbreviation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	public double getBattingAverage() {
		return battingAverage;
	}

	public void setBattingAverage(double battingAverage) {
		this.battingAverage = battingAverage;
	}

	public int getWicketsTaken() {
		return wicketsTaken;
	}

	public void setWicketsTaken(int wicketsTaken) {
		this.wicketsTaken = wicketsTaken;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Team [id=").append(id).append(", name=").append(name).append(", abbreviation=")
				.append(abbreviation).append(", ownerName=").append(ownerName).append(", maxAge=").append(maxAge)
				.append(", battingAverage=").append(battingAverage).append(", wicketsTaken=").append(wicketsTaken)
				.append("]");
		return builder.toString();
	}
}
