package com.app.daos;

import static utils.HibernateUtils.getFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.entities.Player;
import com.app.entities.Team;

public class PlayerDaoImpl implements PlayerDao {

	@Override
	public String addPlayerToTeam(Integer teamId, Player newPlayer) {
		String messageString = "Adding new player failed!!!";
		
		//open session form sf (session factory)
		Session session = getFactory().openSession();
		
		//begin a transaction
		Transaction tx = session.beginTransaction();
		
		try {
			//get team details from it's id
			Team team = session.get(Team.class, teamId);
			if(team!=null) {
				//team exists, persistent
				team.addPlayer(newPlayer);
				messageString = "Added new player successfully!!!";
			}
			tx.commit();
		}
		catch (RuntimeException e) {
			if(tx!=null) {
				tx.rollback();
			}
		}
		return messageString;
	}
}
