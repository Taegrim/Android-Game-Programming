package kr.ac.tukorea.sgp.s2018182024.dragonflight.game;

import android.graphics.RectF;

import kr.ac.tukorea.sgp.s2018182024.dragonflight.R;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.AnimationSprite;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.BaseScene;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.BitmapPool;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.CollisionObject;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.Metrics;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.Recyclable;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.RecycleBin;

public class Enemy extends AnimationSprite implements CollisionObject, Recyclable {
    private static final float SIZE = Metrics.gameWidth / 5;
    private static final int SPEED = 2;
    private int level;
    private RectF collisionRect = new RectF();
    private static final int[] resId = {
            R.mipmap.enemy_01, R.mipmap.enemy_02, R.mipmap.enemy_03, R.mipmap.enemy_04, R.mipmap.enemy_05,
            R.mipmap.enemy_06, R.mipmap.enemy_07, R.mipmap.enemy_08, R.mipmap.enemy_09, R.mipmap.enemy_10,
            R.mipmap.enemy_11, R.mipmap.enemy_12, R.mipmap.enemy_13, R.mipmap.enemy_14, R.mipmap.enemy_15,
            R.mipmap.enemy_16, R.mipmap.enemy_17, R.mipmap.enemy_18, R.mipmap.enemy_19, R.mipmap.enemy_20
    };
    public static final int MAX_HP = resId.length - 1;

    public static Enemy get(int index, int level) {
        Enemy enemy = (Enemy) RecycleBin.get(Enemy.class);
        if(enemy == null){
            return new Enemy(index, level);
        }
        enemy.x = ((Metrics.gameWidth / 10) * (2 * index + 1));
        enemy.y = -(SIZE / 2);
        if(level != enemy.level) {
            enemy.level = level;
            enemy.bitmap = BitmapPool.get(resId[level]);
        }
        return enemy;
    }

    private Enemy(int index, int level) {
        super(resId[level], (Metrics.gameWidth / 10) * (2 * index + 1), -(SIZE / 2), SIZE, SIZE, 10, 0);
        this.level = level;
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

    @Override
    public void onRecycle() {

    }

    public int getScore() {
        return 100 * (level + 1);
    }
}
