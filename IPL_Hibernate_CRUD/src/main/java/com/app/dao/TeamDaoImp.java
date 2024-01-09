package com.app.dao;

import com.app.entities.Team;

import org.hibernate.*;
import static com.app.utils.HibernateUtils.getFactory;

import java.io.Serializable;
import java.util.List;

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

	@Override
	public List<Team> getAllTeamIdsAndAbbreviations() {
		
		List<Team> list = null;
		
		String jpql = "select new com.app.entities.Team(id, abbreviation) from Team t";
		
		// 1. Get session from SF
		Session session = getFactory().getCurrentSession();
		// 2. Begin a Tx
		Transaction tx = session.beginTransaction();
		try {
			list = session.createQuery(jpql, Team.class).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			
			throw e;
		}
		
		return list;
	}

	@Override
	public String deleteTeamDetails(Long id) {
		Team teamToDelete=null;
		String mesg="deleting team details failed !!!!";
		// 1. Get session from SF
		Session session = getFactory().getCurrentSession();
		// 2. Begin a Tx
		Transaction tx = session.beginTransaction();
		try {
			//get teamToDelete by it's id
			teamToDelete=session.get(Team.class, id);
			if(teamToDelete != null)
			{
				//teamToDelete : persistent 
				session.delete(teamToDelete);//emp : REMOVED (simply marked for removal)
				mesg="deleted team details ..."+teamToDelete.getName()+" ("+teamToDelete.getAbbreviation()+")";
			}
			tx.commit();//DML : delete , L1 cache is destroyed
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		//teamToDelete : transient
		return mesg;
	}//teamToDelete : marked for GC

	@Override
	public Team getTeamDetailsByTeamId(Long teamId) {
		Team team = null;// emp : does not exist in heap !
		// 1. Get session from SF
		Session session = getFactory().getCurrentSession();
		// 2. Begin a Tx
		Transaction tx = session.beginTransaction();
		try {
			team = session.get(Team.class, teamId);// select

			// in case of valid id -- team : PERSISTENT (exists in cache n db)
			// tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return team;// team : DETACHED from L1 cache
	}

	@Override
	public String updateMaxAgeAndBatAvgByTeamName(String name, int maxAge, double battingAvg) {
		String mesg = "New Max Age and Batting avg updation failed !!!!!";
		Team team = null;
		
		String jpql = "select t from Team t where t.name = :teamName";
		// 1. Get session from SF
		Session session = getFactory().getCurrentSession();
		// 2. Begin a Tx
		Transaction tx = session.beginTransaction();
		try {
			team = session.createQuery(jpql, Team.class).setParameter("teamName", name).getSingleResult();// throws
																											// NoResultException
																											// : in case
																											// of no
																											// result
																											// found !
			// team : PERSISTENT(exists in L1 cache n db)
			team.setMaxAge(maxAge);// modifying state of the persistent entity
			team.setBattingAverage(battingAvg);// modifying state of the persistent entity
			
			// session.evict(team);//team : DETACHED
			tx.commit();// session.flush--> dirty checking -->entity exists but with updated state --
			mesg = "New Max Age = "+maxAge+" and Batting avg = "+battingAvg+" updation successfull!";
			// DML : update --session.close()
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		// team : DETACHED from L1 cache 
		//Any changes made now will not persist in DB
		return mesg;
	}

	@Override
	public List<Team> sortByDescOrderOfMaxAgeDependingOnLessThanMaxAgeAndMinWickets(int maxAgeLimit, int minWicketsReq){
			List<Team> list = null;
	
			String jpql = "select t from Team t where :xage > maxAge and wicketsTaken > :xwicketsTaken order by maxAge desc";
			
			// 1. Get session from SF
			Session session = getFactory().getCurrentSession();
			// 2. Begin a Tx
			Transaction tx = session.beginTransaction();
			try {
				list = session.createQuery(jpql, Team.class).setParameter("xage", maxAgeLimit)
							.setParameter("xwicketsTaken", minWicketsReq).getResultList();
				tx.commit();
			} catch (RuntimeException e) {
				if (tx != null)
					tx.rollback();
				
				throw e;
			}
			
			return list;

	}

}
