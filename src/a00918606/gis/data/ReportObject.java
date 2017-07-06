/**
 * Project: Gis
 * File: ReportObject.java
 * Date: Mar 30, 2016
 * Time: 1:22:25 PM
 */

/**
 * @author Ki Jun Joseph Jung A00918606
 * 
 * Class ReportObject that contains member data of an entity ReportObject
 */

package a00918606.gis.data;


public class ReportObject {

	private String gamerTag;
	private String gameName;
	private String platform;
	private String gameId;
	
	private int personaId;
	private int win;
	private int loss;
	private int totalGame;

	/**
	 * Default constructor.
	 */
	public ReportObject() {
	}

	/**
	 * @return the id
	 */
	public String getGamerTag() {
		return gamerTag;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setGamerTag(String tag) {
		this.gamerTag = tag;
	}

	/**
	 * @return the firstName
	 */
	public String getGameName() {
		return gameName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setGameName(String gn) {
		this.gameName = gn;
	}

	/**
	 * @return the lastName
	 */
	public String getPlatform() {
		return platform;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setPlatform(String pf) {
		this.platform = pf;
	}

	/**
	 * @return the emailAddress
	 */
	public int getWin() {
		return win;
	}

	/**
	 * @param emailAddress
	 *            the emailAddress to set
	 */
	public void setWin(int win) {
		this.win = win;
	}

	/**
	 * @return the birthDate
	 */
	public int getLoss() {
		return loss;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setLoss(int loss) {
		this.loss = loss;
	}
	
	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public int getPersonaId() {
		return personaId;
	}

	public void setPersonaId(int personaId) {
		this.personaId = personaId;
	}
	
	public void addWin() {
		this.win += 1;
	}
	
	public void addLoss() {
		this.loss += 1;
	}

	public int getTotalGame() {
		return totalGame;
	}

	public void setTotalGame(int totalGame) {
		this.totalGame = totalGame;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Player [tag=" + gamerTag + ", gamename=" + gameName + "]";
	}
}
