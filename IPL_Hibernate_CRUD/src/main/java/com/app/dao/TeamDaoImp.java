package com.app.dao;

import com.app.entities.Team;

import org.hibernate.*;
import static com.app.utils.HibernateUtils.getFactory;

import java.io.Serializable;

public class TeamDaoImp implements TeamDao {

	@Override
	public String addNewTeam(Team newTeam) {
		String messageString = "Adding new team failed!!!";
		
		//open session form sf (session factory)
		Session session = getFactory().openSession();
		
		//begin a transaction
		Transaction tx = session.beginTransaction();
		
		try {
			
			Serializable teamId = session.save(newTeam);
			//successful transaction
			tx.commit();
			messageString = "Added new team with ID = " + teamId;
			
		} catch (RuntimeException e) {
			//discard transaction
			if(tx != null) {
				tx.rollback();
			}
			//re throw SAME exception to the caller
			throw e;
		}
		finally {
			//close session -> L1 cache gets destroyed and pooled out 
			// 				db connection returns to DBCP 
			if(session != null) {
				session.close();			
			}
		}
		return messageString;
	}

}
