/**
 * Project: Gis
 * File: Game.java
 * Date: Mar 30, 2016
 * Time: 1:22:25 PM
 */

/**
 * @author Ki Jun Joseph Jung A00918606
 * 
 * Class Game that contains member data of an entity Game
 */


package a00918606.gis.data;


public class Game implements GisData {

	private String id;
	private String name;
	private String producer;

	/**
	 * Default constructor
	 */
	public Game() {
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the producer
	 */
	public String getProducer() {
		return producer;
	}

	/**
	 * @param producer
	 *            the producer to set
	 */
	public void setProducer(String producer) {
		this.producer = producer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see a00123456.gis.data.GisData#getAttributeCount()
	 */
	@Override
	public int getAttributeCount() {
		return 3;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", producer=" + producer + "]";
	}

}
