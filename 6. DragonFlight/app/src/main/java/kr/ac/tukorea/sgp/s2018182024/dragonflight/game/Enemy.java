package kr.ac.tukorea.sgp.s2018182024.dragonflight.game;

import android.graphics.Canvas;
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
    protected int life, maxLife;
    private RectF collisionRect = new RectF();
    private Guage guage = new Guage();
    private static final int[] resId = {
            R.mipmap.enemy_01, R.mipmap.enemy_02, R.mipmap.enemy_03, R.mipmap.enemy_04, R.mipmap.enemy_05,
            R.mipmap.enemy_06, R.mipmap.enemy_07, R.mipmap.enemy_08, R.mipmap.enemy_09, R.mipmap.enemy_10,
            R.mipmap.enemy_11, R.mipmap.enemy_12, R.mipmap.enemy_13, R.mipmap.enemy_14, R.mipmap.enemy_15,
            R.mipmap.enemy_16, R.mipmap.enemy_17, R.mipmap.enemy_18, R.mipmap.enemy_19, R.mipmap.enemy_20
    };
    public static final int MAX_LEVEL = resId.length - 1;

    public static Enemy get(int index, int level) {
        Enemy enemy = (Enemy) RecycleBin.get(Enemy.class);
        if(enemy == null){
            return new Enemy(index, level);
        }
        enemy.x = ((Metrics.gameWidth / 10) * (2 * index + 1));
        enemy.y = -(SIZE / 2);
        enemy.init(level);
        return enemy;
    }

    private Enemy(int index, int level) {
        super(resId[level], (Metrics.gameWidth / 10) * (2 * index + 1), -(SIZE / 2), SIZE, SIZE, 10, 0);
        init(level);
    }

    private void init(int level) {
        if(level != this.level) {
            this.level = level;
            this.bitmap = BitmapPool.get(resId[level]);
        }
        this.life = this.maxLife = (level + 1) * 10;
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
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.save();
        canvas.translate(rect.left, rect.bottom);
        canvas.scale(rect.width(), rect.height());
        guage.draw(canvas, (float)this.life / this.maxLife);
        canvas.restore();
    }

    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }

    @Override
    public void onRecycle() {

    }

    public boolean decreaseLife(int damage) {
        life -= damage;
        if(life <= 0) return true;
        return false;
    }

    public int getScore() {
        return 100 * (level + 1);
    }
}
