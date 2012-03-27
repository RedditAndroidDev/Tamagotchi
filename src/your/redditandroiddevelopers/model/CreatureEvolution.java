package your.redditandroiddevelopers.model;

public class CreatureEvolution {

	private int id;
	private CreatureType type;
	private String name;
	private int maxHealth;
	private int maxBowel;
	private int maxDiscipline;
	private int maxHunger;
	private int maxHappy;
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
	 * @return the maxHealth
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
	/**
	 * @param maxHealth the maxHealth to set
	 */
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	/**
	 * @return the maxBowel
	 */
	public int getMaxBowel() {
		return maxBowel;
	}
	/**
	 * @param maxBowel the maxBowel to set
	 */
	public void setMaxBowel(int maxBowel) {
		this.maxBowel = maxBowel;
	}
	/**
	 * @return the maxDiscipline
	 */
	public int getMaxDiscipline() {
		return maxDiscipline;
	}
	/**
	 * @param maxDiscipline the maxDiscipline to set
	 */
	public void setMaxDiscipline(int maxDiscipline) {
		this.maxDiscipline = maxDiscipline;
	}
	/**
	 * @return the maxHunger
	 */
	public int getMaxHunger() {
		return maxHunger;
	}
	/**
	 * @param maxHunger the maxHunger to set
	 */
	public void setMaxHunger(int maxHunger) {
		this.maxHunger = maxHunger;
	}
	/**
	 * @return the maxHappy
	 */
	public int getMaxHappy() {
		return maxHappy;
	}
	/**
	 * @param maxHappy the maxHappy to set
	 */
	public void setMaxHappy(int maxHappy) {
		this.maxHappy = maxHappy;
	}
}
