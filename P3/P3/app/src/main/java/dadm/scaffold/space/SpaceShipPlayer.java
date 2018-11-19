package dadm.scaffold.space;

import java.util.ArrayList;
import java.util.List;

import dadm.scaffold.R;
import dadm.scaffold.engine.GameEngine;
import dadm.scaffold.engine.ScreenGameObject;
import dadm.scaffold.engine.Sprite;
import dadm.scaffold.input.InputController;

public class SpaceShipPlayer extends Sprite {

    private static final int INITIAL_BULLET_POOL_AMOUNT = 6;
    private static final long TIME_BETWEEN_BULLETS = 400;
    private static final long TIME_BETWEEN_SPECIAL_BULLETS = 200;
    List<Bullet> bullets = new ArrayList<Bullet>();
    List<SpecialBullet> specialbullets = new ArrayList<SpecialBullet>();
    private long timeSinceLastFire;
    private long timeSinceLastSpecialFire;

    private int maxX;
    private int maxY;
    private double speedFactor;

    public SpaceShipPlayer(GameEngine gameEngine, int drawable){
        super(gameEngine, drawable);
        speedFactor = pixelFactor * 100d / 1000d; // We want to move at 100px per second on a 400px tall screen
        maxX = gameEngine.width - imageWidth;
        maxY = gameEngine.height - imageHeight;
        positionX = maxX / 2;
        positionY = maxY / 2;
        initBulletPool(gameEngine);
        initSpecialBulletPool(gameEngine);
    }

    private void initBulletPool(GameEngine gameEngine) {
        for (int i=0; i<INITIAL_BULLET_POOL_AMOUNT; i++) {
            bullets.add(new Bullet(gameEngine));
        }
    }

    private void initSpecialBulletPool(GameEngine gameEngine) {
        for (int i=0; i<INITIAL_BULLET_POOL_AMOUNT; i++) {
            specialbullets.add(new SpecialBullet(gameEngine));
        }
    }

    private Bullet getBullet() {
        if (bullets.isEmpty()) {
            return null;
        }
        return bullets.remove(0);
    }

    private SpecialBullet getSpecialBullet() {
        if (specialbullets.isEmpty()) {
            return null;
        }
        return specialbullets.remove(0);
    }

    void releaseBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    void releaseSpecialBullet(SpecialBullet bullet) {specialbullets.add(bullet);}

    @Override
    public void startGame() {
        positionX = maxX / 2;
        positionY = maxY / 2;
    }

    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {
        // Get the info from the inputController
        updatePosition(elapsedMillis, gameEngine.theInputController);
        checkFiring(elapsedMillis, gameEngine);
        autoFireBullets(elapsedMillis, gameEngine);
        this.mX = positionX;
        this.mY = positionY;
        this.mWidth = imageWidth;
        this.mHeight = imageHeight;
        onPostUpdate(gameEngine);
    }

    private void updatePosition(long elapsedMillis, InputController inputController) {
        positionX += speedFactor * inputController.horizontalFactor * elapsedMillis;
        if (positionX < 0) {
            positionX = 0;
        }
        if (positionX > maxX) {
            positionX = maxX;
        }
        positionY += speedFactor * inputController.verticalFactor * elapsedMillis;
        if (positionY < 0) {
            positionY = 0;
        }
        if (positionY > maxY) {
            positionY = maxY;
        }
    }

    private void autoFireBullets(long elapsedMillis, GameEngine gameEngine){
        if (timeSinceLastFire > TIME_BETWEEN_BULLETS) {
            Bullet bullet = getBullet();
            if (bullet == null) {
                return;
            }
            bullet.init(this, positionX + imageWidth/2, positionY);
            gameEngine.addGameObject(bullet);
            timeSinceLastFire = 0;
        }
        else {
            timeSinceLastFire += elapsedMillis;
        }
    }

    private void checkFiring(long elapsedMillis, GameEngine gameEngine) {
        if (gameEngine.theInputController.isFiring && timeSinceLastSpecialFire > TIME_BETWEEN_SPECIAL_BULLETS) {
            SpecialBullet bullet = getSpecialBullet();
            if (bullet == null) {
                return;
            }
            bullet.init(this, positionX + imageWidth/2, positionY);
            gameEngine.addGameObject(bullet);
            timeSinceLastSpecialFire = 0;
        }
        else {
            timeSinceLastSpecialFire += elapsedMillis;
        }
    }

    @Override
    public void onCollision(GameEngine gameEngine,
                            ScreenGameObject otherObject) {
        if (otherObject instanceof Asteroid) {
            gameEngine.removeLife();
            removeObject(gameEngine);
            Asteroid a = (Asteroid) otherObject;
            a.removeObject(gameEngine);
        }
    }

    public void removeObject(GameEngine engine){
        engine.removeGameObject(this);
        engine.addGameObject(new SpaceShipPlayer(engine, engine.getDrawable()));
    }


}
