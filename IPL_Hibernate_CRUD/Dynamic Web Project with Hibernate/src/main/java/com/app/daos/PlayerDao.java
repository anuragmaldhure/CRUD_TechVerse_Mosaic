package com.app.daos;

import com.app.entities.Player;

public interface PlayerDao {
	//add a method to add player to the team
	String addPlayerToTeam(Integer teamId, Player newPlayer);
}
