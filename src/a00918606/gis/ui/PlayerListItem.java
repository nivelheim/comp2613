/*
 * Project: Gis
 * File: PlayerListItem.java
 * Date: Mar 30, 2016
 * Time: 1:22:25 PM
 */

/*
 * @author Ki Jun Joseph Jung A00918606
 * 
 * Class PlayerListItem models list of players which contains player objects
*/

package a00918606.gis.ui;

import a00918606.gis.data.Player;


public class PlayerListItem {

	private Player player;

	/**
	 * @param player
	 */
	public PlayerListItem(Player player) {
		this.player = player;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player
	 *            the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public String toString() {
		if (player == null) {
			return null;
		}


		return player.getId() + " " + player.getFirstName() + " " + player.getLastName();
	}
}
