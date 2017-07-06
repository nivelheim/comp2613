/**
 * Project: Gis
 * File: Player.java
 * Date: Mar 30, 2016
 * Time: 1:22:25 PM
 */

/**
 * @author Ki Jun Joseph Jung A00918606
 * 
 * Class Player that contains member data of an entity PLayer
 */

package a00918606.gis.data;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Player implements GisData {

	private long id;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private ZonedDateTime birthDate;
	
	private Map<Long, Persona> personas;
	private List<Persona> personaList;

	/**
	 * Default constructor.
	 */
	public Player() {
		personas = new HashMap<>();
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress
	 *            the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the birthDate
	 */
	public ZonedDateTime getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(ZonedDateTime birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * Set the birthdate
	 * 
	 * @param year the year, includes the century, ex. 1967
	 * @param month the month - must be 1-based
	 * @param day the day of the month - 1-based
	 */
	public void setBirthDate(int year, int month, int day) {
		birthDate = ZonedDateTime.of(year, month, day, 0, 0, 0, 0, ZoneId.systemDefault());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see a00123456.gis.data.GisData#getAttributeCount()
	 */
	@Override
	public int getAttributeCount() {
		return 5;
	}

	/**
	 * @return the personas
	 */
	public Map<Long, Persona> getPersonas() {
		return personas;
	}


	public List<Persona> getPersonaList() {
		return personaList;
	}

	public void setPersonaList(List<Persona> personaList) {
		this.personaList = personaList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Player [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailAddress="
				+ emailAddress + ", birthDate=" + birthDate + "]";
	}

}
