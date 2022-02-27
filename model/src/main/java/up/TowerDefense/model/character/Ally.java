package up.TowerDefense.model.character;

import up.TowerDefense.model.object.Position;

public class Ally extends Character implements Movable{
	
	/**
	 * Definit la taille constante de chaque allie a la moitier d'une case (0.50)
	 */
	private static final float SIZE = 0.50f;
	private static final float DAMAGE = 10.00f;

	/**
	 * Construit un allie de la taille definit par la constante SIZE
	 */
	public Ally() {
		super(SIZE);
	}
	
	/**
	 * Construit un allie de la taille definit par la constante SIZE à la position "position"
	 * 
	 * @param position definit la position de l'allie sur la carte
	 */
	public Ally(Position position) {
		super(position, SIZE, 1.00f, 100, 1.00f);
	}
	
	public void attack(Enemy target) {
		target.setlifePoint_current(target.getlifePoint_current()-(int)(DAMAGE/target.getResistance()));
	}
	
}
