package dadm.scaffold.engine;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public abstract class Sprite extends ScreenGameObject {

    protected double positionX;
    protected double positionY;
    protected double rotation;
    protected double rotationSpeed;

    protected double pixelFactor;

    protected Bitmap bitmap;
    protected int imageHeight;
    protected int imageWidth;

    protected final Matrix matrix = new Matrix();

    protected Sprite (GameEngine gameEngine, int drawableRes) {
        Resources r = gameEngine.getContext().getResources();
        Drawable spriteDrawable = r.getDrawable(drawableRes);

        this.pixelFactor = gameEngine.pixelFactor;

        this.imageHeight = (int) (spriteDrawable.getIntrinsicHeight() * this.pixelFactor);
        this.imageWidth = (int) (spriteDrawable.getIntrinsicWidth() * this.pixelFactor);

        this.bitmap = ((BitmapDrawable) spriteDrawable).getBitmap();

        this.paint = new Paint();
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (positionY > canvas.getWidth()
                || positionY > canvas.getHeight()
                || positionX < -imageWidth
                || positionX < -imageHeight) {
            return;
        }
        if (mX > canvas.getWidth() || mY > canvas.getHeight()
                || mX < -mWidth || mY < -mHeight) {
            return;
        }

        paint.setColor(Color.YELLOW);
        canvas.drawRect(mBoundingRect, paint);

        matrix.reset();
        matrix.postScale((float) pixelFactor, (float) pixelFactor);
        matrix.postTranslate((float) positionX, (float) positionY);
        matrix.postRotate((float) rotation,
                (float) (positionX + imageWidth/2),
                (float) (positionY + imageHeight/2));
        canvas.drawBitmap(bitmap, matrix, null);
    }

    public abstract void onCollision(GameEngine gameEngine,
                                     ScreenGameObject sgo);

}
