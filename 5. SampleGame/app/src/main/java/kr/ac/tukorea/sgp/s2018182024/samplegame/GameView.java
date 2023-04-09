package kr.ac.tukorea.sgp.s2018182024.samplegame;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class GameView extends View implements Choreographer.FrameCallback {
    private static final String TAG = GameView.class.getSimpleName();
    private Bitmap soccerBitmap;
    private RectF soccerRect = new RectF();
    private float ballDx, ballDy;
    private float scale;

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
        Resources res = getResources();
        soccerBitmap = BitmapFactory.decodeResource(res, R.mipmap.soccer_ball_240);

        float cx = 5.0f;
        float cy = 7.0f;
        float radius = 1.25f;
        soccerRect.set(cx - radius, cy - radius, cx + radius, cy + radius);
        ballDx = 0.04f;
        ballDy = 0.06f;

        Choreographer.getInstance().postFrameCallback(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);

        scale = w / 10.f;
    }

    @Override
    public void doFrame(long l) {
        update();
        invalidate();
        if(isShown()){
            Choreographer.getInstance().postFrameCallback(this);
        }
    }

    private void update() {
        soccerRect.offset(ballDx, ballDy);
        if(ballDx > 0){
            if(soccerRect.right > 10){
                ballDx = -ballDx;
            }
        }
        else{
            if(soccerRect.left < 0){
                ballDx = -ballDx;
            }
        }

        if(ballDy > 0){
            if(soccerRect.bottom > 15.0f){
                ballDy = -ballDy;
            }
        }
        else{
            if(soccerRect.top < 0){
                ballDy = -ballDy;
            }
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.scale(scale, scale);
        canvas.drawBitmap(soccerBitmap, null, soccerRect, null);
    }
}