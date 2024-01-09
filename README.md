## Project Step-by-step Implementation :

# Project 1 : IPL (Hibernate)

## Phase 1 : Setting up Hibernate Project and Add / Insert Operation
(Understand Hibernate Architecture before proceeding)

1. Imported Maven project with dependencies
2. Edited hibernate.cfg.xml
3. Write class HibernateUtils.java
4. Write TestHibernate.java
5. Run and test if it works!         
6. Now Write Team POJO / Entity class with proper Annotations (Implementing ORM)     
(Code first approach instead of creating table in DB)
7. Now run and see if table is created successfully by Hibernate 
8. Create TeamDAO Interface and method : public String addNewTeam(Team newTeam);
9. Write TeamDAOImp (implementation of TeamDAO) class
10. Write AddNewTeam.java to call add new team (insert into DB) method of TeamDAOIMp
11. Run and test if it data is inserted into DB successfully!
### CREATE ^

## Phase 2 : Implementing other CRUD operations 
(Understood Hibernate Entity Life Cycle : Transient, Persistent, Detached and Removed , before proceeding)

1. Understand the code flow of creating Session Factory instance and getting the session and then Session methods like .save() previously, transaction commit() (internally calls session.flush() and session.close()) or rollback() and Exception handling by Hibernate at runtime in case of failure (Note - MySQL level errors, if any, handled by JDBC exceptions and further handled by Hibernate Exception as wrapper as mentioned in Hibernate Exception Java EE Docs) and associated Boiler plate code.
2. Implement method :	public List<Team> getAllTeamIdsAndAbbreviations() and corresponding tester class to test. Understand the JPQL (can also be HQL instead) and .createQuery() and .getResultList()
### READ ^
3. Implement method :	public Team getTeamDetailsByTeamId(Long teamId) and corresponding tester class to test. Understand the .get()
4. Implement method :	public String deleteTeamDetails(Long id) and corresponding tester class to test. Understand the .delete()
### DELETE ^
5. Implement method :	public String updateMaxAgeAndBatAvgByTeamName(String name, int maxAge, double battingAvg) and corresponding tester class to test. Understand the .setParameter(), .getSingleResult() and setters used.
### UPDATE ^ 
6. Implement method : public List<Team> sortByDescOrderOfMaxAgeDependingOnLessThanMaxAgeAndMinWickets(int maxAgeLimit, int minWicketsReq) and corresponding tester class to test. Understand writing complex queries and retrieving data.
