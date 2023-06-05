package kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.resource;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.HashMap;

import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.view.GameView;

public class BitmapPool {
    private static HashMap<Integer, Bitmap> bitmaps = new HashMap<>();
    private static BitmapFactory.Options opts;

    public static Bitmap get(int resId) {
        Bitmap bitmap = bitmaps.get(resId);
        if(bitmap == null){
            if(opts == null) {
                opts = new BitmapFactory.Options();
                opts.inScaled = false;
            }
            bitmap = BitmapFactory.decodeResource(GameView.res, resId, opts);
            bitmaps.put(resId, bitmap);
        }
        return bitmap;
    }
}
