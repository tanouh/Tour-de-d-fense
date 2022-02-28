package up.TowerDefense.model.character;

import up.TowerDefense.model.object.Position;
import up.TowerDefense.model.object.DestructibleObstacle;
import up.TowerDefense.model.object.Tower;
import up.TowerDefense.model.object.Obstacle;

public abstract class Enemy extends Character implements Movable{
	
	/**
	 * Correspond au nombre de coins rapportes une fois l'enemy mort.
	 */
	private int coins_value;
	
	/**
	 * Correspond a l'agressivite de l'enemy (va t'il plutot attaquer les tours ou etre plus agressif et attaquer les allies)
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
	 * Determine si l'enemy est suicidaire ou non (s'il meurt des sa premiere attaque ou pas).
	 */
	private boolean suicidal;
	
	/**
	 * Represente un deplacement de l'enemy vers la gauche en fonction de sa vitesse
	 */
	private final Position LEFT = new Position(this.getPosition().x - this.getSpeed(), this.getPosition().y);
	
	/**
	 * Represente un deplacement de l'enemy vers la droite en fonction de sa vitesse
	 */
	private final Position RIGHT = new Position(this.getPosition().x + this.getSpeed(), this.getPosition().y);
	
	/**
	 * Represente un deplacement de l'enemy vers le haut en fonction de sa vitesse
	 */
	private final Position UP = new Position(this.getPosition().x, this.getPosition().y + this.getSpeed());
	
	/**
	 * Represente un deplacement de l'enemy vers le bas en fonction de sa vitesse
	 */
	private final Position DOWN = new Position(this.getPosition().x, this.getPosition().y - this.getSpeed());
	
	/**
	 * Construit un enemy de taille "size" Ã  la position "position"
	 * 
	 * @param position Definit la position de l'ennemy
	 * @param coins_value Correspond au nombre de coins que rapporte par l'enemy.
	 * @param agressiv_degree Correspond au degre d'agressivite de l'enemy
	 * @param size Correspond a la taille de l'enemy
	 */
	/*public Enemy(Location position, int coins_value, int agressiv_degree, float size) {
		super(position, size);
		this.coins_value = coins_value;
		this.agressiveness_degree = agressiv_degree;
	}*/
	
	/**
	 * Construit un enemy a la position "position" a partir des informations d'un PresetEnemy
	 * 
	 * @param presetEnemy Contient toute les informations concernant l'enemy notament son degre d'agressivite ou sa vitesse.
	 * @param position Definit la position de l'enemy
	 */
	public Enemy(PresetEnemy presetEnemy, Position position) {
		super(position, presetEnemy.getSize(), presetEnemy.getResistance(), presetEnemy.getMaxHealth(), presetEnemy.getSpeed());
		this.coins_value = presetEnemy.getCoins();
		this.agressiveness_degree = presetEnemy.getAgressiv_Degree();
		this.attackspeed = presetEnemy.getAgressiv_Degree();
		this.damage = presetEnemy.getDammage();
		this.suicidal = presetEnemy.isSuicidal();
	}
	
	/**
	 * L'ennemi attaque un obstacle destructible "target"
	 *
	 * @param target Represente l'obstacle cible de l'ennemi (tour ou autre)
	 */
	public void attackObstacle(DestructibleObstacle target) {
		target.setCurrentHealth(target.getCurrentHealth()-(int)this.damage);
	}
	
	/**
	 * L'ennemi attaque un allié "target".
	 * 
	 * @param target Represente l'allié cible de l'ennemi
	 */
	public void attackAlly(Ally target) {
		target.setlifePoint_current(target.getlifePoint_current()-(int)(this.damage/target.getResistance()));
	}
	
	/**
	 * L'enemy soigne un autre enemy "target".
	 * 
	 * @param target Represente l'enemy soigné par l'enemy courant.
	 */
	private void heal(Enemy target) {
		target.setlifePoint_current(target.getlifePoint_current()+2);
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
