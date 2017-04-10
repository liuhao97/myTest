package com.bawei.test;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liuhao
 * on 2017/4/10
 * use to :
 */

public class CircleProgress extends View {
    private Paint circlePaint, arcPaint;
    private RectF rectF;

    int i = 0;
    private int mCircleWidth;
    private int mCircleColor;
    private int mProgressColor;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(i < 360){
                i += 12;
                postInvalidate();
                postDelayed(this, 1000);
            }

        }
    };

    public CircleProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initAttrs(attrs);

        initPaint();
    }

    private void initPaint() {
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(mCircleColor);

        arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setColor(mProgressColor);

        rectF = new RectF();
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ProgressCircle);
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int attr = typedArray.getIndex(i);
            switch (attr){
                case R.styleable.ProgressCircle_circleWidth:
                    mCircleWidth = (int) typedArray.getDimension(attr, 0);
                    break;
                case R.styleable.ProgressCircle_circleColor:
                    mCircleColor = typedArray.getColor(attr, 0);
                    break;
                case R.styleable.ProgressCircle_progressColor:
                    mProgressColor = typedArray.getColor(attr, 0);
                    break;
            }
        }
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float o = getWidth() / 2;
        float radius = o - mCircleWidth;

        rectF.left = o - radius - mCircleWidth / 2;
        rectF.top = o - radius - mCircleWidth / 2;
        rectF.right = o + radius + mCircleWidth / 2;
        rectF.bottom = o + radius + mCircleWidth / 2;

        arcPaint.setStrokeWidth(mCircleWidth);

        canvas.drawCircle(o, o, radius, circlePaint);

        int progress = i*10/36;
        canvas.drawArc(rectF, 270, i, false, arcPaint);

    }

    public void start(){
        postDelayed(runnable, 1000);
    }

    public void reset(){
        i = 0;
        postInvalidate();
    }
}
