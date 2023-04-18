package kr.ac.tukorea.sgp.s2018182024.dragonflight.game;

import android.graphics.Canvas;
import android.util.Log;

import java.util.Random;

import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.BaseScene;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.GameObject;

public class EnemyGenerator implements GameObject {
    private static final String TAG = EnemyGenerator.class.getSimpleName();
    private static final float GEN_INTERVAL = 5.0f;
    private float time;
    private int wave;

    private void generate() {
        ++wave;
        Log.v(TAG, "Generate!");
        Random r = new Random();
        MainScene scene = (MainScene) BaseScene.getTopScene();
        for(int i = 0; i < 5; ++i){
            int level = (wave + 5) / 5 - r.nextInt(3);
            if(level < 0)
                level = 0;
            if(level > Enemy.MAX_HP)
                level = Enemy.MAX_HP;
            scene.addObject(MainScene.Layer.ENEMY, Enemy.get(i, level));
        }
    }

    @Override
    public void update() {
        time += BaseScene.frameTime;
        if(time > GEN_INTERVAL){
            generate();
            time -= GEN_INTERVAL;
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
