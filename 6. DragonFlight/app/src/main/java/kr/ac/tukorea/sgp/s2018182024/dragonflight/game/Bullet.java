package kr.ac.tukorea.sgp.s2018182024.dragonflight.game;

import android.graphics.Paint;
import android.graphics.RectF;

import kr.ac.tukorea.sgp.s2018182024.dragonflight.R;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.BaseScene;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.CollisionObject;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.Metrics;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.Sprite;

public class Bullet extends Sprite implements CollisionObject {
    private static final float BULLET_WIDTH = 28 * Metrics.bitmapRatio;
    private static final float BULLET_HEIGHT = 40 * Metrics.bitmapRatio;
    private static float SPEED = 20.f;
    protected static Paint paint;

    public Bullet(float x, float y) {
        super(R.mipmap.laser_1, x, y, BULLET_WIDTH, BULLET_HEIGHT);
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {
        float frameTime = BaseScene.frameTime;
        y -= SPEED * frameTime;
        fixRect();

        if(rect.bottom < 0){
            BaseScene.getTopScene().removeObject(this);
        }
    }

    @Override
    public RectF getCollisionRect() {
        return rect;
    }
}
