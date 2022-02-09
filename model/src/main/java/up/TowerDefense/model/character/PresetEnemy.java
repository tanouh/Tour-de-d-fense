package up.TowerDefense.model.character;

public class PresetEnemy {
	
	private int coins_value;
	
	/**
	 * Correspond a l'agressivite de l'enemy (va t'il plutot attaquer les tours ou être plus agressif et attaquer les allies)
	 */
	private float agressiveness_degree;
	
	private float attackspeed;
	
	private float dammage;
	
	private boolean suicidal;
		
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
		
	public PresetEnemy(int maxHealth, float speed, int coins, float agressiveness_degree, float attackspeed, float dammage, boolean suicid, float size, float resistance) {
		this.maxHealth = maxHealth;
		this.setSpeed(speed);
		this.setCoins_value(coins);
		this.attackspeed = attackspeed;
		this.dammage = dammage;
		this.suicidal = suicid;
		this.agressiveness_degree = agressiveness_degree;
		this.size = size;
		this.currentHealth = maxHealth;
		this.resistance = resistance;
	}
	
	public static PresetEnemy Covid() {
		return new PresetEnemy(100,1.25f,10,5.00f, 1.00f, 100.00f, false, 0.5f, 1.00f);
	}

	/*public static PresetEnemy Assailant() {
		return new PresetEnemy(100, 1.25f, )
	}*/
	
	public float getSize() {
		return this.size;
	}

	public float getAgressiv_Degree() {
		return this.agressiveness_degree;
	}

	public int getCoins() {
		return coins_value;
	}

	public void setCoins_value(int coins_value) {
		this.coins_value = coins_value;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
}
