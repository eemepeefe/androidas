package dadm.scaffold.space;

import dadm.scaffold.R;
import dadm.scaffold.engine.GameEngine;
import dadm.scaffold.engine.ScreenGameObject;
import dadm.scaffold.engine.Sprite;

public class SpecialBullet extends Sprite {

    private double speedFactor;

    private SpaceShipPlayer parent;

    public SpecialBullet(GameEngine gameEngine){
        super(gameEngine, R.drawable.specialbomb);
        speedFactor = gameEngine.pixelFactor * -300d / 1000d;
    }

    @Override
    public void startGame() {}

    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {
        positionY += speedFactor * elapsedMillis;
        if (positionY < -imageHeight) {
            gameEngine.removeGameObject(this);
            // And return it to the pool
            parent.releaseSpecialBullet(this);
        }

        this.mX = positionX;
        this.mY = positionY;
        this.mWidth = imageWidth;
        this.mHeight = imageHeight;
        onPostUpdate(gameEngine);
    }

    @Override
    public void onCollision(GameEngine gameEngine,
                            ScreenGameObject otherObject) {
        if (otherObject instanceof Asteroid) {
            gameEngine.addScore();
            removeObject(gameEngine);
            Asteroid a = (Asteroid) otherObject;
            a.removeObject(gameEngine);
        }
    }

    public void removeObject(GameEngine engine){
        engine.removeGameObject(this);
        parent.releaseSpecialBullet(this);
    }

    public void init(SpaceShipPlayer parentPlayer, double initPositionX, double initPositionY) {
        positionX = initPositionX - imageWidth/2;
        positionY = initPositionY - imageHeight/2;
        parent = parentPlayer;
    }
}
