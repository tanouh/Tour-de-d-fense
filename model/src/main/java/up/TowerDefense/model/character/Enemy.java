package up.TowerDefense.model.character;

import up.TowerDefense.model.object.Position;

public abstract class Enemy extends Character{
	
	/**
	 * Correspond au nombre de coins rapportes une fois l'enemy mort.
	 */
	private int coins_value;
	
	/**
	 * Correspond a l'agressivite de l'enemy (va t'il plutot attaquer les tours ou �tre plus agressif et attaquer les allies)
	 */
	private float agressiveness_degree;
	
	private float attackspeed;
	
	private float damage;
	
	private boolean suicid;
	

	/*public Enemy(Location position, int coins_value, int agressiv_degree, float size) {
		super(position, size);
		this.coins_value = coins_value;
		this.agressiveness_degree = agressiv_degree;
	}*/
	
	public Enemy(PresetEnemy presetEnemy, Position position) {
		super(position, presetEnemy.getSize(), presetEnemy.getSize(), presetEnemy.getMaxHealth(), presetEnemy.getSpeed());
		this.coins_value = presetEnemy.getCoins();
		this.agressiveness_degree = presetEnemy.getAgressiv_Degree();
	}
	
	public int getCoins_value() {
		return this.coins_value;
	}
	
	public float getAgressiv_degree() {
		return this.agressiveness_degree;
	}
	
	public void setAgressiv_degree(int newdegree) {
		this.agressiveness_degree = newdegree;
	}
}
