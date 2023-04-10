package kr.ac.tukorea.sgp.s2018182024.samplegame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

public class Fighter implements GameObject {
    private static final float RADIUS = 1.25f;
    private static final float SPEED = 10.0f;
    private static Bitmap bitmap;
    private RectF rect = new RectF();
    private float x, y;     // 현재 위치
    private float tx, ty;   // 움직여야 할 위치
    private float dx, dy;   // 1초당 움직여야 할 양
    private float angle;

    public Fighter() {
        x = tx = 4.5f;
        y = ty = 13.25f;
        dx = dy = 0;
        rect.set(x - RADIUS, y - RADIUS, x + RADIUS, y + RADIUS);

        if(bitmap == null){
            bitmap = BitmapFactory.decodeResource(GameView.res, R.mipmap.plane_240);
        }
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
        rect.set(x - RADIUS, y - RADIUS, x + RADIUS, y + RADIUS);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.rotate(angle, x, y);
        canvas.drawBitmap(bitmap, null, rect, null);
        canvas.restore();
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
