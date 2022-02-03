package up.TowerDefense.model.character;

import up.TowerDefense.model.object.Position;

public class SuicideBomber extends Enemy{

    /**
     * Construit un enemy de taille "size" � la position "position"
     *
     * @param presetEnemy
     * @param position    Definit la position de l'ennemy
     */
    public SuicideBomber(PresetEnemy presetEnemy, Position position) {
        super(presetEnemy, position);
    }

    @Override
    public void moveTo(Position position) {

    }

    @Override
    public void moveUp() {

    }

    @Override
    public void moveDown() {

    }

    @Override
    public void moveLeft() {

    }

    @Override
    public void moveRight() {

    }
}
