package up.TowerDefense.model.character;

import up.TowerDefense.model.object.Position;

public interface Movable {
	
	/**
	 * Deplace l'element a la position "position"
	 * 
	 * @param position de la case sur laquelle deplacer l'element
	 */
	public void moveTo(Position position);
	
	/**
	 * Deplace l'element une case plus haut
	 */
	public void moveUp();
	
	/**
	 * Deplace l'element une case plus bas
	 */
	public void moveDown();
	
	/**
	 * Deplace l'element une case a gauche
	 */
	public void moveLeft();
	
	/**
	 * Deplace l'element une case a droite
	 */
	public void moveRight();
}
