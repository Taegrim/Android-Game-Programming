package kr.ac.tukorea.sgp.s2018182024.samplegame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

public class Ball extends Sprite {
    private float dx, dy;

    public Ball(float dx, float dy) {
        super(R.mipmap.soccer_ball_240, 2.0f, 2.0f, 2.5f, 2.5f);
        this.dx = dx;
        this.dy = dy;

        fixRect();
    }

    @Override
    public void update() {
        rect.offset(dx * BaseScene.frameTime, dy * BaseScene.frameTime);
        if(dx > 0){
            if(rect.right > Metrics.gameWidth){
                dx = -dx;
            }
        }
        else{
            if(rect.left < 0){
                dx = -dx;
            }
        }

        if(dy > 0){
            if(rect.bottom > Metrics.gameHeight){
                dy = -dy;
            }
        }
        else{
            if(rect.top < 0){
                dy = -dy;
            }
        }
    }
}
