package kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.object;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.resource.BitmapPool;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.interfaces.GameObject;

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
        setSize(width, height);
    }

    protected void setSize(float width, float height) {
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
