package kr.ac.tukorea.sgp.s2018182024.dragonflight.game;

import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.tukorea.sgp.s2018182024.dragonflight.R;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.BaseScene;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.GameObject;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.Metrics;

public class MainScene extends BaseScene {
    private static final String TAG = MainScene.class.getSimpleName();
    private final Player player;
    private final Score score;

    enum Layer {
        BG1, ENEMY, BULLET, PLAYER, BG2, UI, CONTROLLER, COUNT
    }

    public MainScene() {
        initLayers(Layer.COUNT);
        player = new Player();
        addObject(Layer.PLAYER, player);

        addObject(Layer.BG1, new VertScrollBackground(R.mipmap.bg_city, 0.4f));
        addObject(Layer.BG2, new VertScrollBackground(R.mipmap.clouds, 0.6f));
        score = new Score();
        addObject(Layer.UI, score);
        addObject(Layer.CONTROLLER, new EnemyGenerator());
        addObject(Layer.CONTROLLER, new CollisionChecker());
    }

    public void changeScore(int value) {
        score.changeScore(value);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch(action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float x = Metrics.toGameX(event.getX());
                float y = Metrics.toGameY(event.getY());
                player.setTargetPosition(x, y);
                return true;
        }
        return super.onTouchEvent(event);
    }
}
