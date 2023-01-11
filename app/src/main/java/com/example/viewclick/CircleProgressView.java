package com.example.viewclick;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.text.DecimalFormat;


public class CircleProgressView extends View {


    private RectF mWheelRect = new RectF();
    private Paint mDefaultWheelPaint;
    private Paint mFinishWheelPaint;
    private Paint mCenterWheelPaint;
    private Paint mTargetPaint;
    private float mCircleStrokeWidth;
    private float mSweepAnglePer;
    private float mPercent;
    private int mStepNum, mCurrStepNum;
    private float pressExtraStrokeWidth;
    private int mMaxStepNum;// 默认最大步数
    private float mTitleY, mStepY, mTargetY;
    private DecimalFormat mDecimalFormat = new DecimalFormat("#.0");// 格式为保留小数点后一位

    public CircleProgressView(Context context) {
        super(context);
        init(null, 0);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CircleProgressView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {

        mFinishWheelPaint = new Paint();
        mFinishWheelPaint.setColor(Color.rgb(35, 116, 255));
        mFinishWheelPaint.setStyle(Paint.Style.STROKE);// 空心
        mFinishWheelPaint.setStrokeCap(Paint.Cap.ROUND);// 圆角画笔
        mFinishWheelPaint.setAntiAlias(true);// 去锯齿

        mCenterWheelPaint = new Paint();
        mCenterWheelPaint.setColor(Color.rgb(255, 255, 255));
        mCenterWheelPaint.setStyle(Paint.Style.FILL);
        mCenterWheelPaint.setStrokeCap(Paint.Cap.ROUND);
        mCenterWheelPaint.setAntiAlias(true);

        mDefaultWheelPaint = new Paint();
        mDefaultWheelPaint.setColor(Color.rgb(246, 245, 249));
        mDefaultWheelPaint.setStyle(Paint.Style.STROKE);
        mDefaultWheelPaint.setStrokeCap(Paint.Cap.ROUND);
        mDefaultWheelPaint.setAntiAlias(true);


        mTargetPaint = new Paint();
        mTargetPaint.setAntiAlias(true);
        mTargetPaint.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawArc(mWheelRect, 0, 359, false, mDefaultWheelPaint);
        if (mCurrStepNum != 0) {
            canvas.drawArc(mWheelRect, 0, mSweepAnglePer, false, mFinishWheelPaint);
        }
        float[] b = getPoint(mWheelRect.centerX(), mWheelRect.centerY(), mSweepAnglePer + 90, mWheelRect.width() / 2);
        if (mCurrStepNum != 100 && mCurrStepNum != 0) {
            canvas.drawCircle(b[0], b[1], dip2px(6), mCenterWheelPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int min = Math.min(width, height);// 获取View最短边的长度
        setMeasuredDimension(min, min);// 强制改View为以最短边为长度的正方形
        mCircleStrokeWidth = getTextScale(15, min);// 圆弧的宽度
        pressExtraStrokeWidth = getTextScale(10, min);// 圆弧离矩形的距离
        mWheelRect.set(mCircleStrokeWidth + pressExtraStrokeWidth, mCircleStrokeWidth + pressExtraStrokeWidth,
                min - mCircleStrokeWidth - pressExtraStrokeWidth, min - mCircleStrokeWidth - pressExtraStrokeWidth);// 设置矩形
        mTargetPaint.setTextSize(getTextScale(40, min));
        mTitleY = getTextScale(170, min);
        mStepY = getTextScale(300, min);
        mTargetY = getTextScale(380, min);
        mFinishWheelPaint.setStrokeWidth(dip2px(15));
        mDefaultWheelPaint.setStrokeWidth(dip2px(15));
    }


    public float getPercent() {
        return mPercent;
    }

    /**
     * 根据控件的大小改变绝对位置的比例
     *
     * @param n
     * @param m
     * @return
     */
    public float getTextScale(float n, float m) {
        return n / 500 * m;
    }

    /**
     * 更新步数和设置一圈动画时间
     *
     * @param stepCount
     * @param time
     */
    public void update(int stepCount) {
        this.mStepNum = stepCount;

        mPercent = Float.parseFloat(mDecimalFormat.format(mStepNum * 100f / mMaxStepNum));// 将浮点值四舍五入保留一位小数
        if (mPercent > 100.0f) {
            mPercent = 100.0f;
        }
        mSweepAnglePer = mStepNum * 360 / mMaxStepNum;
        mCurrStepNum = mStepNum;
        requestLayout();
    }

    /**
     * @param stepNum
     */
    public void setMaxStepNum(int stepNum) {
        mMaxStepNum = stepNum;
    }

    public void setColor(int color) {
        mFinishWheelPaint.setColor(color);
    }


    public int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * 根据角度和边长获取位置
     *
     * @param p      起点位置
     * @param degree 角度
     * @param width  边长
     * @return 位置 抠抠群721765299
     */
    public float[] getPoint(float pX, float pY, double degree, double width) {
        degree = degree > 360 ? degree - 360 : degree;
        int v = (int) (degree / 90);
        double d = (degree % 90);
        float side = (float) getRightSideFromDegree(d, width);//与中心的直角边长
        float top = (float) getRightSideFromLength(side, width);//另一直角边
        float x = 0, y = 0;
        switch (v) {
            case 0:
                x = (pX + top);
                y = (pY - side);
                break;
            case 1:
                x = (pX + side);
                y = (pY + top);
                break;
            case 2:
                x = (pX - top);
                y = (pY + side);
                break;
            case 3:
                x = (pX - side);
                y = (pY - top);
                break;
        }
        return new float[]{x, y};
    }

    /**
     * 直角三角形 根据角度和斜边求直角边
     *
     * @param degree 角度
     * @param width  斜边
     * @return 直角边长
     */
    public double getRightSideFromDegree(double degree, double width) {
        double cos = Math.cos(Math.toRadians(degree));
        return width * cos;
    }

    /**
     * 直角三角形 根据直角边和斜边求直角边
     *
     * @param a 直角边
     * @param b 斜边
     * @return 直角边长
     */
    public double getRightSideFromLength(double a, double b) {
        return Math.sqrt(b * b - a * a);
    }


}

