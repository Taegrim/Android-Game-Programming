package kr.ac.tukorea.sgp.s2018182024.samplegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

public class Fighter {
    private static Bitmap bitmap;
    private RectF rect = new RectF();

    public Fighter(){
        float x = 5.0f;
        float y = 12.0f;
        setPosition(x, y);
    }

    public void update(){

    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap, null, rect, null);
    }

    public static void setBitmap(Bitmap bitmap) {
        Fighter.bitmap = bitmap;
    }

    public void setPosition(float x, float y){
        float r = 1.25f;
        rect.set(x - r, y - r, x + r, y + r);
    }
}
