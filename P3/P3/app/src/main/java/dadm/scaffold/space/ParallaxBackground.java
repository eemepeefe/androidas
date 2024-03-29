package dadm.scaffold.space;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import dadm.scaffold.engine.GameEngine;
import dadm.scaffold.engine.GameObject;

public class ParallaxBackground extends GameObject {


    protected double positionY;
    protected double rotation;

    protected double speedY;

    //Parallax
    protected int screenHeight;
    protected int screenWidth;
    protected int targetWidth;

    protected double pixelFactor;

    protected Bitmap bitmap;
    protected int imageHeight;
    protected int imageWidth;

    protected final Matrix matrix = new Matrix();

    private int speed;

    public ParallaxBackground(GameEngine gameEngine, int speed, int drawableResId) {

        Drawable spriteDrawable = gameEngine.getContext().getResources()
                .getDrawable(drawableResId);
        bitmap = ((BitmapDrawable) spriteDrawable).getBitmap();
        pixelFactor = gameEngine.pixelFactor;
        speedY = speed*pixelFactor/1000d;
        imageHeight = (int) (spriteDrawable.getIntrinsicHeight()*pixelFactor);
        imageWidth = (int) (spriteDrawable.getIntrinsicWidth()*pixelFactor);
        screenHeight = gameEngine.height;
        screenWidth = gameEngine.width;
        targetWidth = Math.min(imageWidth, screenWidth);
    }

    @Override
    public void startGame() {

    }

    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {
        positionY += speedY * elapsedMillis;

    }

    @Override
    public void onDraw(Canvas canvas){
        if (positionY > 0) {
            matrix.reset();
            matrix.postScale((float) (pixelFactor),
                    (float) (pixelFactor));
            matrix.postTranslate(0, (float) (positionY - imageHeight));
            canvas.drawBitmap(bitmap, matrix, null);
        }
        matrix.reset();
        matrix.postScale((float) (pixelFactor),
                (float) (pixelFactor));
        matrix.postTranslate(0, (float) positionY);
        canvas.drawBitmap(bitmap, matrix, null);
        if (positionY > screenHeight) {
            positionY -= imageHeight;
        }

    }

}
