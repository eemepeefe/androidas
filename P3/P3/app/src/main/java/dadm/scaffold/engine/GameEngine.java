package dadm.scaffold.engine;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.widget.Chronometer;
import android.widget.Space;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dadm.scaffold.EndGameFragment;
import dadm.scaffold.R;
import dadm.scaffold.ScaffoldActivity;
import dadm.scaffold.input.InputController;
import dadm.scaffold.space.SpaceShipPlayer;

public class GameEngine {

    private List<GameObject> gameObjects = new ArrayList<GameObject>();
    private List<GameObject> objectsToAdd = new ArrayList<GameObject>();
    private List<GameObject> objectsToRemove = new ArrayList<GameObject>();
    private List<ScreenGameObject> mCollisionableObjects = new ArrayList<>();

    private UpdateThread theUpdateThread;
    private DrawThread theDrawThread;
    public InputController theInputController;
    private final GameView theGameView;


    public int width;
    public int height;
    public double pixelFactor;

    private int lives;
    private int score;

    public boolean isPausedAvailable;

    private Activity mainActivity;

    private Chronometer chrono;
    private MediaPlayer player;
    private float time;
    private boolean prepared = false;

    public GameEngine(Activity activity, GameView gameView) {
        mainActivity = activity;
        theGameView = gameView;
        theGameView.setGameObjects(this.gameObjects);
        this.width = theGameView.getWidth()
                - theGameView.getPaddingRight() - theGameView.getPaddingLeft();
        this.height = theGameView.getHeight()
                - theGameView.getPaddingTop() - theGameView.getPaddingTop();

        this.pixelFactor = this.height / 1200d;

        this.lives = 3;
        this.score = 0;
        this.time = 0;
        isPausedAvailable = false;

        player = new MediaPlayer();

        chrono = new Chronometer(getContext());
        chrono.start();
    }

    public void setTheInputController(InputController inputController) {
        theInputController = inputController;
    }

    private void startMusic(){
        player = MediaPlayer.create(getContext(), R.raw.dnb_background);
        player.start();
    }

    public void startGame() {
        // Stop a game if it is running
        stopGame();
        startMusic();
        // Setup the game objects
        int numGameObjects = gameObjects.size();
        for (int i = 0; i < numGameObjects; i++) {
            gameObjects.get(i).startGame();
        }

        // Start the update thread
        theUpdateThread = new UpdateThread(this);
        theUpdateThread.start();

        // Start the drawing thread
        theDrawThread = new DrawThread(this);
        theDrawThread.start();

        isPausedAvailable = true;
    }

    public void stopGame() {
        if (theUpdateThread != null) {
            theUpdateThread.stopGame();
        }
        if (theDrawThread != null) {
            theDrawThread.stopGame();
        }
    }

    public void pauseGame() {
        if (theUpdateThread != null && isPausedAvailable) {
            theUpdateThread.pauseGame();
        }
        if (theDrawThread != null  && isPausedAvailable) {
            theDrawThread.pauseGame();
        }
    }

    public void resumeGame() {
        if (theUpdateThread != null) {
            theUpdateThread.resumeGame();
        }
        if (theDrawThread != null) {
            theDrawThread.resumeGame();
        }
    }

    public void addGameObject(GameObject gameObject) {
        if (isRunning()) {
            objectsToAdd.add(gameObject);
        } else {
            gameObjects.add(gameObject);
            if (gameObject instanceof SpaceShipPlayer){
                mCollisionableObjects.add((ScreenGameObject) gameObject);
            }
        }
        mainActivity.runOnUiThread(gameObject.onAddedRunnable);
    }

    public void removeGameObject(GameObject gameObject) {
        objectsToRemove.add(gameObject);
        mainActivity.runOnUiThread(gameObject.onRemovedRunnable);
    }

    public void onUpdate(long elapsedMillis) {

        int numGameObjects = gameObjects.size();
        for (int i = 0; i < numGameObjects; i++) {
            gameObjects.get(i).onUpdate(elapsedMillis, this);
        }
        checkCollisions();
        synchronized (gameObjects) {
            while (!objectsToRemove.isEmpty()) {
                GameObject objectToRemove = objectsToRemove.remove(0);
                gameObjects.remove(objectToRemove);
                mCollisionableObjects.remove(objectToRemove);
            }
            while (!objectsToAdd.isEmpty()) {
                GameObject objectToRemove = objectsToAdd.remove(0);
                gameObjects.add(objectToRemove);
                mCollisionableObjects.add((ScreenGameObject) objectToRemove);
            }
        }

        checkTime();
    }

    public void onDraw() {
        theGameView.draw();
    }

    public boolean isRunning() {
        return theUpdateThread != null && theUpdateThread.isGameRunning();
    }

    public boolean isPaused() {
        return theUpdateThread != null && theUpdateThread.isGamePaused();
    }

    public Context getContext() {
        return theGameView.getContext();
    }

    private void checkCollisions() {
        int numObjects = mCollisionableObjects.size();
        for (int i = 0; i < numObjects; i++) {
            ScreenGameObject objectA = mCollisionableObjects.get(i);
            for (int j = i + 1; j < numObjects; j++) {
                ScreenGameObject objectB = mCollisionableObjects.get(j);
                if (objectA.checkCollision(objectB)) {
                    System.out.println(objectA + " collided with  " + objectB);
                    objectA.onCollision(this, objectB);
                    objectB.onCollision(this, objectA);
                }
            }
        }
    }

    /*
    public void addCollisionable(GameObject object){
        mCollisionableObjects.add((ScreenGameObject) object);
    }

    public void removeCollisionable(GameObject object){
        mCollisionableObjects.remove((ScreenGameObject) object);
    }
*/

    public void removeLife(){
        if (lives > 0){
            lives--;
            System.out.println("LIVES: " + lives);
            ((ScaffoldActivity)mainActivity).updateLivesandScore(returnScoreAndLives());
        } else if (lives == 0){
            isPausedAvailable = false;
            player.stop();
            ((ScaffoldActivity)mainActivity).stopGame(this);
            ((ScaffoldActivity)mainActivity).sendScore(score);
            ((ScaffoldActivity)mainActivity).endGame(this);
        }
    }

    public void addScore(){
        score=score+10;
        ((ScaffoldActivity)mainActivity).updateLivesandScore(returnScoreAndLives());
    }

    public int[] returnScoreAndLives(){
        int[] aux = {score, lives};
        return aux;
    }

    public int getDrawable(){
        int drawable = ((ScaffoldActivity)mainActivity).getShip();
        return drawable;
    }

    //TIME


    private void checkTime(){
        time = (SystemClock.elapsedRealtime() - chrono.getBase()) / 1000;
        System.out.println(time);
        if (time>=30){
            isPausedAvailable = false;
            player.stop();
            ((ScaffoldActivity)mainActivity).stopGame(this);
            ((ScaffoldActivity)mainActivity).sendScore(score);
            ((ScaffoldActivity)mainActivity).endGame(this);
        }
    }
}
