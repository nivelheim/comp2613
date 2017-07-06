/**
 * Project: Gis
 * File: PersonaDao.java
 * Date: Mar 30, 2016
 * Time: 1:22:25 PM
 */

/**
 * @author Ki Jun Joseph Jung A00918606
 *
 * Data access object for personas
 */
package a00918606.gis.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00918606.gis.ApplicationException;
import a00918606.gis.data.Database;
import a00918606.gis.data.Persona;


public class PersonaDao extends Dao {

	private static Logger LOG = LogManager.getLogger(PersonaDao.class.getName());

	public static final String TABLE_NAME = "Personas";
	public static final String PERSONAID_COLUMN_NAME = "personaId";
	public static final String PLAYERID_COLUMN_NAME = "playerId";
	public static final String GAMERTAG_COLUMN_NAME = "gamerTag";
	public static final String PLATFORM_COLUMN_NAME = "platform";
	public static final int MAX_GAMERTAG_LENGTH = 40;
	public static final int MAX_PLATFORM_LENGTH = 2;

	public PersonaDao(Database database) {
		super(database, TABLE_NAME);
	}

	@Override
	public void create() throws SQLException {
		LOG.debug("Creating database table " + TABLE_NAME);

		String sqlString = String
				.format("CREATE TABLE %s(%s INTEGER, %s INTEGER, %s VARCHAR(%d), %s VARCHAR(%d), PRIMARY KEY (%s))",
						TABLE_NAME, PERSONAID_COLUMN_NAME, PLAYERID_COLUMN_NAME, GAMERTAG_COLUMN_NAME, MAX_GAMERTAG_LENGTH,
						PLATFORM_COLUMN_NAME, MAX_PLATFORM_LENGTH, PERSONAID_COLUMN_NAME);

		super.create(sqlString);
	}

	public void add(Persona persona) throws SQLException {
		String sqlString = String.format("INSERT INTO %s values(%d, %d, '%s', '%s')", TABLE_NAME,
				persona.getId(), persona.getPlayerId(), persona.getGamerTag(), persona.getPlatform());
		int rowCount = super.add(sqlString);
		LOG.debug(String.format("Added %d rows", rowCount));
	}

	/**
	 * Update the player.
	 * 
	 * @param player
	 * @throws SQLException
	 */
	public void update(Persona persona) throws SQLException {
		String sqlString = String.format("UPDATE %s SET %d='%d', %d='%d', %s='%s', %s='%s' WHERE %s=%d",
				TABLE_NAME, PERSONAID_COLUMN_NAME, persona.getId(), PLAYERID_COLUMN_NAME, persona.getPlayerId(),
				GAMERTAG_COLUMN_NAME, persona.getGamerTag(), PLATFORM_COLUMN_NAME, persona.getPlatform());
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
	public void delete(Persona Persona) throws SQLException {
		Connection connection;
		Statement statement = null;
		try {
			connection = Database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sqlString = String.format("DELETE FROM %s WHERE %s='%s'", TABLE_NAME, GAMERTAG_COLUMN_NAME,
					Persona.getGamerTag());
			LOG.debug(sqlString);
			int rowcount = statement.executeUpdate(sqlString);
			LOG.debug(String.format("Deleted %d rows", rowcount));
		} finally {
			close(statement);
		}
	}

	/**
	 * Retrieve all the gamertags from the database
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<String> getGamertags() throws SQLException {
		List<String> gamerTags = new ArrayList<String>();

		String selectString = String.format("SELECT * FROM %s", TABLE_NAME);
		LOG.debug(selectString);
		
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			Connection connection = Database.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(selectString);

			while (resultSet.next()) {
				gamerTags.add(resultSet.getString(GAMERTAG_COLUMN_NAME));
			}

		} finally {
			close(statement);
		}

		LOG.debug(String.format("Loaded %d gamer tags from the database", gamerTags.size()));

		return gamerTags;
	}

	/**
	 * @param actionCommand
	 * @return
	 * @throws ApplicationException
	 */
	public Persona getPersona(int id) throws Exception {
		String selectString = String.format("SELECT * FROM %s WHERE %s = %d", TABLE_NAME, PERSONAID_COLUMN_NAME,
				id);
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
				
				Persona persona = new Persona();
				persona.setId(resultSet.getInt(PERSONAID_COLUMN_NAME));
				persona.setPlayerId(resultSet.getInt(PLAYERID_COLUMN_NAME));
				persona.setGamerTag(resultSet.getString(GAMERTAG_COLUMN_NAME));
				persona.setPlatform(resultSet.getString(PLATFORM_COLUMN_NAME));
				
				return persona;
			}
		} finally {
			close(statement);
		}

		return null;
	}
	
	public Persona getPersonaById(long id) throws Exception {
		String selectString = String.format("SELECT * FROM %s WHERE %s = %d", TABLE_NAME, PLAYERID_COLUMN_NAME,
				id);
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
				
				Persona persona = new Persona();
				persona.setId(resultSet.getInt(PERSONAID_COLUMN_NAME));
				persona.setPlayerId(resultSet.getInt(PLAYERID_COLUMN_NAME));
				persona.setGamerTag(resultSet.getString(GAMERTAG_COLUMN_NAME));
				persona.setPlatform(resultSet.getString(PLATFORM_COLUMN_NAME));
				
				return persona;
			}
		} finally {
			close(statement);
		}

		return null;
	}
	
	public Persona getPersonaByTag(String tag) throws Exception {
		String selectString = String.format("SELECT * FROM %s WHERE %s = '%s'", TABLE_NAME, GAMERTAG_COLUMN_NAME,
				tag);
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
				
				Persona persona = new Persona();
				persona.setId(resultSet.getInt(PERSONAID_COLUMN_NAME));
				persona.setPlayerId(resultSet.getInt(PLAYERID_COLUMN_NAME));
				persona.setGamerTag(resultSet.getString(GAMERTAG_COLUMN_NAME));
				persona.setPlatform(resultSet.getString(PLATFORM_COLUMN_NAME));
				
				return persona;
			}
		} finally {
			close(statement);
		}

		return null;
	}

}
