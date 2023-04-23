package kr.ac.tukorea.sgp.s2018182024.dragonflight.game;

import android.graphics.Canvas;

import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.BaseScene;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.Metrics;

public class HorzScrollBackground extends Background {
    private final float speed;
    private final float width;
    private float scroll;

    public HorzScrollBackground(int resId, float speed) {
        super(resId, speed);
        this.width = bitmap.getWidth() * (Metrics.gameHeight / bitmap.getHeight());

        setSize(this.width, Metrics.gameHeight);
        this.speed = speed;
    }

    @Override
    public void update() {
        scroll += speed * BaseScene.frameTime;
    }

    @Override
    public void draw(Canvas canvas) {
        float cur = -(scroll % width);
        if(cur < 0) {
            cur += width;
        }
        while(cur > -width) {
            rect.set(cur, 0, cur + width, Metrics.gameHeight);
            canvas.drawBitmap(bitmap, null, rect, null);
            cur -= width;
        }
    }
}
