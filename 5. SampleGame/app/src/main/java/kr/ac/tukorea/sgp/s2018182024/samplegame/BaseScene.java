package kr.ac.tukorea.sgp.s2018182024.samplegame;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

public class BaseScene {
    private static ArrayList<BaseScene> stack = new ArrayList<>();
    private ArrayList<GameObject> objects = new ArrayList<>();

    public static BaseScene getTopScene() {
        return stack.get(stack.size() - 1);
    }

    public int pushScene() {
        stack.add(this);
        return stack.size();
    }

    public int addObject(GameObject object) {
        objects.add(object);
        return objects.size();
    }

    public void update() {
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
