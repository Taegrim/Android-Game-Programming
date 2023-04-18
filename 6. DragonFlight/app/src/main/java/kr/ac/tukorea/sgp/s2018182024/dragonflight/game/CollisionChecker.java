package kr.ac.tukorea.sgp.s2018182024.dragonflight.game;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;

import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.BaseScene;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.CollisionHelper;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.GameObject;

public class CollisionChecker implements GameObject {
    private static final String TAG = CollisionChecker.class.getSimpleName();

    @Override
    public void update() {
        MainScene scene = (MainScene) BaseScene.getTopScene();
        ArrayList<GameObject> enemies = scene.getObjects(MainScene.Layer.ENEMY);
        ArrayList<GameObject> bullets = scene.getObjects(MainScene.Layer.BULLET);
        for(GameObject o1 : enemies){
            Enemy enemy = (Enemy) o1;

            for(GameObject o2 : bullets){
                Bullet bullet = (Bullet) o2;

                if(CollisionHelper.collide(enemy, bullet)){
                    Log.d(TAG, "Collision!");
                    scene.removeObject(o1);
                    scene.removeObject(o2);
                    break;
                }
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
