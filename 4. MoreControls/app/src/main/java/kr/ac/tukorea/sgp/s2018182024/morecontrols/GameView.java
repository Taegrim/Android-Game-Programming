package kr.ac.tukorea.sgp.s2018182024.morecontrols;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class GameView extends View {
    private static final String TAG = GameView.class.getSimpleName();
    private Paint paint, facePaint, outlinePaint;
    private Rect rect;
    private RectF ovalRect; // float 값 저장하는 Rect

    public GameView(Context context) {
        super(context);
        init(null, 0);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        facePaint = new Paint();
        facePaint.setColor(Color.YELLOW);

        outlinePaint = new Paint();
        outlinePaint.setColor(Color.BLACK);
        outlinePaint.setStyle(Paint.Style.STROKE);
    }

    private void calcSize(){
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        rect = new Rect(paddingLeft, paddingTop, getWidth() - paddingRight, getHeight() - paddingBottom);
        ovalRect = new RectF(rect.left, rect.top, rect.right, rect.bottom);

        outlinePaint.setStrokeWidth(2);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
        Log.d(TAG, "onSizeChanged: " + w + "," + h);
        calcSize();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        canvas.drawRect(rect, paint);
        setCanvasRect(canvas, rect.left, rect.top, rect.width(), rect.height());
        drawSmiley(canvas);
    }

    private void setCanvasRect(Canvas canvas, float left, float top, float width, float height){
        Log.d(TAG, "setCanvasRect(" + left + ", " +
                top + ", " + width + ", " + height + ")");
        canvas.translate(left, top);
        canvas.scale(width / 100f, height / 100f);
    }

    private void drawSmiley(Canvas canvas){
        canvas.drawOval(0, 0, 100, 100, facePaint);
        canvas.drawCircle(30, 40, 7, outlinePaint);
        canvas.drawCircle(70, 40, 7, outlinePaint);
        canvas.drawArc(20, 20, 80, 80, 30, 120, false, outlinePaint);
    }
}