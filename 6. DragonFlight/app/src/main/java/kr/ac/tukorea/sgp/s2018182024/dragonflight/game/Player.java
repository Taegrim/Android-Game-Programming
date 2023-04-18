package kr.ac.tukorea.sgp.s2018182024.dragonflight.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.tukorea.sgp.s2018182024.dragonflight.R;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.BaseScene;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.BitmapPool;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.Metrics;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.Sprite;

public class Player extends Sprite {
    private static final float PLAYER_X = Metrics.gameWidth / 2;
    private static final float PLAYER_Y = Metrics.gameHeight - 1.5f;
    private static final float PLAYER_WIDTH = 72 * Metrics.bitmapRatio;
    private static final float PLAYER_HEIGHT = 80 * Metrics.bitmapRatio;
    private static final float TARGET_RADIUS = 0.5f;
    private static final float SPEED = 10.0f;
    private static final float PLAYER_MAX_LEFT = PLAYER_WIDTH / 2;
    private static final float PLAYER_MAX_RIGHT = Metrics.gameWidth - PLAYER_WIDTH / 2;

    private float tx;
    private final Bitmap targetBitmap, sparkBitmap;
    private final RectF targetRect = new RectF();
    private final RectF sparkRect = new RectF();
    private static final float FIRE_INTERVAL = 0.25f;
    private static final float SPARK_DURATION = FIRE_INTERVAL * 0.4f;
    private static final float SPARK_WIDTH = 50 * Metrics.bitmapRatio;
    private static final float SPARK_HEIGHT = 30 * Metrics.bitmapRatio;
    private static final float SPARK_OFFSET = 0.65f;
    private float accumulatedTime;

    public Player() {
        super(R.mipmap.fighter, PLAYER_X, PLAYER_Y, PLAYER_WIDTH, PLAYER_HEIGHT);
        targetBitmap = BitmapPool.get(R.mipmap.target);
        sparkBitmap = BitmapPool.get(R.mipmap.laser_0);
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
        if(x < PLAYER_MAX_LEFT){
            x = tx = PLAYER_MAX_LEFT;
        }
        if (x > PLAYER_MAX_RIGHT){
            x = tx = PLAYER_MAX_RIGHT;
        }

        checkFire();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(tx != x){
            canvas.drawBitmap(targetBitmap, null, targetRect, null);
        }
        if(accumulatedTime < SPARK_DURATION){
            sparkRect.set(x - SPARK_WIDTH / 2, y - SPARK_HEIGHT / 2 - SPARK_OFFSET,
                    x + SPARK_WIDTH / 2, y + SPARK_HEIGHT / 2 - SPARK_OFFSET);
            canvas.drawBitmap(sparkBitmap, null, sparkRect, null);
        }
    }

    private void checkFire() {
        accumulatedTime += BaseScene.frameTime;
        if(accumulatedTime < FIRE_INTERVAL){
            return;
        }
        accumulatedTime -= FIRE_INTERVAL;
        fire();
    }

    public void fire() {
        Bullet bullet = Bullet.get(x, y);
        BaseScene.getTopScene().addObject(bullet);
    }
}
