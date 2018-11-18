package dadm.scaffold.engine;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

import dadm.scaffold.space.Asteroid;

public class GameController extends GameObject {
    List<Asteroid> mAsteroidPool = new ArrayList<Asteroid>();
    private final int TIME_BETWEEN_ENEMIES = 500; //miliseconds
    private int mEnemiesSpawned;
    private long mCurrentMillis;


    public GameController(GameEngine engine){
        mCurrentMillis = 0;
        mEnemiesSpawned = 20;
        for (int i=0; i<mEnemiesSpawned; i++) {
            mAsteroidPool.add(new Asteroid(this,engine ));
        }
    }
    @Override
    public void startGame() {

    }
    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {
        mCurrentMillis += elapsedMillis;
        long waveTimestamp = mEnemiesSpawned*TIME_BETWEEN_ENEMIES;
        if (mCurrentMillis > waveTimestamp) {
            // Spawn a new enemy
            Asteroid a = mAsteroidPool.remove(0);
            a.init(gameEngine);
            gameEngine.addGameObject(a);
            mEnemiesSpawned++;
        }
    }

    @Override
    public void onDraw(Canvas canvas) {

    }

    public void returnToPool(Asteroid a){
        mAsteroidPool.add(a);
    }
}
