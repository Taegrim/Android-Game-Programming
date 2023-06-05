package kr.ac.tukorea.sgp.s2018182024.dragonflight.dragonflight.game;

import android.graphics.Canvas;

import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.scene.BaseScene;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.view.Metrics;

public class VertScrollBackground extends Background {
    private final float speed;
    private final float height;
    private float scroll;

    public VertScrollBackground (int resId, float speed) {
        super(resId, speed);
        this.height = bitmap.getHeight() * (Metrics.gameWidth / bitmap.getWidth());
        setSize(Metrics.gameWidth, this.height);
        this.speed = speed;
    }

    @Override
    public void update() {
        scroll += speed * BaseScene.frameTime;
    }

    @Override
    public void draw(Canvas canvas) {
        float cur = scroll % height;
        if(cur > 0) {
            cur -= height;
        }
        while(cur < Metrics.gameHeight) {
            rect.set(0, cur, Metrics.gameWidth, cur + height);
            canvas.drawBitmap(bitmap, null, rect, null);
            cur += height;
        }
    }
}
