package kr.ac.tukorea.sgp.s2018182024.dragonflight.game;

import android.util.Log;
import android.view.MotionEvent;

import kr.ac.tukorea.sgp.s2018182024.dragonflight.BuildConfig;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.R;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.AnimationSprite;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.BaseScene;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.CollisionChecker;
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
    }

    @Override
    public void update(long timeElapsed) {
        super.update(timeElapsed);
        checkCollision();
    }

    private void checkCollision() {
        for(GameObject o1 : layers.get(Layer.ENEMY.ordinal())){
            if(!(o1 instanceof Enemy)){
                continue;
            }
            Enemy enemy = (Enemy) o1;

            for(GameObject o2 : layers.get(Layer.BULLET.ordinal())){
                if(!(o2 instanceof Bullet)){
                    continue;
                }
                Bullet bullet = (Bullet) o2;

                if(CollisionChecker.collide(enemy, bullet)){
                    Log.d(TAG, "Collision!");
                    removeObject(o1);
                    removeObject(o2);

                    break;
                }
            }
        }
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
