package up.TowerDefense.model.game;

import up.TowerDefense.model.character.Enemy;
import up.TowerDefense.model.character.PresetEnemy;
import up.TowerDefense.model.map.Board;
import up.TowerDefense.model.object.Position;
import up.TowerDefense.view.componentHandler.MapGenerator;

import java.util.Random;
import java.util.TimerTask;

/**
 * le contenu des sous vagues du jeu
 */
public class EnemySpawn extends TimerTask {
    public final Position spawnPoint; // position à laquelle va apparaitre les enemy
    public PresetEnemy enemy;
    private static long INTERVAL = 500;
    public int quantity;

    /*La quantité de temps entre le spawn de deux enemis successifs*/
    private long spawnTime;

    public EnemySpawn(PresetEnemy enemy, int quantity, Position spawnPoint) {
        this.enemy = enemy;
        this.quantity = quantity;
        this.spawnPoint = spawnPoint;
    }

    public PresetEnemy getEnemy(){
        return this.enemy;
    }

    public long getInterval(){
        return this.INTERVAL;
    }
    public int getQuantity(){
        return this.quantity;
    }

    /**
     * Retourne un point de frai aléatoire
     */
    public static Position getRandomSpawnPosition(Board board){
        Random rand = new Random();
        int i = rand.nextInt(board.getSpawnablePoint().size());
        return board.getSpawnablePoint().get(i);
    }

    public static EnemySpawn getBacterium(){
         Position spawnPoint = getRandomSpawnPosition(Game.getBoard());
         return new EnemySpawn(PresetEnemy.Bacterium(), 10,spawnPoint );
    }


    public void spawnEnemy() {
        //Mettre les ennemis dans mapGen un à un

        //fixme : voir si on n'a pas besoin de nouveau threads pour gérer les vagues
    }

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {

    }
}
