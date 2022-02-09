package up.TowerDefense.model.character;

import up.TowerDefense.model.object.Position;

public class Ally extends Character implements Movable{
	
	/**
	 * Definit la taille constante de chaque allie a la moitier d'une case (0.50)
	 */
	private static final float SIZE = 0.50f;
	
	private final Position LEFT = new Position(this.getPosition().getX() - this.getSpeed(), this.getPosition().getY());
	private final Position RIGHT = new Position(this.getPosition().getX() + this.getSpeed(), this.getPosition().getY());
	private final Position UP = new Position(this.getPosition().getX(), this.getPosition().getY() + this.getSpeed());
	private final Position DOWN = new Position(this.getPosition().getX(), this.getPosition().getY() - this.getSpeed());

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
	
	public void moveTo(Position position) {
		this.setPosition(position);
	}
	
	public void moveUp() {
		this.moveTo(UP);
	}
	
	public void moveDown() {
		this.moveTo(DOWN);
	}
	
	public void moveLeft() {
		this.moveTo(LEFT);
	}
	
	public void moveRight() {
		this.moveTo(RIGHT);
	}
	
}
