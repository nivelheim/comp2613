/*
 * Project: Gis
 * File: TotalReport.java
 * Date: Mar 30, 2016
 * Time: 1:22:25 PM
 */

/*
 * @author Ki Jun Joseph Jung A00918606
 * 
 * Class TotalReport that generates the total number of each games played
*/

package a00918606.gis.io;

import java.util.ArrayList;
import java.util.List;

import a00918606.gis.dao.*;
import a00918606.gis.data.*;



public class TotalReport {

	/**
	 * @param args
	 */
	ScoreDao scoreDao;
	GameDao gameDao;
	
	public TotalReport(ScoreDao sd, GameDao gd) {
		scoreDao = sd;
		gameDao = gd;
	}

	public String generateReport() throws Exception {
		List<String> games = gameDao.getGameIDs();
		
		List<Score> scoreList = new ArrayList<Score>();
		List<String> report = new ArrayList<String>();
		
		
		String result = new String();
		

		List<Score> tempList = scoreDao.getScores();
		for (int j = 0; j<tempList.size(); j++) {
			scoreList.add(tempList.get(j));
		}
		
		for (String g : games) {
			int gameCount = 0;
			
			for (Score s : scoreList){
				String tmp = s.getGameId();			
				if (tmp.equals(g)) {
					gameCount++;
				}
			}
			
			report.add(gameDao.getGame(g).getName() + " played " + gameCount + " times");
		}
		
		for (String s : report) {
			result = result + s + "\r\n";
		}
		
		return result;
		
		
	}

	
	
	
	
	
	
	
	
	
}