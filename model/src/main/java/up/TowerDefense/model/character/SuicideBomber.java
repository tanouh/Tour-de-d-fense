package up.TowerDefense.model.character;

import up.TowerDefense.model.object.Position;

public class SuicideBomber extends Enemy{
    private PresetEnemy presetEnemy;
    /**
     * Construit un enemy de taille "size" à la position "position"
     *
     * @param presetEnemy
     * @param position    Definit la position de l'ennemy
     */
    public SuicideBomber(PresetEnemy presetEnemy, Position position) {
        super(presetEnemy, position);
    }
    /*@Override
    public Enemy copy() {
        return new SuicideBomber(this.presetEnemy, position);
    }*/

}
