package kr.ac.tukorea.sgp.s2018182024.samplegame;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

public class BaseScene {
    private static ArrayList<BaseScene> stack = new ArrayList<>();
    private ArrayList<GameObject> objects = new ArrayList<>();
    public static float frameTime;

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
        objects.add(object);
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
