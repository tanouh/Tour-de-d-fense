package up.TowerDefense.model.object;

import up.TowerDefense.model.object.Tower.Type;

public class PresetTower {
	
	/**
	 * Correspond a la localisation de l'image de la tour par rapport au repertoire courant
	 */
	public final String imgName;
	
	/**
	 * Represente la taille d'une tour sur la carte.
	 */
	public static final int SIZE = 2;

    /**
     * Represente la porte d'attaque de la Tour.
     */
    protected double range;
    
    /**
     * Represente la puissance de la Tour (le nombre de degats quelle fait).
     */
    protected double power;
    
    /**
     * Represente le niveau de la Tour (En augmentant certain de ses attributs augmentent).
     */
    protected int level = 1;
    
    /**
     * Represente le coefficient d'augmentation de la portee et de la puissance lors d'une amelioration.
     */
    private final static double modifierIncrease = 1.5;
    
    /**
     * Represente le niveau maximal de la Tour.
     */
    protected final static int MAX_LEVEL = 5 ; //à déterminer
    
    /**
     * Represente le cout de l'amelioration.
     */
    protected double upgradeCost;
    
    /**
     * Represente le nombre de point de vie initiale de la Tour.
     */
    protected final static int STARTING_HEALTH = 100 ; //à voir
    
    /**
     * Represente le temps d'attente entre chaque attaque de la Tour.
     */
    protected double reloadTime; // temps de charge avant de pouvoir attaquer de nouveau
    
    /**
     * Represente le moment de la derniere attaque de la Tour (En fonction du temps de jeu au moments de l'attaque).
     */
    protected long lastAttackTime;
    
    /**
     * Represente le type de la Tour.
     */
    protected Type towerType;

	/**
	 * Image signifiant que la tour a été touchée par la cible d'un ennemi
	 */
	protected String reloadImage;
    
    /**
     * Represente le prix la tour lors de l'achat d'une nouvelle Tour.
     */
    protected double price;

	/**
	 * Détermine si les attaques de la tour freeze les ennemis ou pas
	 */
	protected boolean freezingAttack;

    /**
     * Construit le preset d'une tour
     * 
     * @param BuyingCost est le prix d'une tour lors d'un premier achat.
     * @param startingRange est la distance a laquelle la tour peut attaquer un ennemi.
     * @param startingPower respresente le nombre de degat que la tour fait a un ennemi.
     * @param startingUpgradeCost represente le prix de l'amelioration de la tour.
     * @param startingReloadTime represente le temps d'attente avant la prochaine attaque.
     * @param LastAttackTime represente le temps de jeu a la derniere attaque.
     * @param towerType represente le type de la Tour (TOWERTEST, ANTI_CHAMPIS, LEUCOCYTE_T, ANTICORPS)
     * @param image est l'image qui sera affiche dans le jeu pour cette tour.
     */
    public PresetTower(double BuyingCost, double startingRange, double startingPower, int startingUpgradeCost, double startingReloadTime, 
    					boolean freezing, long LastAttackTime, Type towerType, String image, String reloadImage) {
    	this.price = BuyingCost;
    	this.range = startingRange;
    	this.power = startingPower;
    	this.upgradeCost = startingUpgradeCost;
    	this.reloadTime = startingReloadTime;
		this.freezingAttack = freezing;
    	this.lastAttackTime = LastAttackTime;
    	this.towerType = towerType;
    	this.imgName = image;
		this.reloadImage = reloadImage;
    }
    
    /**
     * Definit la Tour TowerTest.
     * Cette Tour aura :
     * 
     * -Un cout d'achat de 100 coins
     * -Une distance d'attaque de 5
     * -Une puissance d'attaque de 2
     * -Un cout d'amelioration de 200 coins
     * -Un temps entre chaque attaque de 1000
     * -Un temps de l'attaque precedente de 0
     * -Le type TowerTest
     * -Un affichage determiner par le fichier tour.png
     * 
     * @return Renvoie un objet PresetTower contenant toute ces informations afin de creer la Tour
     */
    public static PresetTower TowerTest() {
		return new PresetTower(100,10,200,200,
				2000, false, 0, Tower.Type.TOWERTEST, "/tour.png",
				"/tour_touche.png");
	}
    
    /**
     * Definit la Tour TowerTest.
     * Cette Tour aura :
     * 
     * 
     * @return Renvoie un objet PresetTower contenant toute ces informations afin de creer la Tour
     */
    public static PresetTower Anti_champis() {
		return new PresetTower(100,10,200,200,
				2000, false, 0, Tower.Type.ANTI_CHAMPIS, "/Anti_Champi.png",
				"/Anti_Champi_Touche.png");
	}
    
    /**
     * Definit la Tour TowerTest.
     * Cette Tour aura :
     * 
     * 
     * @return Renvoie un objet PresetTower contenant toute ces informations afin de creer la Tour
     */
    public static PresetTower Leucocyte_T() {
		return new PresetTower(100,10,200,200,
				2000, false, 0, Tower.Type.LEUCOCYTE_T, "/LeucoT.png",
				"/leuco_touche.png");
	}
    
    /**
     * Definit la Tour TowerTest.
     * Cette Tour aura :
     * 
     * 
     * @return Renvoie un objet PresetTower contenant toute ces informations afin de creer la Tour
     */
    public static PresetTower Anticorps() {
		return new PresetTower(100,10,50,200,
				2000, true, 0, Tower.Type.ANTICORPS, "/Anticorps.png",
				"/antic-touche.png");
	}
    
	public int getSize() {
		return PresetTower.SIZE;
	}

	public double getRange() {
		return this.range;
	}

	public double getPower() {
		return this.power;
	}

	public double getPrice(){ return price; }

	public double getUpgradeCost() {
		return this.upgradeCost;
	}

	public double getReloadTime() {
		return this.reloadTime;
	}

	public boolean isFreezingAttack() {
		return freezingAttack;
	}

	public long getLastAttackTime() {
		return this.lastAttackTime;
	}

	public Type getTowerType() {
		return this.towerType;
	}

    
}
