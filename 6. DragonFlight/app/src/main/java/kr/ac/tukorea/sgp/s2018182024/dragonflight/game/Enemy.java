package kr.ac.tukorea.sgp.s2018182024.dragonflight.game;

import android.graphics.RectF;

import kr.ac.tukorea.sgp.s2018182024.dragonflight.R;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.BaseScene;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.CollisionObject;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.Metrics;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.Sprite;

public class Enemy extends Sprite implements CollisionObject {
    private static final float SIZE = Metrics.gameWidth / 5;
    private static final int SPEED = 2;
    private int hp;
    private RectF collisionRect = new RectF();
    private static final int[] resId = {
            R.mipmap.f_01_01, R.mipmap.f_02_01, R.mipmap.f_03_01, R.mipmap.f_04_01, R.mipmap.f_05_01,
            R.mipmap.f_06_01, R.mipmap.f_07_01, R.mipmap.f_08_01, R.mipmap.f_09_01, R.mipmap.f_10_01
    };
    public static final int MAX_LEVEL = resId.length - 1;

    public Enemy(int index, int level) {
        super(resId[level], (Metrics.gameWidth / 10) * (2 * index + 1), -(SIZE / 2), SIZE, SIZE);
        this.hp = level;
    }

    @Override
    public void update() {
        super.update();
        y += SPEED * BaseScene.frameTime;
        fixRect();

        if(rect.top > Metrics.gameHeight){
            BaseScene.getTopScene().removeObject(this);
        }
        collisionRect.set(rect);
        collisionRect.inset(0.1f, 0.1f);
    }

    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }
}
