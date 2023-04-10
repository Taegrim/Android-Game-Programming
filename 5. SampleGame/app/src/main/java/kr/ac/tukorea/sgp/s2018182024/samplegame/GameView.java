package kr.ac.tukorea.sgp.s2018182024.samplegame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class GameView extends View implements Choreographer.FrameCallback {
    private static final String TAG = GameView.class.getSimpleName();
    public static Resources res;
    public static float scale;
    protected Paint fpsPaint = new Paint();

    public GameView(Context context) {
        super(context);
        init(null, 0);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        GameView.res = getResources();
        Choreographer.getInstance().postFrameCallback(this);

        fpsPaint.setColor(Color.BLUE);
        fpsPaint.setTextSize(100f);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);

        scale = w / 10.f;
    }

    private long previousTime;
    @Override
    public void doFrame(long currentTime) {
        if(previousTime != 0){
            long timeElapsed = currentTime - previousTime;
            BaseScene.getTopScene().update(timeElapsed);
        }
        previousTime = currentTime;
        invalidate();
        if(isShown()){
            Choreographer.getInstance().postFrameCallback(this);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.scale(scale, scale);
        BaseScene.getTopScene().draw(canvas);
        canvas.restore();

        int fps = (int) (1.0f / BaseScene.frameTime);
        canvas.drawText("FPS : " + fps, 50f, 100f, fpsPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean handled = BaseScene.getTopScene().onTouchEvent(event);
        if(handled){
            return true;
        }
        return super.onTouchEvent(event);
    }
}