package kr.ac.tukorea.sgp.s2018182024.samplegame.framework;

import android.graphics.Canvas;
import android.os.Handler;
import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.tukorea.sgp.s2018182024.samplegame.R;

public class BaseScene {
    private static ArrayList<BaseScene> stack = new ArrayList<>();
    private ArrayList<GameObject> objects = new ArrayList<>();
    public static float frameTime;
    protected static Handler handler = new Handler();

    public static BaseScene getTopScene() {
        int top = stack.size() - 1;
        if(top < 0)
            return null;
        return stack.get(top);
    }

    public int pushScene() {
        stack.add(this);
        return stack.size();
    }

    public int addObject(GameObject object) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                objects.add(object);
            }
        });
        return objects.size();
    }

    public void update(long timeElapsed) {
        frameTime = timeElapsed / 1_000_000_000f;
        for(GameObject object : objects){
            object.update();
        }
    }

    public void draw(Canvas canvas) {
        for(GameObject object : objects){
            object.draw(canvas);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }
}
