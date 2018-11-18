package dadm.scaffold.space;

import java.util.Random;

import dadm.scaffold.R;
import dadm.scaffold.engine.GameController;
import dadm.scaffold.engine.GameEngine;
import dadm.scaffold.engine.ScreenGameObject;
import dadm.scaffold.engine.Sprite;

public class Asteroid extends Sprite {

    private final GameController mController;
    private final double mSpeed;
    private double mSpeedX;
    private double mSpeedY;

    private Random rnd;

    public Asteroid(GameController gameController, GameEngine
            gameEngine) {
        super(gameEngine, R.drawable.robot
                );
        mSpeed = 200d*pixelFactor/1000d;
        mController = gameController;
        rnd = new Random();
    }

    public void init(GameEngine gameEngine) {
// They initialize in a [-30, 30] degrees angle
        double angle =
                rnd.nextDouble()*Math.PI/3d-Math.PI/6d;
        mSpeedX = mSpeed * Math.sin(angle);
        mSpeedY = mSpeed * Math.cos(angle);
// Asteroids initialize in the central 50% of the screen
        positionX = rnd.nextInt(gameEngine.width/2)+
                gameEngine.width/4;
// They initialize outside of the screen vertically
        positionY = -imageHeight;
        rotation = rnd.nextInt(360);
        rotationSpeed = angle*(180d / Math.PI)/250d;

    }

    @Override
    public void startGame() {

    }

    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine)
    {
        positionX += mSpeedX * elapsedMillis;
        positionY += mSpeedY * elapsedMillis;
// Check of the sprite goes out of the screen
        if (positionY > gameEngine.height) {
            gameEngine.removeGameObject(this);
            mController.returnToPool(this);
        }
        rotation += rotationSpeed * elapsedMillis;
        if (rotation > 360) {
            rotation = 0;
        }
        else if (rotation < 0) {
            rotation = 360;
        }

        this.mX = positionX;
        this.mY = positionY;
        this.mWidth = imageWidth;
        this.mHeight = imageHeight;
        onPostUpdate(gameEngine);
    }

    public void removeObject(GameEngine engine){
        engine.removeGameObject(this);
        mController.returnToPool(this);
    }

    @Override
    public void onCollision(GameEngine gameEngine,
                            ScreenGameObject otherObject) {

    }
}
