# CRUD demonstrations in different tech stacks using sample projects that I created for understanding the concepts across the Tech-Verse:
* I have also written briefly step by step implementation guide for my understanding and you can also go through it if it adds any value to your knowledge 
* Also if you find it useful, do **star (this repository)** to show appreciation, bookmark for your reference and receive updates :)

## Table of contents
* [Project 1](#project-1)

## Project 1 
### IPL (JAVA EE : Hibernate + Dynamic Web using JSP and JSTL)

#### Phase 1 : Setting up Hibernate Project and Add / Insert Operation
(Understand Hibernate Architecture before proceeding)

##### Refer IPL_Hibernate_CRUD/ (Ignore IPL_Hibernate_CRUD/Dynamic Web Project with Hibernate for now)

1. Created / Imported Maven project with dependencies
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
##### CREATE ^

#### Phase 2 : Implementing other CRUD operations 
(Understood Hibernate Entity Life Cycle : Transient, Persistent, Detached and Removed , before proceeding)

1. Understand the code flow of creating Session Factory instance and getting the session and then Session methods like .save() previously, transaction commit() (internally calls session.flush() and session.close()) or rollback() and Exception handling by Hibernate at runtime in case of failure (Note - MySQL level errors, if any, handled by JDBC exceptions and further handled by Hibernate Exception as wrapper as mentioned in Hibernate Exception Java EE Docs) and associated Boiler plate code.
2. Implement method :	public List<Team> getAllTeamIdsAndAbbreviations() and corresponding tester class to test. Understand the JPQL (can also be HQL instead) and .createQuery() and .getResultList()
##### READ ^
3. Implement method :	public Team getTeamDetailsByTeamId(Long teamId) and corresponding tester class to test. Understand the .get()
4. Implement method :	public String deleteTeamDetails(Long id) and corresponding tester class to test. Understand the .delete()
##### DELETE ^
5. Implement method :	public String updateMaxAgeAndBatAvgByTeamName(String name, int maxAge, double battingAvg) and corresponding tester class to test. Understand the .setParameter(), .getSingleResult() and setters used.
##### UPDATE ^ 
6. Implement method : public List<Team> sortByDescOrderOfMaxAgeDependingOnLessThanMaxAgeAndMinWickets(int maxAgeLimit, int minWicketsReq) and corresponding tester class to test. Understand writing complex queries and retrieving data.

#### Phase 3 : Transforming Hibernate Basic Project into Web Project 

##### Now refer IPL_Hibernate_CRUD/Dynamic Web Project with Hibernate/

1. Integrate previous hibernate project in a web maven project (Now we won’t use tester methods anymore in com.app.tester but call them from JSP pages to interact on browser)
2. Add previously written entities in com.app.entities 
3. Add previously written daos in com.app.daos 
4. Here we used HibernateSFManager class in web_app_listeners that implements ServletContextListener so that session factory is created/initialised as soon as the application starts that is defined in contextInitialized() and gets destroyed when application is close that is defined in contextDestroyed() that are called implicitly. Session is created when DAO method is invoked and closed after transaction boundary is reached (as it internally calls session.close()).
5. Drop table - teams in DB 
6. Now modify POJOs / Entities to include Relationship
7. Add BaseEntity that has common property id for Team entity and Player entity as they extend BaseEntity ( Inheritance ). Add @MappedSuperclass -> mandatory class level annotation to tell hibernate that this is a base class for other entities, do not create any table for it
8. Add references : List<Player> playersList in Team entity and Team myTeam in Players entity  .
9. Add corresponding getters and setters.
10. Add annotation @OneToMany for playersList and @ManyToOne for myTeam to specify relationship [ So now Bi-directional relationship exists -> Team has players and Player is a part of Team ] 
11. Now the problem is Hibernate will automatically create a third additional table which will have team_id and player_id so that it keeps a track of owning and non-owning fields of related table. So we explicitly specify it by using property mappedBy in @OneToMany and its value is set to name of the property as it appears in owning side of association. Thus, we added mappedBy in inverse (non-owning) side of association. (teams : one, parent, inverse (non owning side = FK on players table) AND players side: many, child, owning)
12. Use @JoinColumn to specify myTeam as foreign key in Team entity ( Specifies a column for joining an entity association or element collection ). Here name property specifies corresponding table name and nullable = false so not null.
13. In @OneToMany,  we used property cascade so that changes made in parent table should trigger changes to associated fields in child table.
14. However, if we remove a Team, so it will have cascading effect on Players table but instead of delete, their parent reference will be updated to null. So they become orphan and persist in DB. To eliminate this problem (as the players should also get deleted if team is deleted), set orphanRemoval property to true (By default, it will be false).
15. Now, modify web.xml to set index.jsp as entry point page of web application
16. Write index.jsp which has link to form page that registers a player -> add_player_form.jsp. Create a Team bean, team_bean using useBean so that we can get all team details in session scope.
17. Write add_player_form.jsp which takes player details as input and dynamically displays team abbreviation as dropdown using the team_bean of the session.

#### Phase 4 - Completing Player Registration after proper validations

1. Modify index.jsp to add Player bean state, player_bean, using useBean so that we can get player details from user in session scope.
<jsp:useBean id = "player_bean" class="com.app.beans.PlayerBean" scope="session"/>
2. Write Player bean class with constructor for initialising Player as well as Team DAO ( note that the date we get is in string form )
3. Write Player DAO interface, PlayerDao and its implementation class, PlayerDaoImp, and write method addPlayerToTeam(Integer teamId, Player newPlayer) to add a player to the team in DB and returns suitable message upon success or failure.
4. Add a Business Logic method in Player Bean, validateAndAddPlayer() to get valid team and then perform age and batting average validations of the player details input by user and add it to DB by method addPlayerToTeam() of PlayerDao ( Note date conversion needs to be handled string date conversion to LocalDate and getting the year difference by Period.between(date, LocalDate.now()).getYears() ).
5. Form in add_player_form.jsp posts the data to process_form.jsp. Also note that PlayerBean fields and add_player_form.jsp input fields should be exactly same and setters required in Player bean for data biding from form in jsp(view).
6. In process_form.jsp, set all form inputs into Player bean and to invoke matching setters, use <jsp:setProperty property="*" name="player_bean"/>
7. Here, invoke B.L. method, by EL syntax, of player bean to add new player that validates the player and also the team and gives suitable string response that should be displayed in JSP upon success or failure. Thus, registration is successful.

#### Further possible mods:
1. Centralised error handling can be introduced.
2. One to One relationship can be demonstrated by Team’s  Owner —> Team (So one more table can be introduced for Team owner which has reference in Team table’s owner attribute)
