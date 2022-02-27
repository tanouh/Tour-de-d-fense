package up.TowerDefense.model.character;

import up.TowerDefense.model.object.DestructibleObstacle;
import up.TowerDefense.model.object.Position;

public class Ally extends Character implements Movable{
	
	/**
	 * Definit la taille constante de chaque allie a la moitier d'une case (0.50)
	 */
	private static final float SIZE = 0.50f;
	
	/**
	 * Definit les dégats constants de chaque allie a 10.
	 */
	private static final float DAMAGE = 10.00f;

	/**
	 * Construit un allie de la taille definit par la constante SIZE
	 */
	public Ally() {
		super(SIZE);
	}
	
	/**
	 * Construit un allie de la taille definit par la constante SIZE Ã  la position "position"
	 * 
	 * @param position definit la position de l'allie sur la carte
	 */
	public Ally(Position position) {
		super(position, SIZE, 1.00f, 100, 1.00f);
	}
	
	/**
	 * L'ennemi attaque un obstacle destructible "target"
	 *
	 * @param target Represente l'obstacle cible de l'ennemi (tour ou autre)
	 */
	public void attackObstacle(DestructibleObstacle target) {
		target.setCurrentHealth(target.getCurrentHealth()-(int)DAMAGE);
	}
	
	/**
	 * L'Allié attaque un allié "target".
	 * 
	 * @param target Represente l'enemy ciblé par l'allié
	 */
	public void attackEnemy(Enemy target) {
		target.setlifePoint_current(target.getlifePoint_current()-(int)(DAMAGE/target.getResistance()));
	}
	
}
