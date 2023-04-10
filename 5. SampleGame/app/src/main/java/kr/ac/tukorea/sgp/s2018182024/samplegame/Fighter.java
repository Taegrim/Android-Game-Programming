package kr.ac.tukorea.sgp.s2018182024.samplegame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

public class Fighter extends Sprite {
    private static final float RADIUS = 1.25f;
    private static final float SPEED = 10.0f;
    private Bitmap targetBitmap;
    private RectF targetRect = new RectF();
    private float tx, ty;   // 움직여야 할 위치
    private float dx, dy;   // 1초당 움직여야 할 양
    private float angle;

    public Fighter() {
        super(R.mipmap.plane_240, Metrics.gameWidth / 2, Metrics.gameHeight - 4.0f, RADIUS * 2, RADIUS * 2);
        tx = x;
        ty = y;
        dx = dy = 0;

        targetBitmap = BitmapPool.get(R.mipmap.target);
    }

    @Override
    public void update() {
        x += dx * BaseScene.frameTime;
        // 타겟 위치를 지나칠 경우 타겟 위치로 이동하고, 움직여야 할 양을 0으로 변경
        if((dx > 0 && x > tx) || (dx < 0 & x < tx)){
            x = tx;
            dx = 0;
        }
        y += dy * BaseScene.frameTime;
        if((dy > 0 && y > ty) || (dy < 0 && y < ty)){
            y = ty;
            dy = 0;
        }
        fixRect();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.rotate(angle, x, y);
        canvas.drawBitmap(bitmap, null, rect, null);
        canvas.restore();
        if(dx != 0 || dy != 0){
            float r = 1.0f;
            targetRect.set(tx - r, ty - r, tx + r, ty + r);
            canvas.drawBitmap(targetBitmap, null, targetRect, null);
        }
    }

    public void setTargetPosition(float tx, float ty) {
        this.tx = tx;
        this.ty = ty;
        float dx = tx - this.x;
        float dy = ty - this.y;
        double radian = Math.atan2(dy, dx);
        this.dx = (float) (SPEED * Math.cos(radian));
        this.dy = (float) (SPEED * Math.sin(radian));
        // 원의 매개변수 방정식 이용하여 dx, dy 구하기
        angle = (float) Math.toDegrees(radian) + 90;
    }
}
