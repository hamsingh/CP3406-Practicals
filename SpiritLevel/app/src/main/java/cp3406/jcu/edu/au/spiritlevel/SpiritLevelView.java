package cp3406.jcu.edu.au.spiritlevel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class SpiritLevelView extends View {

    private Rect horizontalRect, verticalRect;
    private Paint rectPaint, cirPaint, linePaint;

    private final int radius = 50;
    float cx, cy;

    public SpiritLevelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //Process
        float screenHeight = getResources().getDisplayMetrics().heightPixels;
        float screenWidth = getResources().getDisplayMetrics().widthPixels;
        setPaints();

        horizontalRect = new Rect(0, (int) (screenHeight / 2 + 100), (int) (screenWidth), (int) (screenHeight / 2 - 100));
        verticalRect = new Rect((int) (screenWidth / 2 - 100), 0,
                (int) (screenWidth / 2 + 100), (int) (screenHeight));

        cx = horizontalRect.centerX();
        cy = verticalRect.centerY();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawRect(horizontalRect, rectPaint);
        canvas.drawRect(verticalRect, rectPaint);

        //Horizontal rect line
        canvas.drawLine(horizontalRect.centerX()-100, horizontalRect.top,
                horizontalRect.centerX()-100, horizontalRect.bottom, linePaint);
        canvas.drawLine(horizontalRect.centerX()+100, horizontalRect.top,
                horizontalRect.centerX()+100, horizontalRect.bottom, linePaint);

        //Vertical rect lines
        canvas.drawLine(verticalRect.left, verticalRect.centerY()-100, verticalRect.right,
                verticalRect.centerY()-100, linePaint);
        canvas.drawLine(verticalRect.left, verticalRect.centerY()+100, verticalRect.right,
                verticalRect.centerY()+100, linePaint);


        canvas.drawCircle(cx, horizontalRect.centerY(), radius, cirPaint);    //horizontal
        canvas.drawCircle(verticalRect.centerX(), cy, radius, cirPaint);    //vertical
    }

    private void setPaints() {

        rectPaint = new Paint();
        rectPaint.setColor(Color.parseColor("#fff486"));

        cirPaint = new Paint();
        cirPaint.setColor(Color.parseColor("#ff6363"));

        linePaint = new Paint();
        linePaint.setStrokeWidth(10);
        linePaint.setColor(Color.BLACK);
    }

    public Rect getHorizontalRect() {
        return horizontalRect;
    }

    public Rect getVerticalRect() {
        return verticalRect;
    }

    public void setCx(float x) {
        cx = x;
    }

    public float getCx() {
        return cx;
    }

    public void setCy(float y) {
        cy = y;
    }

    public float getCy() {
        return cy;
    }

    public int getCircleRadius() {
        return radius;
    }

}
