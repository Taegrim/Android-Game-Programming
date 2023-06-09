package kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.scene;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.tukorea.sgp.s2018182024.dragonflight.BuildConfig;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.interfaces.CollisionObject;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.interfaces.GameObject;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.interfaces.Recyclable;

public class BaseScene {
    private static ArrayList<BaseScene> stack = new ArrayList<>();
    protected ArrayList<ArrayList<GameObject>> layers;
    public static float frameTime;
    protected static Handler handler = new Handler();
    private static Paint collisionPaint;

    protected <E extends Enum<E>> void initLayers(E enumCount) {
        int layerCount = enumCount.ordinal();
        layers = new ArrayList<>();
        for(int i = 0; i < layerCount; ++i) {
            layers.add(new ArrayList<>());
        }
    }

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
        int count = 0;
        for(ArrayList<GameObject> objects : layers) {
            count += objects.size();
        }
        return count;
    }

    public int pushScene() {
        stack.add(this);
        return stack.size();
    }

    public int popScene() {
        stack.remove(this);
        return stack.size();
    }

    public <E extends Enum<E>> void addObject(E layerIndex, GameObject object, boolean immediate) {
        if(immediate) {
            addObject(layerIndex, object);
            return;
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                addObject(layerIndex, object);
            }
        });
    }

    public <E extends Enum<E>> void addObject(E layerIndex, GameObject object) {
        layers.get(layerIndex.ordinal()).add(object);
    }

    public <E extends Enum<E>> void removeObject(E layerIndex, GameObject object, boolean immediate) {
        if(immediate) {
            removeObject(layerIndex, object);
            return;
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                // 레이어에서 찾아서 삭제, 없다면 다음 레이어에서 삭제
                // 삭제되었다면 재활용
                removeObject(layerIndex, object);
            }
        });
    }

    public <E extends Enum<E>> void removeObject(E layerIndex, GameObject object) {
        boolean removed = getObjects(layerIndex).remove(object);
        if(removed && object instanceof Recyclable) {
            RecycleBin.collect((Recyclable) object);
        }
    }

    public <E extends Enum<E>> ArrayList<GameObject> getObjects(Enum layerIndex) {
        return layers.get(layerIndex.ordinal());
    }

    public void update(long timeElapsed) {
        frameTime = timeElapsed / 1_000_000_000f;
        for(ArrayList<GameObject> objects : layers) {
            for(int i = objects.size() - 1; i >= 0; --i){
                objects.get(i).update();
            }
        }
    }

    public void draw(Canvas canvas) {
        for(ArrayList<GameObject> objects : layers) {
            for(GameObject obj : objects){
                obj.draw(canvas);
            }
        }

        if(BuildConfig.DEBUG){
            if(collisionPaint == null){
                collisionPaint = new Paint();
                collisionPaint.setStyle(Paint.Style.STROKE);
                collisionPaint.setColor(Color.RED);
            }
            for(ArrayList<GameObject> objects : layers) {
                for (GameObject obj : objects) {
                    if (!(obj instanceof CollisionObject)) continue;

                    RectF rect = ((CollisionObject) obj).getCollisionRect();
                    canvas.drawRect(rect, collisionPaint);
                }
            }
        }
    }

    public boolean clipsRect() {
        return true;
    }

    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }
}
