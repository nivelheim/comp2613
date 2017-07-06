/**
 * Project: Gis
 * File: ApplicationException.java
 * Date: Mar 30, 2016
 * Time: 1:22:25 PM
 */

/**
 * @author Ki Jun Joseph Jung A00918606
 *
 * Data access object for scores
 */
package a00918606.gis.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00918606.gis.ApplicationException;
import a00918606.gis.data.Database;
import a00918606.gis.data.ReportObject;
import a00918606.gis.data.Score;


public class ScoreDao extends Dao {

	private static Logger LOG = LogManager.getLogger(ScoreDao.class.getName());

	public static final String TABLE_NAME = "Scores";
	public static final String PERSONAID_COLUMN_NAME = "personaId";
	public static final String GAMEID_COLUMN_NAME = "gameId";
	public static final String WINLOSS_COLUMN_NAME = "winLoss";
	public static final int MAX_GAMEID_LENGTH = 4;

	public ScoreDao(Database database) {
		super(database, TABLE_NAME);
	}

	@Override
	public void create() throws SQLException {
		LOG.debug("Creating database table " + TABLE_NAME);

		String sqlString = String
				.format("CREATE TABLE %s(%s INTEGER, %s VARCHAR(%d), %s BOOLEAN)",
						TABLE_NAME, PERSONAID_COLUMN_NAME, GAMEID_COLUMN_NAME, MAX_GAMEID_LENGTH, WINLOSS_COLUMN_NAME);

		super.create(sqlString);
	}

	public void add(Score score) throws SQLException {
		String sqlString = String.format("INSERT INTO %s values(%d, '%s', %b)", TABLE_NAME,
				score.getPersonaId(), score.getGameId(), score.isWin());
		int rowCount = super.add(sqlString);
		LOG.debug(String.format("Added %d rows", rowCount));
	}

	/**
	 * Update the player.
	 * 
	 * @param player
	 * @throws SQLException
	 */
	public void update(Score score) throws SQLException {
		String sqlString = String.format("UPDATE %s SET %s='%s', %s='%b' WHERE %s=%d",
				TABLE_NAME, GAMEID_COLUMN_NAME, score.getGameId(), WINLOSS_COLUMN_NAME, score.isWin(),
				PERSONAID_COLUMN_NAME, score.getPersonaId());
		LOG.debug("Update statment: " + sqlString);

		int rowCount = super.add(sqlString);
		LOG.debug(String.format("Updated %d rows", rowCount));
	}

	/**
	 * Delete the player from the database.
	 * 
	 * @param player
	 * @throws SQLException
	 */
	public void delete(Score score) throws SQLException {
		Connection connection;
		Statement statement = null;
		try {
			connection = Database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sqlString = String.format("DELETE FROM %s WHERE %s='%d'", TABLE_NAME, PERSONAID_COLUMN_NAME,
					score.getPersonaId());
			LOG.debug(sqlString);
			int rowcount = statement.executeUpdate(sqlString);
			LOG.debug(String.format("Deleted %d rows", rowcount));
		} finally {
			close(statement);
		}
	}

	/**
	 * Retrieve all the lastNames from the database
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Integer> getPersonaIds() throws SQLException {
		List<Integer> personaIds = new ArrayList<Integer>();

		String selectString = String.format("SELECT * FROM %s", TABLE_NAME);
		LOG.debug(selectString);
		
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			Connection connection = Database.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(selectString);

			while (resultSet.next()) {
				if (!(personaIds.contains(resultSet.getInt(PERSONAID_COLUMN_NAME)))) {
					personaIds.add(resultSet.getInt(PERSONAID_COLUMN_NAME));
				}
				
				else {
				}
				
	
			}

		} finally {
			close(statement);
		}

		LOG.debug(String.format("Loaded %d last names from the database", personaIds.size()));
		
		Collections.sort(personaIds, new Comparator<Integer>(){
		      public int compare(Integer obj1, Integer obj2)
		      {
		            // TODO Auto-generated method stub
		            return obj1.compareTo(obj2);
		      }
		});
		
		return personaIds;
	}
	
	
	/**
	 * @param actionCommand
	 * @return
	 * @throws ApplicationException
	 */
	public Score getScore(Integer personaid, String gameid) throws Exception {
		String selectString = String.format("SELECT * FROM %s WHERE %s = %d AND %s = '%s'", TABLE_NAME, PERSONAID_COLUMN_NAME,
				personaid, GAMEID_COLUMN_NAME, gameid);
		LOG.debug(selectString);

		Statement statement = null;
		ResultSet resultSet = null;
		try {
			Connection connection = Database.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(selectString);

			int count = 0;
			while (resultSet.next()) {
				count++;
				if (count > 1) {
					throw new ApplicationException(String.format("Expected one result, got %d", count));
				}
				
				Score score = new Score();
				score.setPersonaId(resultSet.getInt(PERSONAID_COLUMN_NAME));
				score.setGameId(resultSet.getString(GAMEID_COLUMN_NAME));
				score.setWin(resultSet.getBoolean(WINLOSS_COLUMN_NAME));
			
				
				return score;
			}
		} finally {
			close(statement);
		}

		return null;
	}
	
	public List<Score> getScores() throws Exception {
		List<Score> scores = new ArrayList<Score>();
		String selectString = String.format("SELECT * FROM %s", TABLE_NAME, PERSONAID_COLUMN_NAME);
		LOG.debug(selectString);

		Statement statement = null;
		ResultSet resultSet = null;
		try {
			Connection connection = Database.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(selectString);

			while (resultSet.next()) {		
				Score score = new Score();
				score.setPersonaId(resultSet.getInt(PERSONAID_COLUMN_NAME));
				score.setGameId(resultSet.getString(GAMEID_COLUMN_NAME));
				score.setWin(resultSet.getBoolean(WINLOSS_COLUMN_NAME));
			
				
				scores.add(score);
			}
		} finally {
			close(statement);
		}

		return scores;
	}
	
	
	public List<ReportObject> getObjects() throws Exception {
		List<ReportObject> objects = new ArrayList<ReportObject>();
		String selectString = String.format("SELECT Scores.gameID,Scores.personaID, Personas.platform FROM Scores INNER JOIN Personas ON Scores.personaID = Personas.personaID GROUP BY Scores.gameID, Scores.personaID, Personas.platform");
		LOG.debug(selectString);

		Statement statement = null;
		ResultSet resultSet = null;
		try {
			Connection connection = Database.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(selectString);

			while (resultSet.next()) {		
				ReportObject obj = new ReportObject();
				obj.setPersonaId(resultSet.getInt(PERSONAID_COLUMN_NAME));
				obj.setGameId(resultSet.getString(GAMEID_COLUMN_NAME));
				obj.setPlatform(resultSet.getString("platform"));
				
				objects.add(obj);
			}
		} finally {
			close(statement);
		}

		return objects;
	}
	

}
