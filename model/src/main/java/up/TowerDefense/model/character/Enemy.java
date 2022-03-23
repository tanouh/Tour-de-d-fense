package up.TowerDefense.model.character;

import up.TowerDefense.model.game.Game;

import up.TowerDefense.model.map.Board;
import up.TowerDefense.model.map.Tile;
import up.TowerDefense.model.object.Position;
import up.TowerDefense.model.object.DestructibleObstacle;
import up.TowerDefense.model.object.Tower;
import up.TowerDefense.model.object.Obstacle;
import java.util.Random;

import static  up.TowerDefense.model.game.StaticFunctions.*;
import static  up.TowerDefense.model.map.Pathfinding.*;


public class Enemy extends Personnage implements Movable{
	
	public enum Type{
		COVID,
        BACTERIUM,
        VIRUS,
        FUNGUS,
        PARASITE,
        BOMBER
        //type d'ennemi
    }
	
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
	 * Determine le type d'obstacle ciblee par l'ennemi
	 */
	private DestructibleObstacle.ObsType target;


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
	 * Construit un enemy de taille "size" a la position "position"
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
		super(position, presetEnemy.getSize(), presetEnemy.getResistance(), presetEnemy.getMaxHealth(), presetEnemy.getSpeed(), presetEnemy.imgName);
		this.coins_value = presetEnemy.getCoins();
		this.agressiveness_degree = presetEnemy.getAgressiv_Degree();
		this.attackspeed = presetEnemy.getAgressiv_Degree();
		this.damage = presetEnemy.getDammage();
		this.suicidal = presetEnemy.isSuicidal();
		this.target = presetEnemy.getTarget();
	}
	
	/**
	 * Fonction de mise a jour de la position de l'ennemi
	 * a chaque fois que l'interface graphique se met a jour cette fonction est appelee
	 */

	/*
	* TODO : revoir le champ target qui représente la cible de l'ennemi
	*  (celle ci peut etre differente selon le type d'ennemi: soit c'est les tours, soit c'est la cible finale)
	**/
	public void update_position(Board board){
		Position destination = null;
		if (this.target == DestructibleObstacle.ObsType.TOWER){
			destination = findTower(this.position,1,board); //FIXME : les enemis n'ont pas de portee?

		}
		if(destination == null){
			destination = Game.getBoard().getNearestTargetPosition(this.position);
		}

		if (destination != null) {
			Tile [] path = FindPath(this.position, destination);
			if (path != null){
				this.position = path[0].getPos();

			}
		}
	}

	/**
	 * L'ennemi attaque un obstacle destructible "target"
	 * @param target Represente l'obstacle cible de l'ennemi (tour ou autre)
	 */
	public void attackObstacle(DestructibleObstacle target) {
		target.setCurrentHealth(target.getCurrentHealth()-(int)this.damage);
	}

	/**
	 * L'ennemi attaque un allie "target".
	 *
	 * @param target Represente l'allie cible de l'ennemi
	 */
	public void attackAlly(Ally target) {
		target.setlifePoint_current(target.getlifePoint_current()-(int)(this.damage/target.getResistance()));
	}

	/**
	 * L'enemy soigne un autre enemy "target".
	 * 
	 * @param target Represente l'enemy soigne par l'enemy courant.
	 */
	private void heal(Enemy target) {
		target.setlifePoint_current(target.getlifePoint_current()+2);
	}
	
	/**
	 * augmente la capacite speed de l'ennemi de 0.15
	 */
	public void upgrade_speed() {
		this.setSpeed((float)(this.getSpeed()+0.15));
	}
	
	/**
	 * augmente la capacite attack_speed de l'ennemi de 0.15
	 */
	public void upgrade_attack_speed() {
		this.setAttackspeed((float)(this.getAttackspeed()+0.15));
	}
	
	/**
	 * augmente la capacite agressiv_degree de l'ennemi de 0.15
	 */
	public void upgrade_agressiv_degree() {
		this.setAgressiv_degree((float)(this.getAgressiv_degree()+0.15));
	}
	
	/**
	 * augmente la capacite resistance de l'ennemi de 0.15
	 */
	public void upgrade_resistance() {
		this.setResistance((float)(this.getResistance()+0.15));
	}
	
	/**
	 * augmente la capacite damage de l'ennemi de 0.15
	 */
	public void upgrade_damage() {
		this.setDamage((float)(this.getDamage()+0.15));
	}
	
	/**
	 * augmente le nombres de point de vie max de l'ennemi de 20
	 */
	public void upgrade_lifepoint_max() {
		this.setlifePoint_max(this.getlifePoint_max()+20);
	}
	
	/**
	 * augmente le nombre de coins que rapportera l'ennemi de 10
	 */
	public void upgrade_coins_value() {
		this.setCoins_value(this.getCoins_value()+10);
	}
	
	/**
	 * devra permettre d'augmenter une capacite aleatoire a la fin d'une vague
	 */
	public void nextWaveStat() {
		Random rd = new Random();
		int value = rd.nextInt(6);
		switch(value) {
			case 0 : this.upgrade_speed(); break;
			case 1 : this.upgrade_attack_speed(); break;
			case 2 : this.upgrade_agressiv_degree(); break;
			case 3 : this.upgrade_resistance(); break;
			case 4 : this.upgrade_damage(); break;
			case 5 : this.upgrade_lifepoint_max(); break;
			case 6 : this.upgrade_coins_value(); break;
		}
	}
	
	public int getCoins_value() {
		return this.coins_value;
	}
	
	public void setCoins_value(int newCoins_value) {
		this.coins_value = newCoins_value;
	}
	
	public float getAgressiv_degree() {
		return this.agressiveness_degree;
	}
	
	public void setAgressiv_degree(float newdegree) {
		this.agressiveness_degree = newdegree;
	}

	public float getAttackspeed() {
		return attackspeed;
	}

	public void setAttackspeed(float attackspeed) {
		this.attackspeed = attackspeed;
	}
	
	public void setDamage(float newdamage) {
		this.damage = newdamage;
	}
	
	public float getDamage() {
		return this.damage;
	}
	
	//public abstract Enemy copy();


}
