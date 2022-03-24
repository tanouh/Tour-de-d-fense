package up.TowerDefense.model.character;

import up.TowerDefense.model.game.Game;

import up.TowerDefense.model.map.Board;
import up.TowerDefense.model.map.Path;
import up.TowerDefense.model.map.Pathfinding;
import up.TowerDefense.model.map.Tile;
import up.TowerDefense.model.object.Position;
import up.TowerDefense.model.object.DestructibleObstacle;
import up.TowerDefense.model.object.Tower;
import up.TowerDefense.model.object.Obstacle;
import java.util.Random;

import static  up.TowerDefense.model.game.StaticFunctions.*;
import static  up.TowerDefense.model.map.Pathfinding.*;


/*TODO Les lignes reliées à path devraient être décommentées lorsque le problème relié à pathfinding sera réglé
 */

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
	 * Chemin suivi par l'enemi
	 */
	private Path path;

	/**
	 * Durée depuis laquelle l'enemi vit (ie qu'il est sur le plateau)
	 */
	private long lifeTime;

	/**
	 * Le temps durant lequel l'enemi a effectué un mouvement
	 */
	private long travelTime;


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
		//this.path = Pathfinding.FindPath(position, Game.getBoard().getNearestTargetPosition(position));

		this.lifeTime=System.currentTimeMillis();
		this.travelTime = System.currentTimeMillis();
	}
	
	/**
	 * Fonction de mise a jour de la position de l'ennemi
	 * a chaque fois que l'interface graphique se met a jour cette fonction est appelee
	 */

	/*
	* FIXME : problème potentiel : un autre personnage pourrait être situé sur une position
	*  sur laquelle le personnage doit aller pour une raison lamda , il faudrait mettre des tests
	**/
	public void update_position(){

		if(System.currentTimeMillis() - travelTime > 500){
			/* todo : l'intervalle de temps entre deux pas est arbitraire
			    à stocker selon le type d'enemi je suppose
			    => au lieu de mettre un attribut float pour speed mettre plutôt un long pour gérer cet intervalle
			 */
			travelTime = System.currentTimeMillis();
			this.position.x++;

			//this.position = path.GetPos(System.currentTimeMillis()-lifeTime, this.getSpeed());
		}

	}


	public void update_paths(){
		//this.path = Pathfinding.FindPath(this.position, Game.getBoard().getNearestTargetPosition(position));
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

	/* fixme : à quoi ça sert de mettre en aléatoire?
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

	public void setLifeTime(long lifeTime) {
		this.lifeTime = lifeTime;
	}
	//public abstract Enemy copy();


}
