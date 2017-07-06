/*
 * Project: Gis
 * File: PlayersReport.java
 * Date: Mar 30, 2016
 * Time: 1:22:25 PM
 */

/*
 * @author Ki Jun Joseph Jung A00918606
 * 
 * Class PlayersReport that generates report of Players objects
*/
package a00918606.gis.io;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00918606.gis.dao.GameDao;
import a00918606.gis.dao.PersonaDao;
import a00918606.gis.dao.PlayerDao;
import a00918606.gis.dao.ScoreDao;
import a00918606.gis.data.*;

@SuppressWarnings("unused")
public class PlayersReport {
	private List<Score> scores;
	private List<ReportObject> objects;

	/**
	 * Generate the lines for the player report.
	 * 
	 * @param allData
	 * @throws Exception 
	 */
	public PlayersReport(PlayerDao pl, GameDao gd, ScoreDao sd, PersonaDao pd) throws Exception {
		scores = new ArrayList<Score>();
		objects = new ArrayList<ReportObject>();
		
		scores = sd.getScores();
		objects = sd.getObjects();
		
		for (ReportObject o : objects) { 
			for (Score s : scores) {
				if (o.getGameId().equals(s.getGameId()) && o.getPersonaId() == s.getPersonaId()) {
					if (s.isWin() == true) {
						o.addWin();
					}
					
					else {
						o.addLoss();
					}
				}
			}
		}
		
		for (ReportObject o: objects) {
			Game game = new Game();
			Persona persona = new Persona();
			
			game = gd.getGame(o.getGameId());
			persona = pd.getPersona(o.getPersonaId());
			
			o.setGameName(game.getName());
			o.setGamerTag(persona.getGamerTag());
		
		}
		
		for (ReportObject o: objects) {
			o.setTotalGame(o.getWin() + o.getLoss());	
		}
		
		
	
	}


	/**
	 * Prints the Game Information System report, which is effectively a leaderboard. An interesting fact is that
	 * OutputStream, which is the parent of FilterOutputStream, which is the parent of PrintStream, was written by
	 * Arthur van Hoff. See http://en.wikipedia.org/wiki/Arthur_van_Hoff for an interesting story of where the guy who
	 * wrote OutputStream.java went.
	 * 
	 * @param out
	 * @param gis
	 */
	public List<ReportObject> returnArray() {
		return this.objects;
	}
	


	
	
}
