package kr.ac.tukorea.sgp.s2018182024.samplegame.game;

import android.view.MotionEvent;

import java.util.Random;

import kr.ac.tukorea.sgp.s2018182024.samplegame.framework.BaseScene;
import kr.ac.tukorea.sgp.s2018182024.samplegame.framework.Metrics;

public class MainScene extends BaseScene {
    private static final int MAX_BALLS = 10;
    private Fighter fighter = new Fighter();

    public MainScene() {
        //Metrics.setGameSize(10.0f, 10.0f);
        Random r = new Random();
        for(int i = 0; i < MAX_BALLS; ++i){
            float x = r.nextFloat() * 3.f + 1.f;
            float y = r.nextFloat() * 3.f + 1.f;
            addObject(new Ball(x, y));
        }

        addObject(fighter);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float scale = Metrics.scale;
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            //    fighter.fire();
            case MotionEvent.ACTION_MOVE:
                float x = Metrics.toGameX(event.getX());
                float y = Metrics.toGameY(event.getY());
                fighter.setTargetPosition(x, y);
                return true;
        }
        return super.onTouchEvent(event);
    }
}
