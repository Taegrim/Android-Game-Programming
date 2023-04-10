package kr.ac.tukorea.sgp.s2018182024.samplegame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

public class Fighter implements GameObject {
    private static final float RADIUS = 1.25f;
    private static Bitmap bitmap;
    private RectF rect = new RectF();
    private float x, y;
    private float angle;

    public Fighter() {
        x = 5.0f;
        y = 13.25f;
        rect.set(x - RADIUS, y - RADIUS, x + RADIUS, y + RADIUS);

        if(bitmap == null){
            bitmap = BitmapFactory.decodeResource(GameView.res, R.mipmap.plane_240);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.rotate(angle, x, y);
        canvas.drawBitmap(bitmap, null, rect, null);
        canvas.restore();
    }

    public void setPosition(float x, float y){
        float dx = x - this.x;
        float dy = y - this.y;
        double radian = Math.atan2(dy, dx);
        angle = (float) Math.toDegrees(radian) + 90;
        rect.set(x - RADIUS, y - RADIUS, x + RADIUS, y + RADIUS);
        this.x = x;
        this.y = y;
    }
}
