package kr.ac.tukorea.sgp.s2018182024.samplegame.framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

public class Sprite implements GameObject {
    protected Bitmap bitmap;
    protected RectF rect = new RectF();
    protected float x, y, width, height;

    public Sprite(int resId, float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        if(resId != 0){
            setBitmapResource(resId);
        }
        fixRect();
    }

    public void setBitmapResource(int resId) {
        bitmap = BitmapPool.get(resId);
    }

    protected void fixRect() {
        float halfWidth = width / 2;
        float halfHeight = height / 2;
        rect.set(x - halfWidth, y - halfHeight, x + halfWidth, y + halfHeight);
    }


    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, rect, null);
    }
}
