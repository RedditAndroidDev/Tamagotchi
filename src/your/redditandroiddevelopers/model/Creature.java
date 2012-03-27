package your.redditandroiddevelopers.model;

import java.util.Date;

public class Creature {

	private int id;
	private String name;
	private CreatureType type;
	private Date birthDate;
	private Date deathDate;
	private boolean alive;
	private Gender gender;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}
	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	/**
	 * @return the deathDate
	 */
	public Date getDeathDate() {
		return deathDate;
	}
	/**
	 * @param deathDate the deathDate to set
	 */
	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}
	/**
	 * @return the alive
	 */
	public boolean isAlive() {
		return alive;
	}
	/**
	 * @param alive the alive to set
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	/**
	 * @return the type
	 */
	public CreatureType getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(CreatureType type) {
		this.type = type;
	}
}
