package kr.ac.tukorea.sgp.s2018182024.dragonflight.framework;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class RecycleBin {
    private static final String TAG = Recyclable.class.getSimpleName();
    private static HashMap<Class, ArrayList<Recyclable>> recycleBin = new HashMap<>();

    public static void collect(Recyclable object) {
        Class c = object.getClass();
        ArrayList<Recyclable> arr = recycleBin.get(c);
        if(arr == null){
            arr = new ArrayList<>();
            recycleBin.put(c, arr);
        }
        if(arr.indexOf(object) >= 0){
            Log.d(TAG, "Already collected object : " + object);
            return;
        }

        object.onRecycle();
        arr.add(object);
        Log.d(TAG, "collect() : " + c.getSimpleName() + ", remain : " + (arr.size()));
    }

    public static Recyclable get(Class c) {
        ArrayList<Recyclable> arr = recycleBin.get(c);
        if(arr == null) {
            return null;
        }
        if(arr.size() == 0) {
            return null;
        }
        Log.d(TAG, "get() : " + c.getSimpleName() + ", remain : " + (arr.size() - 1));
        return arr.remove(0);
    }

}
