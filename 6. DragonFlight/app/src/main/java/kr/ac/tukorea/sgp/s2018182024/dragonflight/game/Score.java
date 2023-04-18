package kr.ac.tukorea.sgp.s2018182024.dragonflight.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import kr.ac.tukorea.sgp.s2018182024.dragonflight.R;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.BitmapPool;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.GameObject;

public class Score implements GameObject {
    private static final String TAG = Score.class.getSimpleName();
    private final Bitmap bitmap;
    private Rect srcRect = new Rect();
    private RectF dstRect = new RectF();
    private final int srcCharWidth, srcCharHeight;
    private final float perCharWidth, perCharHeight;
    private final float x, y;
    private int score, displayScore;

    Score() {
        this.bitmap = BitmapPool.get(R.mipmap.number_24x32);
        this.srcCharWidth = bitmap.getWidth() / 10;
        this.srcCharHeight = bitmap.getHeight();
        this.perCharWidth = 0.6f;
        this.perCharHeight = perCharWidth * srcCharHeight / srcCharWidth;
        this.x = 0.5f;
        this.y = 0.5f;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void changeScore(int value) {
        score += value;
    }

    @Override
    public void update() {
        int diff = score - displayScore;
        if(diff == 0)
            return;

        if(-10 < diff && diff < 0) {
            --displayScore;
        }
        else if(0 < diff && diff < 10) {
            ++displayScore;
        }
        else {
            displayScore += diff / 10;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        int score = this.displayScore;
        float x = this.x;
        int exp = (int)(Math.log10(score));
        while(exp >= 0) {
            int value = (int)Math.pow(10, exp);
            int digit = score / value;
            srcRect.set(digit * srcCharWidth, 0,
                    (digit + 1) * srcCharWidth, srcCharHeight);
            dstRect.set(x, this.y, x + perCharWidth, this.y + perCharHeight);
            x += perCharWidth;
            canvas.drawBitmap(bitmap, srcRect, dstRect, null);
            score %= value;
            exp -= 1;
        }
    }
}
