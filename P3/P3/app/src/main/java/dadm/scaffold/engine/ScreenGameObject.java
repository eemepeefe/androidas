package dadm.scaffold.engine;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public abstract class ScreenGameObject extends GameObject {
    protected double mX;
    protected double mY;
    protected int mHeight;
    protected int mWidth;

    protected Paint paint;

    public Rect mBoundingRect = new Rect(-1, -1, -1, -1);

    public boolean checkCollision(ScreenGameObject otherObject) {
        return checkRectangularCollision(otherObject);
    }

    private boolean checkRectangularCollision(ScreenGameObject other) {
        return Rect.intersects(mBoundingRect, other.mBoundingRect);
    }

    public abstract void onCollision(GameEngine gameEngine,
                            ScreenGameObject sgo);

    public void onPostUpdate(GameEngine gameEngine) {
        mBoundingRect.set(
                (int) mX,
                (int) mY,
                (int) mX + mWidth,
                (int) mY + mHeight);
    }

}