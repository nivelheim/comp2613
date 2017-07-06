/*
 * Project: Gis
 * File: ScoreListItem.java
 * Date: Mar 30, 2016
 * Time: 1:22:25 PM
 */

/*
 * @author Ki Jun Joseph Jung A00918606
 * 
 * Class ScoreListItem models list of scores which contains score objects
*/

package a00918606.gis.ui;


import a00918606.gis.data.Score;

/**
 * @author Fred Fish, A00123456
 *
 */
public class ScoreListItem {

	private Score score;

	/**
	 * @param player
	 */
	public ScoreListItem(Score score) {
		this.score = score;
	}

	/**
	 * @return the score
	 */
	public Score getPlayer() {
		return score;
	}

	/**
	 * @param score
	 * 
	 */
	public void setScore(Score score) {
		this.score = score;
	}

	@Override
	public String toString() {
		String temp = new String();
		if (score == null) {
			return null;
		}
		
		if (score.isWin() == true) {
			temp = "Persona #" + score.getPersonaId() + " won " + score.getGameId();
		}
		
		else {
			temp = "Persona #" + score.getPersonaId() + " lost " + score.getGameId();
		}
		
		return temp;
	}
}
