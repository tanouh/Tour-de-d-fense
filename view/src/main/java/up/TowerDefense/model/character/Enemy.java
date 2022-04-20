package up.TowerDefense.model.character;

import up.TowerDefense.model.game.Game;
import up.TowerDefense.model.map.Path;
import up.TowerDefense.model.map.Pathfinding;
import up.TowerDefense.model.object.DestructibleObstacle;
import up.TowerDefense.model.object.EnemyProjectile;
import up.TowerDefense.model.object.PlaceableObstacle;
import up.TowerDefense.model.object.Position;
import up.TowerDefense.view.componentHandler.MapGenerator;

import static up.TowerDefense.model.game.StaticFunctions.findTower;


/*TODO Les lignes reliées à path devraient être décommentées lorsque le problème relié à pathfinding sera réglé
 */

public class Enemy extends Personnage{



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
	 * Temps durant lequel l'image de l'ennemi sera remplacée par une image temporaire qui signifierait
	 * qu'il a été touché par une attaque
	 */
	public final long HITDELAY = 250;

	/**
	 * Correspond au nombre de coins rapportes une fois l'enemy mort.
	 */
	private int reward;
	
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

	private int range;
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
	 * Déclence HITDELAY
	 */
	protected long hitStart ;

	/**
	 * Signale si l'ennemi a reçu un coup
	 */
	protected boolean tookHit = false;


	private boolean alive;
	private boolean frozen;
	private long freezeStartTime;
	private long freezeDuration;

	private long reloadTime;
	private long timeSinceLastAttack;


	/**
	 * Construit un enemy a la position "position" a partir des informations d'un PresetEnemy
	 * 
	 * @param presetEnemy Contient toute les informations concernant l'enemy notament son degre d'agressivite ou sa vitesse.
	 * @param position Definit la position de l'enemy
	 */
	public Enemy(PresetEnemy presetEnemy, Position position) {
		super(position, presetEnemy.getSize(), presetEnemy.getResistance(), presetEnemy.getMaxHealth(),
				presetEnemy.getSpeed(), presetEnemy.imgName, presetEnemy.reloadImgName);
		this.reward = presetEnemy.getCoins();
		this.agressiveness_degree = presetEnemy.getAgressiv_Degree();
		this.attackspeed = presetEnemy.getAgressiv_Degree();
		this.damage = presetEnemy.getDammage();
		this.target = presetEnemy.getTarget();
		this.range= presetEnemy.getRange();
		this.path = Pathfinding.FindPath(position, Game.getBoard().getNearestTargetPosition(position));
		Game.getBoard().addToListEnemy(this);


		//fixme : à intégrer dans les attributs des ennemis
		reloadTime = 2000;


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
		if(frozen && System.currentTimeMillis() - freezeStartTime > freezeDuration){
			unfreeze();
		}

		/**
		 * Quand l'ennemi pass aux environs d'une tour il ne s'arrête pas mais lance des projectiles tout en continuant
		 * Quant à lui, s'il accuse une attaque sa vitesse diminue
		 */
		//if(System.currentTimeMillis() - travelTime > this.getSpeed()){

		travelTime = System.currentTimeMillis();
		Game.getBoard().getTile(this.position).setEnemy(null);

		this.position = path.GetPos(System.currentTimeMillis() - lifeTime, this.velocity);
		Game.getBoard().getTile(this.position).setEnemy(this);

		if (Game.getBoard().getTile((int)position.x,(int)position.y).isTarget()){
			Game.setLives(-1);
			this.die(false);
		}
	}

	public void update_paths(){
		this.path = Pathfinding.FindPath(this.position, Game.getBoard().getNearestTargetPosition(position));
	}

	public void identifyTarget(){
		Position towerPos = findTower(this.position, this.range, Game.getBoard());
		//System.out.println("###########Detection de tour : "+ towerPos);
		if(towerPos != null){
			System.out.println("   tower found");
			launchAttack((PlaceableObstacle) Game.getBoard().getOccupier(towerPos));
		}
	}

	private void launchAttack(PlaceableObstacle target) {
		if(System.currentTimeMillis() - timeSinceLastAttack > reloadTime){
			EnemyProjectile projectile = new EnemyProjectile(this.position, target.position, this.damage, Game.getLevel(), target);
			MapGenerator.projectilesList.add(projectile);
			timeSinceLastAttack = System.currentTimeMillis();
		}
	}



	/**
	 * L'ennemi attaque un allie "target".
	 *
	 * @param target Represente l'allie cible de l'ennemi
	 */
	public void attackAlly(Ally target) {
		target.setCurrentHealth(target.getCurrentHealth()-(int)(this.damage/target.getResistance()));
	}


	/**
	 * L'enemy soigne un autre enemy "target".
	 * @param target Represente l'enemy soigne par l'enemy courant.
	 */
	private void heal(Enemy target) {
		target.setCurrentHealth(target.getCurrentHealth()+2);
	}


	public void die(boolean killed){
		System.out.println("deces");
		this.alive = false;
		MapGenerator.charactersList.remove(this);
		if (killed) Game.setCredits(this.reward);
	}

	public long getReloadTime() {
		return reloadTime;
	}

	public long getTimeSinceLastAttack() {
		return timeSinceLastAttack;
	}

	public void setFreezeDuration(long i) {
		this.freezeDuration = i;
	}
	public void freeze() {
		this.frozen = true;
		freezeDuration = System.currentTimeMillis();
	}
	public void unfreeze(){
		this.frozen = false;
		freezeStartTime = -10000;
	}

	public void live(){
		alive = true;
		lifeTime = System.currentTimeMillis();
		timeSinceLastAttack = System.currentTimeMillis();
		travelTime = System.currentTimeMillis();
	}

	public boolean isAlive(){
		return alive;
	}

	public void takeDamage(double power){
		currentHealth = (int) Math.round(currentHealth - power/resistance);
		System.out.println("             enemy " + this + " took damage\n currentHealth : " + currentHealth);
		this.hitStart = System.currentTimeMillis();
		tookHit = true;
		if(currentHealth <= 0){
			die(true);
		}
	}

	@Override
	public boolean tookHit(){
		if (tookHit){
			if (System.currentTimeMillis() - hitStart > HITDELAY) {
				tookHit = false;
				hitStart = 2*System.currentTimeMillis();
			}
		}
		return tookHit;
	}
}
