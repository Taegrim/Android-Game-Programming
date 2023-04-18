package kr.ac.tukorea.sgp.s2018182024.dragonflight.game;

import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.BaseScene;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.GameObject;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.Metrics;

public class MainScene extends BaseScene {
    private static final String TAG = MainScene.class.getSimpleName();
    private final Player player;

    enum Layer {
        ENEMY, BULLET, PLAYER, CONTROLLER, COUNT
    }

    public MainScene() {
        initLayers(Layer.COUNT);
        player = new Player();
        addObject(Layer.PLAYER, player);

        addObject(Layer.CONTROLLER, new EnemyGenerator());
        addObject(Layer.CONTROLLER, new CollisionChecker());
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
