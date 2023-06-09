package kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

import kr.ac.tukorea.sgp.s2018182024.dragonflight.BuildConfig;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.scene.BaseScene;

/**
 * TODO: document your custom view class.
 */
public class GameView extends View implements Choreographer.FrameCallback {
    private static final String TAG = GameView.class.getSimpleName();
    public static Resources res;
    protected Paint fpsPaint;
    protected Paint borderPaint;
    protected boolean running;

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
        running = true;

        Choreographer.getInstance().postFrameCallback(this);

        if(BuildConfig.DEBUG) {
            fpsPaint = new Paint();
            fpsPaint.setColor(Color.BLUE);
            fpsPaint.setTextSize(100f);

            borderPaint = new Paint();
            borderPaint.setColor(Color.RED);
            borderPaint.setStyle(Paint.Style.STROKE);
            borderPaint.setStrokeWidth(0.1f);
        }
    }

    public void setFullScreen() {
        setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);

        float viewRatio = (float)w / (float)h;
        float gameRatio = Metrics.gameWidth / Metrics.gameHeight;

        // 화면 비율이 게임 비율 보다 크면 (폰이 가로로 넓으면)
        if(viewRatio > gameRatio){
            Metrics.xOffset = (int) ((w - h * gameRatio) / 2);
            Metrics.yOffset = 0;
            Metrics.scale = h / Metrics.gameHeight;
        }
        else{
            Metrics.xOffset = 0;
            Metrics.yOffset = (int) ((h - w / gameRatio) / 2);
            Metrics.scale = w / Metrics.gameWidth;
        }
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
        if(running){
            Choreographer.getInstance().postFrameCallback(this);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.translate(Metrics.xOffset, Metrics.yOffset);
        canvas.scale(Metrics.scale, Metrics.scale);
        BaseScene scene = BaseScene.getTopScene();
        if(scene != null){
            if(scene.clipsRect()){
                canvas.clipRect(0, 0, Metrics.gameWidth, Metrics.gameHeight);
            }
            scene.draw(canvas);
        }

        if(BuildConfig.DEBUG){
            canvas.drawRect(0, 0, Metrics.gameWidth, Metrics.gameHeight, borderPaint);
        }
        canvas.restore();

        if(BuildConfig.DEBUG && BaseScene.frameTime > 0) {
            int fps = (int) (1.0f / BaseScene.frameTime);
            int count = (scene != null) ? scene.getObjectCount() : 0;
            canvas.drawText("FPS : " + fps + " objects : " + count, 50f, 100f, fpsPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean handled = BaseScene.getTopScene().onTouchEvent(event);
        if(handled){
            return true;
        }
        return super.onTouchEvent(event);
    }

    public void pauseGame(){
        running = false;
    }

    public void resumeGame(){
        if(running){
            return;
        }
        running = true;
        previousTime = 0;
        Choreographer.getInstance().postFrameCallback(this);
    }
}