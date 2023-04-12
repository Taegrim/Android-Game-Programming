package kr.ac.tukorea.sgp.s2018182024.dragonflight.game;

import android.util.Log;
import android.view.MotionEvent;

import kr.ac.tukorea.sgp.s2018182024.dragonflight.BuildConfig;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.BaseScene;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.CollisionChecker;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.GameObject;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.Metrics;

public class MainScene extends BaseScene {
    private static final String TAG = MainScene.class.getSimpleName();
    private final Player player;
    public MainScene() {
        player = new Player();
        addObject(player);

        addObject(new EnemyGenerator());
    }

    @Override
    public void update(long timeElapsed) {
        super.update(timeElapsed);
        checkCollision();
    }

    private void checkCollision() {
        for(GameObject o1 : objects){
            if(!(o1 instanceof Enemy)){
                continue;
            }
            Enemy enemy = (Enemy) o1;

            for(GameObject o2 : objects){
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
