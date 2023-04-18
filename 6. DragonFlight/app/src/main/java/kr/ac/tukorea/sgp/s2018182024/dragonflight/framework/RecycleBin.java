package kr.ac.tukorea.sgp.s2018182024.dragonflight.framework;

import java.util.ArrayList;
import java.util.HashMap;

public class RecycleBin {
    private static HashMap<Class, ArrayList<Recyclable>> recycleBin = new HashMap<>();

    public static void collect(Recyclable object) {
        Class c = object.getClass();
        ArrayList<Recyclable> arr = recycleBin.get(c);
        if(arr == null){
            arr = new ArrayList<>();
            recycleBin.put(c, arr);
        }
        if(arr.indexOf(object) >= 0){
            return;
        }

        object.onRecycle();
        arr.add(object);
    }

    public static Recyclable get(Class c) {
        ArrayList<Recyclable> arr = recycleBin.get(c);
        if(arr == null) {
            return null;
        }
        if(arr.size() == 0) {
            return null;
        }
        return arr.remove(0);
    }

}
