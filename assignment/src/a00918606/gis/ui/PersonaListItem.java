/*
 * Project: Gis
 * File: PersonaListItem.java
 * Date: Mar 30, 2016
 * Time: 1:22:25 PM
 */

/*
 * @author Ki Jun Joseph Jung A00918606
 * 
 * Class PersonaListItem that contains all the persona objects and acts as a model object.
*/
package a00918606.gis.ui;

import a00918606.gis.data.Persona;


public class PersonaListItem {

	private Persona persona;

	/**
	 * @param persona
	 */
	public PersonaListItem(Persona p) {
		this.persona = p;
	}

	/**
	 * @return the persona
	 */
	public Persona getPersona() {
		return persona;
	}

	/**
	 * @param persona
	 *            the persona to set
	 */
	public void setPlayer(Persona player) {
		this.persona = player;
	}

	@Override
	public String toString() {
		if (persona == null) {
			return null;
		}


		return persona.getId() + ": " + persona.getGamerTag();
	}
}
