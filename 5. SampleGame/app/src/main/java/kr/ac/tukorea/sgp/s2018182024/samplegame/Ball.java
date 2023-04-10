package kr.ac.tukorea.sgp.s2018182024.samplegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

public class Ball implements GameObject {
    private static Bitmap bitmap;
    private RectF rect = new RectF();
    private float dx, dy;

    public Ball(float dx, float dy){
        this.dx = dx;
        this.dy = dy;
        rect.set(0, 0, 2.5f, 2.5f);
    }

    public static void setBitmap(Bitmap bitmap) {
        Ball.bitmap = bitmap;
    }

    @Override
    public void update() {
        rect.offset(dx, dy);
        if(dx > 0){
            if(rect.right > 10.0f){
                dx = -dx;
            }
        }
        else{
            if(rect.left < 0){
                dx = -dx;
            }
        }

        if(dy > 0){
            if(rect.bottom > 15.0f){
                dy = -dy;
            }
        }
        else{
            if(rect.top < 0){
                dy = -dy;
            }
        }
    }

    @Override
    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap, null, rect, null);
    }
}
