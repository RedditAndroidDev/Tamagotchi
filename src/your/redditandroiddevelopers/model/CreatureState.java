package your.redditandroiddevelopers.model;

public class CreatureState {

	private int id;
	private Creature creature;
	private CreatureRaiseType raiseType;
	private Sickness sickness;
	private int health;
	private int bowel;
	private int discipline;
	private int hunger;
	private int happy;
	private boolean sick;
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
	 * @return the creature
	 */
	public Creature getCreature() {
		return creature;
	}
	/**
	 * @param creature the creature to set
	 */
	public void setCreature(Creature creature) {
		this.creature = creature;
	}
	/**
	 * @return the raiseType
	 */
	public CreatureRaiseType getRaiseType() {
		return raiseType;
	}
	/**
	 * @param raiseType the raiseType to set
	 */
	public void setRaiseType(CreatureRaiseType raiseType) {
		this.raiseType = raiseType;
	}
	/**
	 * @return the sickness
	 */
	public Sickness getSickness() {
		return sickness;
	}
	/**
	 * @param sickness the sickness to set
	 */
	public void setSickness(Sickness sickness) {
		this.sickness = sickness;
	}
	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}
	/**
	 * @param health the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	/**
	 * @return the bowel
	 */
	public int getBowel() {
		return bowel;
	}
	/**
	 * @param bowel the bowel to set
	 */
	public void setBowel(int bowel) {
		this.bowel = bowel;
	}
	/**
	 * @return the discipline
	 */
	public int getDiscipline() {
		return discipline;
	}
	/**
	 * @param discipline the discipline to set
	 */
	public void setDiscipline(int discipline) {
		this.discipline = discipline;
	}
	/**
	 * @return the hunger
	 */
	public int getHunger() {
		return hunger;
	}
	/**
	 * @param hunger the hunger to set
	 */
	public void setHunger(int hunger) {
		this.hunger = hunger;
	}
	/**
	 * @return the happy
	 */
	public int getHappy() {
		return happy;
	}
	/**
	 * @param happy the happy to set
	 */
	public void setHappy(int happy) {
		this.happy = happy;
	}
	/**
	 * @return the sick
	 */
	public boolean isSick() {
		return sick;
	}
	/**
	 * @param sick the sick to set
	 */
	public void setSick(boolean sick) {
		this.sick = sick;
	}
}
