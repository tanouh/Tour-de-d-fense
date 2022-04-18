package up.TowerDefense.model.game;


/**
 * la classe est utilisé en tant que gestionnaire de vagues
 */
public class Wave {
    public static boolean endOfLevel = false; // Annonce la fin de la vague

    private Subwave currentWave ;
    private boolean running = false; // si une vague est en cours actuellement



    public static int waveOrder ;

    public static int level = 1;
    public static long TIME_SINCE_LAST_SPAWN;
    public static final int MAX_NB_WAVES = Game.getNbWavesTotal();
    public static long TIME_INTERVAL = 10000;  // Intervalle de temps entre deux séries de vagues
    public static long DELAY = 5000;

    public Wave() {
        resetTimeSinceLastSpawn();
        waveOrder = 1;;
        currentWave = Subwave.subwavesInOrder();
    }

    /**
     * The action to be performed by this timer task.
     */
    public void run() {
        if (endOfLevel) return;
        if (!running &&  System.currentTimeMillis() - TIME_SINCE_LAST_SPAWN > DELAY){
            TIME_SINCE_LAST_SPAWN = System.currentTimeMillis();
            running = true;
        }
        if(running){
            if(waveOrder > MAX_NB_WAVES){
                endOfLevel = true;
                running = false;
            }else {
                if (!currentWave.isFinished()) {
                    currentWave.run();
                } else {
                    if(System.currentTimeMillis() - TIME_SINCE_LAST_SPAWN > TIME_INTERVAL){
                        currentWave = Subwave.subwavesInOrder();
                        resetTimeSinceLastSpawn();
                    }
                }
            }
        }
    }

    private void upgrade(){
        for (int i = 1 ; i < level ; i++){
            currentWave.upgrade();
        }
    }

    /**
     * Verifie si la vague a ete lancé
     */
    public boolean hasStarted() {
        return currentWave != null;
    }
    
    /**
     * vérifie si une vague est en cours
     */
    public boolean isRunning() {
        return running;
    }

    public static void resetTimeSinceLastSpawn() {
        TIME_SINCE_LAST_SPAWN = System.currentTimeMillis();
    }
}