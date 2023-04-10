package kr.ac.tukorea.sgp.s2018182024.samplegame;

import android.view.MotionEvent;

import java.util.Random;

public class MainScene extends BaseScene{
    private static final int MAX_BALLS = 10;
    private Fighter fighter = new Fighter();

    public MainScene() {
        Random r = new Random();
        for(int i = 0; i < MAX_BALLS; ++i){
            float x = r.nextFloat() * 0.05f + 0.03f;
            float y = r.nextFloat() * 0.05f + 0.03f;
            addObject(new Ball(x, y));
        }

        addObject(fighter);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float scale = GameView.scale;
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float x = (float) event.getX() / scale;
                float y = (float) event.getY() / scale;
                fighter.setPosition(x, y);
                return true;
        }
        return super.onTouchEvent(event);
    }
}
