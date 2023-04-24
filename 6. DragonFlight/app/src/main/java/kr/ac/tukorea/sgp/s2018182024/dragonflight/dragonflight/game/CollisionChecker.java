package kr.ac.tukorea.sgp.s2018182024.dragonflight.dragonflight.game;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;

import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.scene.BaseScene;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.util.CollisionHelper;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.interfaces.GameObject;

public class CollisionChecker implements GameObject {
    private static final String TAG = CollisionChecker.class.getSimpleName();

    @Override
    public void update() {
        MainScene scene = (MainScene) BaseScene.getTopScene();
        ArrayList<GameObject> enemies = scene.getObjects(MainScene.Layer.ENEMY);
        ArrayList<GameObject> bullets = scene.getObjects(MainScene.Layer.BULLET);
        for(int i = enemies.size() - 1; i >= 0; --i){
            Enemy enemy = (Enemy) enemies.get(i);

            for(int j = bullets.size() - 1; j >= 0; --j){
                Bullet bullet = (Bullet) bullets.get(j);

                if(CollisionHelper.collide(enemy, bullet)){
                    Log.d(TAG, "Collision!");
                    scene.removeObject(MainScene.Layer.BULLET, bullet);
                    boolean death = enemy.decreaseLife(bullet.getDamage());
                    if(death) {
                        scene.removeObject(MainScene.Layer.ENEMY, enemy);
                        int score = enemy.getScore();
                        scene.changeScore(score);
                        scene.player.changePower(score);
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
