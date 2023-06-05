package kr.ac.tukorea.sgp.s2018182024.dragonflight.dragonflight.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.tukorea.sgp.s2018182024.dragonflight.R;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.scene.BaseScene;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.resource.BitmapPool;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.view.Metrics;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.object.Sprite;

public class Player extends Sprite {
    private static final float PLAYER_X = Metrics.gameWidth / 2;
    private static final float PLAYER_Y_OFFSET = 1.5f;
    private static final float PLAYER_Y = Metrics.gameHeight - PLAYER_Y_OFFSET;
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
    private static final float FIRE_INTERVAL = 1.f;
    private static final float SPARK_DURATION = FIRE_INTERVAL * 0.4f;
    private static final float SPARK_WIDTH = 50 * Metrics.bitmapRatio;
    private static final float SPARK_HEIGHT = 30 * Metrics.bitmapRatio;
    private static final float SPARK_OFFSET = 0.65f;
    private float accumulatedTime;
    private float power = 10.f;
    private static final float MAX_ROLL_TIME = 0.3f;    // 회전에 걸리는 시간
    private float rollTime;

    private static final Rect[] rects = new Rect[] {
            new Rect(  8, 0,   8 + 42, 80),
            new Rect( 76, 0,  76 + 42, 80),
            new Rect(140, 0, 140 + 50, 80),
            new Rect(205, 0, 205 + 56, 80),
            new Rect(270, 0, 270 + 62, 80),
            new Rect(334, 0, 334 + 70, 80),
            new Rect(406, 0, 406 + 62, 80),
            new Rect(477, 0, 477 + 56, 80),
            new Rect(549, 0, 549 + 48, 80),
            new Rect(621, 0, 621 + 42, 80),
            new Rect(689, 0, 689 + 42, 80)
    };

    public Player() {
        super(R.mipmap.fighters, PLAYER_X, PLAYER_Y, PLAYER_WIDTH, PLAYER_HEIGHT);
        targetBitmap = BitmapPool.get(R.mipmap.target);
        sparkBitmap = BitmapPool.get(R.mipmap.laser_0);
        tx = x;
    }

    public void setTargetPosition(float tx, float ty) {
        this.tx = tx;
        targetRect.set(
                tx - TARGET_RADIUS, y - TARGET_RADIUS,
                tx + TARGET_RADIUS, y + TARGET_RADIUS);
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

        int sign;
        if(x > tx) {
            sign = -1;
        }
        else {
            if (x < tx) {
                sign = 1;
            }
            else {
                sign = 0;
            }
        }

        if(x == tx) {                           // 목표로 이동하다가 멈추면
            if(rollTime > 0) sign = -1;         // 기존과 반대 방향으로 회전해서 원래대로 돌아가야 함
            else if (rollTime < 0) sign = 1;
        }
        rollTime += sign * time;
        if(x == tx) {                                   // 멈춰있을 때
            if(sign < 0 && rollTime < 0) rollTime = 0;  // 반대방향으로 회전하다가 정면이 되었다면
            if(sign > 0 && rollTime > 0) rollTime = 0;  // 회전을 멈춤
        }

        if(rollTime < -MAX_ROLL_TIME) rollTime = -MAX_ROLL_TIME;
        if(rollTime > MAX_ROLL_TIME) rollTime = MAX_ROLL_TIME;


        checkFire();
    }

    @Override
    public void draw(Canvas canvas) {
        // 정면이 5번, 최대 11개의 이미지 이므로 위아래 최대 5개
        int rollIndex = 5 + (int)(rollTime * 5 / MAX_ROLL_TIME);
        canvas.drawBitmap(bitmap, rects[rollIndex], rect, null);
        if(tx != x){
            canvas.drawBitmap(targetBitmap, null, targetRect, null);
        }
        if(accumulatedTime < SPARK_DURATION){
            sparkRect.set(x - SPARK_WIDTH / 2, y - SPARK_HEIGHT / 2 - SPARK_OFFSET,
                    x + SPARK_WIDTH / 2, y + SPARK_HEIGHT / 2 - SPARK_OFFSET);
            canvas.drawBitmap(sparkBitmap, null, sparkRect, null);
        }
    }

    public void changePower(int score) {
        power += (float)score / 1000;
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
        Bullet bullet = Bullet.get(x, y, (int)power);
        BaseScene.getTopScene().addObject(MainScene.Layer.BULLET, bullet);
    }
}
