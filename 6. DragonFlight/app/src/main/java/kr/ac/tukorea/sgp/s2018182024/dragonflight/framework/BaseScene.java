package kr.ac.tukorea.sgp.s2018182024.dragonflight.framework;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.view.MotionEvent;

import java.lang.reflect.Array;
import java.util.ArrayList;

import kr.ac.tukorea.sgp.s2018182024.dragonflight.BuildConfig;

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

    public <E extends Enum<E>> void addObject(E layerIndex, GameObject object) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<GameObject> objects = layers.get(layerIndex.ordinal());
                objects.add(object);
            }
        });
    }

    public void removeObject(GameObject object) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                // 레이어에서 찾아서 삭제, 없다면 다음 레이어에서 삭제
                // 삭제되었다면 재활용
                for(ArrayList<GameObject> objects : layers) {
                    boolean removed = objects.remove(object);
                    if(removed) {
                        if(object instanceof Recyclable) {
                            RecycleBin.collect((Recyclable) object);
                        }
                        break;
                    }
                }
            }
        });
    }

    public void update(long timeElapsed) {
        frameTime = timeElapsed / 1_000_000_000f;
        for(ArrayList<GameObject> objects : layers) {
            for(GameObject obj : objects){
                obj.update();
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

    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }
}
