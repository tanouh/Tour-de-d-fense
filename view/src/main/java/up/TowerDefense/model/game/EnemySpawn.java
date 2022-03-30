package up.TowerDefense.model.game;

import up.TowerDefense.model.character.Enemy;
import up.TowerDefense.model.character.PresetEnemy;
import up.TowerDefense.model.map.Board;
import up.TowerDefense.model.object.Position;

import java.util.Random;

import static up.TowerDefense.view.componentHandler.MapGenerator.charactersList;

/**
 * le contenu des sous vagues du jeu
 */
public class EnemySpawn {
    public PresetEnemy enemy;
    public int quantity;
    private boolean finishedSpawn = false;

    private int count;

    public EnemySpawn(PresetEnemy enemy, int quantity) {
        this.enemy = enemy;
        this.quantity = quantity;
        this.count=0;
    }
    /**
     * Retourne un point de frai aléatoire
     */
    public static Position getRandomSpawnPosition(Board board){
        Random rand = new Random();
        int i = rand.nextInt(board.getSpawnablePoint().size());
        return board.getSpawnablePoint().get(i);
    }

    /**
     * Lance une sous-vagues d'ennemis de même type
     */
    public void spawnEnemies(){
        charactersList.add(new Enemy(this.enemy, getRandomSpawnPosition(Game.getBoard())));
        count++;
        if(count == quantity){
            finishedSpawn = true;
        }

        //fixme: à un certain moment certains ennemis se chevauchent sur l'interface graphique , problème à travailler plus tard
    }

    /*
    TODO : à compléter
     */
    public static EnemySpawn getBacterium(int order){
         return new EnemySpawn(PresetEnemy.Bacterium(), 5*order );
    }

    public static EnemySpawn getVirus(){
        return new EnemySpawn(PresetEnemy.Virus(), 10);

    }
    public boolean isFinished(){
        return finishedSpawn;
    }

    public void setFinishedSpawn(boolean finishedSpawn) {
        this.finishedSpawn = finishedSpawn;
    }
}
