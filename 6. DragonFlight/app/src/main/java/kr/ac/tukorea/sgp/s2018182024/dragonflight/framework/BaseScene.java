package kr.ac.tukorea.sgp.s2018182024.dragonflight.framework;

import android.graphics.Canvas;
import android.os.Handler;
import android.view.MotionEvent;

import java.util.ArrayList;

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

    public static void popAllScene() {
        while (!stack.isEmpty()) {
            BaseScene scene = getTopScene();
            scene.popScene();
        }
    }

    public int getObjectCount() {
        return objects.size();
    }

    public int pushScene() {
        stack.add(this);
        return stack.size();
    }

    public int popScene() {
        stack.remove(this);
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

    public int removeObject(GameObject object) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                objects.remove(object);
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
