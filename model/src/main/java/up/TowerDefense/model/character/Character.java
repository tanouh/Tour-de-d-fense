package up.TowerDefense.model.character;

import up.TowerDefense.model.object.Position;

public abstract class Character implements Movable{
	
	/**
	 * position actuelle du personnage
	 */
	private Position position;
	
	/** 
	 * nombre de point de vie maximum du personnage
	 */
	private int maxHealth;
	
	/**
	 * nombre de point de vie actuel du personnage
	 */
	private int currentHealth;
	
	/**
	 * vitesse d'attaque du personnage
	 */
	private float speed;
	
	/**
	 * taille du personnage par rapport � une case
	 */
	private float size;
	
	/**
	 * resistance du personnage 
	 */
	private float resistance;
	
	/**
	 * Construit un personnage de taille "size" à la position "position"
	 * 
	 * @param position definit la position du personnage
	 * @param size definit la taille du personnage
	 */
	public Character(Position position, float size, float resistance, int maxHealth, float speed) {
		this.position = position;
		this.maxHealth = maxHealth;
		this.currentHealth = this.maxHealth;
		this.speed = speed;
		this.size = size;
		this.resistance = resistance;
	}
	
	/**
	 * Construit un personnage de taille "size" � une position par defaut 0.00
	 * 
	 * @param size definit la taille du personnage
	 */
	public Character(float size) {
		this(new Position(0.00,0.00), size, 1.00f, 100, 1.00f);
	}
	
	/**
	 * Construit un personnage de taille 0.50 (par rapport � 1 case) � la position par defaut 0.00
	 */
	public Character() {
		this(new Position(0.00,0.00), 0.50f, 1.00f, 100, 1.00f);
	}
	
	public Position getPosition() {
		return this.position;
	}
	
	public float getResistance() {
		return this.resistance;
	}
	
	public float getSpeed() {
		return this.speed;
	}
	
	public float getSize() {
		return this.size;
	}
	
	public int getlifePoint_max() {
		return this.maxHealth;
	}
	
	public int getlifePoint_current() {
		return this.currentHealth;
	}
	
	public void setSize(float newSize) {
		this.size = newSize;
	}
	
	public void setlifePoint_current(int newlifePoint) {
		this.currentHealth = newlifePoint;
	}
	
	public void setSpeed(float newSpeed) {
		this.speed = newSpeed;
	}
	
	public void setResistance(float newResistance) {
		this.resistance = newResistance;
	}
	
	public void setPosition(Position newPosition) {
		this.position = newPosition;
	}
	
}
