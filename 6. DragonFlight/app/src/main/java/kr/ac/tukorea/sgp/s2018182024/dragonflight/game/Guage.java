package kr.ac.tukorea.sgp.s2018182024.dragonflight.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Guage {
    private Paint fgPaint = new Paint();
    private Paint bgPaint = new Paint();

    public Guage(float width) {
        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setStrokeWidth(width);
        bgPaint.setColor(Color.YELLOW);
        bgPaint.setStrokeCap(Paint.Cap.ROUND);
        fgPaint.setStyle(Paint.Style.STROKE);
        fgPaint.setStrokeWidth(width * (float)0.75);
        fgPaint.setColor(Color.RED);
        fgPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    public void draw(Canvas canvas, float value) {
        canvas.drawLine(0, 0, 1.f, 0.f, bgPaint);
        if(value > 0) {
            canvas.drawLine(0, 0, value, 0.f, fgPaint);
        }
    }
}
