package club.iandroid.sevenchartslib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import java.util.List;

import club.iandroid.sevenchartslib.entity.LineEntity;
import club.iandroid.sevenchartslib.utils.Utils;

/**
 * 线形图+柱状图
 * Created by 加荣 on 2017/7/27.
 */

public class BarLineChart extends BaseBarChart {

    //绘制线条
    private Paint mLinePaint;
    //绘制圆点
    private Paint mPointPaint;
    //线形值
    private List<LineEntity> mLineValues;
    private int pointWidth = 4;
    private int mColor;

    public List<LineEntity> getLineValues() {
        return mLineValues;
    }

    public void setLineValues(List<LineEntity> mLineValues) {
        this.mLineValues = mLineValues;
        if(mLineValues!=null && mLineValues.size()>0){
            animProgress = new float[mLineValues.size()];
        }
    }

    public int getmColor() {
        return mColor;
    }

    public void setmColor(int mColor) {
        this.mColor = mColor;
    }

    public BarLineChart(Context context) {
        super(context);
        initView();
    }

    public BarLineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BarLineChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        //线形图paint
        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);

        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(Utils.dp2px(1));

        mPointPaint = new Paint();
        mPointPaint.setAntiAlias(true);

        histogramAnimation = new HistogramAnimation();
        histogramAnimation.setDuration(2000);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawLines(canvas);

        super.onDraw(canvas);

    }

    private void drawLines(Canvas canvas) {
        if (mLineValues != null && mLineValues.size() > 0) {
            mPointPaint.setColor(mColor);
            mLinePaint.setColor(mColor);
            Path path = new Path();// 折线图的路径
            for (int i = 0; i < mLineValues.size(); i++) {
                LineEntity lineEntityStart = mLineValues.get(i);

                float entityX = findFinalPointXByXValue(lineEntityStart.getmLabel()) - Utils.dp2px(pointWidth);
                float entityY = findFinalYByValue(animProgress[i]);
                        //findFinalYByValue(lineEntityStart.getmValue());


                //画圆点
                canvas.drawCircle(entityX, entityY, Utils.dp2px(pointWidth), mPointPaint);

                if (i == 0) {
                    path.moveTo(entityX, entityY);
                } else {
                    path.lineTo(entityX, entityY);
                }
                canvas.drawPath(path, mLinePaint);
            }
        }
    }

    // 是否使用动画
    private int flag;
    private float[] animProgress;
    private HistogramAnimation histogramAnimation;
    public void animShow(int flag) {
        startAnimation(histogramAnimation);
        this.flag = flag;

        start(flag);
    }

    /**
     * 集成animation的一个动画类
     *
     * @author
     */
    private class HistogramAnimation extends Animation {
        @Override
        protected void applyTransformation(float interpolatedTime,
                                           Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            if (interpolatedTime < 1.0f && flag == 2) {
                for (int i = 0; i < animProgress.length; i++) {
                    animProgress[i] = mLineValues.get(i).getmValue() * interpolatedTime;
                }
            } else {
                for (int i = 0; i < animProgress.length; i++) {
                    animProgress[i] = mLineValues.get(i).getmValue();
                }
            }
            invalidate();
        }
    }

}
