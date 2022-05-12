package up.TowerDefense.model.character;

import up.TowerDefense.model.object.DestructibleObstacle;

public class PresetEnemy {
	
	/**
	 * Correspond a la localisation de l'image de l'ennemi par rapport au repertoire courant
	 */
	public final String imgName;
	public final String reloadImgName;
	/**
	 * Correspond au nombre de coins rapportes une fois l'enemy mort.
	 */
	private int reward;
	
	/**
	 * Correspond a l'agressivite de l'enemy (va t'il plutot attaquer les tours ou être plus agressif et attaquer les allies)
	 */
	private float agressiveness_degree;
	
	/**
	 * Correspond a la vitesse d'attaque de l'enemy
	 */
	private float attackspeed;
	
	/**
	 * Correspond aux degats de l'enemy
	 */
	private float damage;

		
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
	private double speed;
	
	/**
	 * taille du personnage par rapport à une case
	 */
	private int size;
	
	/**
	 * resistance du personnage 
	 */
	private float resistance;

	/**
	 * portée d'attaque de l'ennemi
	 */
	private int range;
	/**
	 * Temps de pause avant le prochain attaque
	 */
	private long reloadTime;

	/**
	 * type de cible
	 * */
	private DestructibleObstacle.ObsType target;
	
    /**
     * Represente le type de l'Ennemi.
     */
    protected Enemy.Type EnemyType;
	
	/**
	 * Construit le preset d'un enemy
	 * 
	 * @param maxHealth vie maximale d'un enemy
	 * @param speed vitesse d'un enemy
	 * @param coins argent raporter par un enemy
	 * @param agressiveness_degree agressivite d'un enemy
	 * @param attackspeed vitesse d'attaque d'un enemy
	 * @param damage degat d'un enemy
	 * @param size indique la taille de l'enemy par rapport a une case
	 * @param resistance indique la resistance de l'enemy contre une tour
	 */
	public PresetEnemy(int maxHealth, double speed, int coins, float agressiveness_degree, float attackspeed, float damage,
					    int size, float resistance, String imgName, String reloadImgName , DestructibleObstacle.ObsType target_obs,
					   Enemy.Type EnemyType, int range, long reloadTime) {
		this.maxHealth = maxHealth;
		this.setSpeed(speed);
		this.setReward(coins);
		this.attackspeed = attackspeed;
		this.damage = damage;
		this.agressiveness_degree = agressiveness_degree;
		this.size = size;
		this.currentHealth = maxHealth;
		this.resistance = resistance;
		this.imgName = imgName;
		this.reloadImgName = reloadImgName;
		this.target = target_obs;
		this.EnemyType = EnemyType;
		this.range=range;
		this.reloadTime = reloadTime;
	}
	
	/**
	 * Definit l'enemy Covid. 
	 * Cette enemy aura :
	 * 
	 * 100 points de vie maximale
	 * Une vitesse 0.5 fois sup�rieur a la normale
	 * Il raportera 30 coins une fois tue
	 * Une agressivite 5 fois superieur a la normale
	 * Une vitesse d'attaque normale
	 * Il fera 100 points de degats a chaque attaque
	 * Sa resistance sera très grande
	 * 
	 * @return Renvoie un objet PresetEnemy contenant toute ces informations afin de creer l'enemy
	 */
	public static PresetEnemy Covid() {
		return new PresetEnemy(100,0.0009,30,5.00f, 1.00f,
				100.00f, 1, 2.00f, "/noir.png", "/noir_touche.png",
				DestructibleObstacle.ObsType.TARGET, Enemy.Type.COVID,5, 2000);
	}

	/**
	 * Definit l'enemy Bacterium. 
	 * Cette enemy aura :
	 * 
	 * 100 points de vie maximale
	 * Une vitesse 1.25 fois superieur a la normale
	 * Il raportera 15 coins une fois tue
	 * Une agressivite normale
	 * Une vitesse d'attaque normale
	 * Il fera 10 points de degats a chaque attaque
	 * Sa resistance sera normale
	 * 
	 * @return Renvoie un objet PresetEnemy contenant toute ces informations afin de creer l'enemy
	 */
	public static PresetEnemy Bacterium() {
		return new PresetEnemy(100, 0.001, 15, 1.00f, 1.00f,
				10.00f,  1, 1.00f, "/bacterium.png", "/bacterium_touche.png",
				DestructibleObstacle.ObsType.TARGET, Enemy.Type.BACTERIUM,5,2000);
	}
	
	/**
	 * Definit l'enemy Virus. 
	 * Cette enemy aura :
	 * 
	 * 100 points de vie maximale
	 * Une vitesse très rapide
	 * Il raportera 20 coins une fois tue
	 * Une agressivite 1.5 fois superieur a la normale
	 * Une vitesse d'attaque normale
	 * Il fera 15 points de degats a chaque attaque
	 * Sa taille corespondra a 0.5 fois une case
	 * Sa resistance sera 1.25 fois superieur a la normale
	 * 
	 * @return Renvoie un objet PresetEnemy contenant toute ces informations afin de creer l'enemy
	 */
	public static PresetEnemy Virus() {
		return new PresetEnemy(100, 0.002, 20, 1.50f, 1.00f,
				15.00f,  1, 1.25f, "/icontest.png", "/icontest_touche.png",
				DestructibleObstacle.ObsType.TARGET, Enemy.Type.VIRUS,5,2000);
	}

	/**
	 * Definit l'enemy Fungus. 
	 * Cette enemy aura :
	 * 
	 * 100 points de vie maximale
	 * Une vitesse 2 fois plus petite que la normale
	 * Il raportera 5 coins une fois tue
	 * Une agressivite 1.75 fois superieur a la normale
	 * Une vitesse d'attaque normale
	 * Il fera 5 points de degats a chaque attaque
	 * Sa taille corespondra a 0.75 fois une case
	 * Sa resistance sera 1.75 fois superieur a la normale
	 * 
	 * @return Renvoie un objet PresetEnemy contenant toute ces informations afin de creer l'enemy
	 */
	public static PresetEnemy Fungus() {
		return new PresetEnemy(100, 0.00075, 5, 1.75f, 1.00f,
				5.00f,  1, 2.0f, "/fungus.png", "/fungus_touche.png",
				DestructibleObstacle.ObsType.TOWER, Enemy.Type.FUNGUS,2,1000);
	}
	
	/**
	 * Definit l'enemy Parasite. 
	 * Cette enemy aura :
	 * 
	 * 100 points de vie maximale
	 * Une vitesse 1.5 fois superieur a la normale
	 * Il raportera 40 coins une fois tue
	 * Une agressivite 1.25 fois superieur a la normale
	 * Une vitesse d'attaque normale
	 * Il fera 20 points de degats a chaque attaque
	 * Sa taille corespondra a 0.5 fois une case
	 * Sa resistance sera 0.75 fois superieur a la normale
	 * 
	 * @return Renvoie un objet PresetEnemy contenant toute ces informations afin de creer l'enemy
	 */
	public static PresetEnemy Parasite() {
		return new PresetEnemy(100, 0.001, 40, 1.25f, 1.00f,
				50.00f,  1, 1.75f, "/noir.png", "/noir_touche.png",
				DestructibleObstacle.ObsType.TOWER, Enemy.Type.PARASITE,5,2000);
	}
	
	public int getSize() {
		return this.size;
	}

	public float getAgressiv_Degree() {
		return this.agressiveness_degree;
	}

	public int getCoins() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public float getResistance() {
		return resistance;
	}

	public float getAttackspeed() {
		return attackspeed;
	}

	public float getDamage() {
		return damage;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public DestructibleObstacle.ObsType getTarget(){
		return target;
	}

	public int getRange() { return range;
	}

	public long getReloadTime() {
		return reloadTime;
	}
}
