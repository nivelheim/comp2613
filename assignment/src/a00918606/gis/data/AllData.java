
package a00918606.gis.data;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00918606.gis.ApplicationException;
import a00918606.gis.io.GameReader;
import a00918606.gis.io.PersonaReader;
import a00918606.gis.io.PlayerReader;
import a00918606.gis.io.ScoreReader;
import a00918606.gis.data.Player;

/**
 * Project: Gis
 * File: AllData.java
 * Date: Mar 30, 2016
 * Time: 1:22:25 PM
 */

/**
 * @author Ki Jun Joseph Jung A00918606
 * 
 * Class AllData to load all data from existing files in the directory
 */
public class AllData {

	public static final String PLATFORMS[] = { "AN", "IO", "PC", "PS", "XB" };

	private static final Logger LOG = LogManager.getLogger(AllData.class);

	private Set<String> platforms = new HashSet<String>();
	private List<Game> games;
	private List<Player> players;
	private List<Persona> personas;
	private List<Score> scores;
	
	public AllData() {
		platforms = new HashSet<String>(Arrays.asList(PLATFORMS));
	}

	/**
	 * @throws ApplicationException
	 * @throws SQLException 
	 * 
	 */
	public void loadData() throws ApplicationException, SQLException {
		LOG.debug("loading the data");
		games = GameReader.read();
		players = PlayerReader.read();
		personas = PersonaReader.read();
		scores = ScoreReader.read();
		

		
	}
	/**
	 * @return the platforms
	 */
	public Set<String> getPlatforms() {
		return platforms;
	}

	/**
	 * @return the games
	 */
	public List<Game> getGames() {
		return games;
	}

	/**
	 * @return the players
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * @return the personas
	 */
	public List<Persona> getPersonas() {
		return personas;
	}

	/**
	 * @return the scores
	 */
	public List<Score> getScores() {
		return scores;
	}

	/**
	 * @return the allGamesLeaderboard
	 */

}
