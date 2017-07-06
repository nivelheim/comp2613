/**
 * Project: Gis
 * File: GameDao.java
 * Date: Mar 30, 2016
 * Time: 1:22:25 PM
 */

/**
 * @author Ki Jun Joseph Jung A00918606
 *
 * Data access object for games
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
import a00918606.gis.data.Game;


public class GameDao extends Dao {

	private static Logger LOG = LogManager.getLogger(GameDao.class.getName());

	public static final String TABLE_NAME = "Games";
	public static final String GAMEID_COLUMN_NAME = "gameId";
	public static final String GAMENAME_COLUMN_NAME = "gameName";
	public static final String PRODUCER_COLUMN_NAME = "producer";
	public static final int MAX_CODE_LENGTH = 4;
	public static final int MAX_NAME_LENGTH = 40;
	public static final int MAX_PRODUCER_LENGTH = 40;

	public GameDao(Database database) {
		super(database, TABLE_NAME);
	}

	@Override
	public void create() throws SQLException {
		LOG.debug("Creating database table " + TABLE_NAME);

		String sqlString = String
				.format("CREATE TABLE %s(%s VARCHAR(%d), %s VARCHAR(%d), %s VARCHAR(%d), PRIMARY KEY (%s))",
						TABLE_NAME, GAMEID_COLUMN_NAME, MAX_CODE_LENGTH, GAMENAME_COLUMN_NAME, MAX_NAME_LENGTH,
						PRODUCER_COLUMN_NAME, MAX_PRODUCER_LENGTH, GAMEID_COLUMN_NAME);

		super.create(sqlString);
	}

	public void add(Game game) throws SQLException {
		String sqlString = String.format("INSERT INTO %s values('%s', '%s', '%s')", TABLE_NAME,
				game.getId(), game.getName(), game.getProducer());
		int rowCount = super.add(sqlString);
		LOG.debug(String.format("Added %d rows", rowCount));
	}

	/**
	 * Update the player.
	 * 
	 * @param player
	 * @throws SQLException
	 */
	public void update(Game game) throws SQLException {
		String sqlString = String.format("UPDATE %s SET %s='%s', %s='%s' WHERE %s=%d",
				TABLE_NAME, GAMENAME_COLUMN_NAME, game.getName(),
				PRODUCER_COLUMN_NAME, game.getProducer(), GAMEID_COLUMN_NAME, game.getId());
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
	public void delete(Game game) throws SQLException {
		Connection connection;
		Statement statement = null;
		try {
			connection = Database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sqlString = String.format("DELETE FROM %s WHERE %s='%s'", TABLE_NAME, GAMEID_COLUMN_NAME,
					game.getId());
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
	public List<String> getGameIDs() throws SQLException {
		List<String> gameIDs = new ArrayList<String>();

		String selectString = String.format("SELECT * FROM %s", TABLE_NAME);
		LOG.debug(selectString);
		
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			Connection connection = Database.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(selectString);

			while (resultSet.next()) {
				gameIDs.add(resultSet.getString(GAMEID_COLUMN_NAME));
			}

		} finally {
			close(statement);
		}

		LOG.debug(String.format("Loaded %d gamer tags from the database", gameIDs.size()));

		return gameIDs;
	}

	/**
	 * @param actionCommand
	 * @return
	 * @throws ApplicationException
	 */
	public Game getGame(String gameID) throws Exception {
		String selectString = String.format("SELECT * FROM %s WHERE %s = '%s'", TABLE_NAME, GAMEID_COLUMN_NAME,
				gameID);
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
				
				Game game = new Game();
				game.setId(resultSet.getString(GAMEID_COLUMN_NAME));
				game.setName(resultSet.getString(GAMENAME_COLUMN_NAME));
				game.setProducer(resultSet.getString(PRODUCER_COLUMN_NAME));
				
				return game;
			}
		} finally {
			close(statement);
		}

		return null;
	}

}
