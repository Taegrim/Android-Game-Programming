package kr.ac.tukorea.sgp.s2018182024.dragonflight.app;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.tukorea.sgp.s2018182024.dragonflight.R;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.BaseScene;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.BitmapPool;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.Metrics;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.Sprite;

public class Player extends Sprite {
    private static final float PLAYER_Y = Metrics.gameHeight - 1.5f;
    private static final float SIZE = 1.75f;
    private static final float TARGET_RADIUS = 0.5f;
    private static final float SPEED = 10.0f;
    private static final float PLAYER_LEFT = SIZE / 2;
    private static final float PLAYER_RIGHT = Metrics.gameWidth - SIZE / 2;
    private float tx;

    private final Bitmap targetBitmap;
    private final RectF targetRect = new RectF();

    public Player() {
        super(R.mipmap.fighter, Metrics.gameWidth / 2, PLAYER_Y, SIZE, SIZE);
        targetBitmap = BitmapPool.get(R.mipmap.target);
        tx = x;
    }

    public void setTargetPosition(float tx, float ty) {
        this.tx = tx;
        targetRect.set(
                tx - TARGET_RADIUS, PLAYER_Y - TARGET_RADIUS,
                tx + TARGET_RADIUS, PLAYER_Y + TARGET_RADIUS);
    }

    @Override
    public void update() {
        super.update();

        float time = BaseScene.frameTime;
        if(tx >= x) {
            x += SPEED * time;
            if(x > tx) {
                x = tx;
            }
        } else {
            x -= SPEED * time;
            if(x < tx){
                x = tx;
            }
        }

        fixRect();
        if(x < PLAYER_LEFT){
            x = tx = PLAYER_LEFT;
        }
        if (x > PLAYER_RIGHT){
            x = tx = PLAYER_RIGHT;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(tx != x){
            canvas.drawBitmap(targetBitmap, null, targetRect, null);
        }
    }
}
