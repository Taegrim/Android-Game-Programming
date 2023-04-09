package kr.ac.tukorea.sgp.s2018182024.samplegame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * TODO: document your custom view class.
 */
public class GameView extends View implements Choreographer.FrameCallback {
    private static final String TAG = GameView.class.getSimpleName();
    private ArrayList<Ball> balls = new ArrayList<>();
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
        Bitmap soccerBitmap = BitmapFactory.decodeResource(res, R.mipmap.soccer_ball_240);
        Ball.setBitmap(soccerBitmap);

        Random r = new Random();
        for(int i = 0; i < 10; ++i){
            float dx = r.nextFloat() * 0.05f + 0.03f;
            float dy = r.nextFloat() * 0.05f + 0.03f;
            balls.add(new Ball(dx, dy));
        }

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
        for(Ball ball : balls){
            ball.update();
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.scale(scale, scale);
        for(Ball ball : balls){
            ball.draw(canvas);
        }
    }
}