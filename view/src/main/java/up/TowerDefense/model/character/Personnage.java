package up.TowerDefense.model.character;

import up.TowerDefense.model.game.StaticFunctions;
import up.TowerDefense.model.object.Position;

import java.awt.image.BufferedImage;


public abstract class Personnage{
	private BufferedImage image;
	private BufferedImage reloadImage;
	/**
	 * position actuelle du personnage
	 */
	public Position position;
	
	/** 
	 * nombre de point de vie maximum du personnage
	 */
	protected final int maxHealth;
	
	/**
	 * nombre de point de vie actuel du personnage
	 */
	protected int currentHealth;
	
	/**
	 *  Le nombre de secondes pour effectuer un pas
	 */
	protected long speed;

	/**
	 * vitesse du personnage
	 */
	protected double velocity = 0.001;
	
	/**
	 * taille du personnage par rapport � une case (coefficient multiplicatif)
	 * */
	protected int size;
	
	/**
	 * resistance du personnage 
	 */
	protected float resistance;
	

	/**
	 * Construit un personnage de taille "size" à la position "position"
	 * 
	 * @param position definit la position du personnage
	 * @param size definit la taille du personnage
	 * @param resistance definit la resistance du personnage
	 * @param maxHealth definit la vie maximale du personnage
	 * @param speed definit la vitesse du personnage
	 */
	public Personnage(Position position, int size, float resistance, int maxHealth, long speed, String imgName, String reloadImgName) {
		this.position = position;
		this.maxHealth = maxHealth;
		this.currentHealth = this.maxHealth;
		this.speed = speed;
		this.size = size;
		this.resistance = resistance;
		image = StaticFunctions.loadImage(imgName);
		this.reloadImage = StaticFunctions.loadImage(reloadImgName);
	}

	
	/**
	 * Construit un personnage de taille "size" � une position par defaut 0.00
	 * 
	 * @param size definit la taille du personnage
	 */
	public Personnage(int size) {
		this(new Position(0.00,0.00), size, 1.00f, 100,
				1000, "/noir.png", "/noir.png");
	}
	
	public Position getPosition() {
		return this.position;
	}
	
	public float getResistance() {
		return this.resistance;
	}
	
	public long getSpeed() {
		return this.speed;
	}
	
	public int getSize() {
		return this.size;
	}

	public int getCurrentHealth() {
		return this.currentHealth;
	}
	
	public BufferedImage getImage() {
        return image;
    }
	public BufferedImage getReloadImage() {
		return reloadImage;
	}

	public boolean tookHit() {
		return false;
	}
	
	public void setSize(int newSize) {
		this.size = newSize;
	}
	
	public void setCurrentHealth(int newlifePoint) {
		this.currentHealth = newlifePoint;
	}
	
	public void setSpeed(long newSpeed) {
		this.speed = newSpeed;
	}
	
	public void setResistance(float newResistance) {
		this.resistance = newResistance;
	}
	
	public void setPosition(Position newPosition) {
		this.position = newPosition;
	}
}
