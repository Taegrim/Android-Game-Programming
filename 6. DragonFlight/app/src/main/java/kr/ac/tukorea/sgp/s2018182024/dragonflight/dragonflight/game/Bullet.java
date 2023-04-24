package kr.ac.tukorea.sgp.s2018182024.dragonflight.dragonflight.game;

import android.graphics.RectF;

import kr.ac.tukorea.sgp.s2018182024.dragonflight.R;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.scene.BaseScene;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.interfaces.CollisionObject;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.view.Metrics;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.interfaces.Recyclable;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.scene.RecycleBin;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.object.Sprite;

public class Bullet extends Sprite implements CollisionObject, Recyclable {
    private static final float BULLET_WIDTH = 28 * Metrics.bitmapRatio;
    private static final float BULLET_HEIGHT = 40 * Metrics.bitmapRatio;
    private static float SPEED = 20.f;
    private int damage;

    public static Bullet get(float x, float y, int power) {
        Bullet bullet = (Bullet) RecycleBin.get(Bullet.class);
        if(bullet == null){
            return new Bullet(x, y, power);
        }
        bullet.init(x, y, power);
        bullet.fixRect();
        return bullet;
    }
    private Bullet(float x, float y, int power) {
        super(R.mipmap.laser_1, x, y, BULLET_WIDTH, BULLET_HEIGHT);
        init(x, y, power);
    }

    private void init(float x, float y, int power) {
        this.x = x;
        this.y = y;
        this.damage = power;
    }


    @Override
    public void update() {
        float frameTime = BaseScene.frameTime;
        y -= SPEED * frameTime;
        fixRect();

        if(rect.bottom < 0){
            BaseScene.getTopScene().removeObject(MainScene.Layer.BULLET, this);
        }
    }

    @Override
    public RectF getCollisionRect() {
        return rect;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void onRecycle() {

    }
}
