package dadm.scaffold.engine;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class FramesPerSecondCounter extends GameObject {

    private final float textWidth;
    private final float textHeight;

    private Paint paint;
    private long totalMillis;
    private int draws;
    private float framesPerSecond;

    private String framesPerSecondText = "";

    public FramesPerSecondCounter(GameEngine gameEngine) {
        paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        textHeight = (float) (150 * gameEngine.pixelFactor);
        textWidth = (float) (300 * gameEngine.pixelFactor);
        paint.setTextSize(textHeight / 2);
    }

    @Override
    public void startGame() {
        totalMillis = 0;
    }

    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {
        /*

        if (totalMillis > 1000) {
            framesPerSecond = draws * 1000 / totalMillis;
            framesPerSecondText = framesPerSecond + " fps";
            totalMillis = 0;

        }
        */
        totalMillis += elapsedMillis;
        framesPerSecond = totalMillis/1000;
        framesPerSecondText = " " + framesPerSecond + " s";
        draws = 0;
    }

    @Override
    public void onDraw(Canvas canvas) {
       /* paint.setColor(Color.BLACK);
        canvas.drawRect(0, (int) (canvas.getHeight() - textHeight), textWidth, canvas.getHeight(), paint);
        */
        paint.setColor(Color.WHITE);
        canvas.drawText(framesPerSecondText, textWidth / 2, (int) (canvas.getHeight() - textHeight / 2), paint);
        draws++;
    }
}
