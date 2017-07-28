package club.iandroid.sevenchartslib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;

import java.util.LinkedList;
import java.util.List;

import club.iandroid.sevenchartslib.entity.DataSet;
import club.iandroid.sevenchartslib.entity.LineEntity;
import club.iandroid.sevenchartslib.utils.Utils;

/**
 * 线形图+柱状图
 * Created by 加荣 on 2017/7/27.
 */

public class BarLineChart extends BaseBarChart {

    //绘制线条
    private Paint mLinePaint;
    //绘制线条虚线
    private Paint mLinePaintDash;
    //绘制圆点
    private Paint mPointPaint;
    //线形值
    private List<DataSet> dataSets;
    //线形动画
    private AnimationSet animationSet;
    private int pointWidth = 4;

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

        mLinePaintDash = new Paint();
        mLinePaintDash.setAntiAlias(true);
        mLinePaintDash.setStyle(Paint.Style.STROKE);
        mLinePaintDash.setStrokeWidth(Utils.dp2px(1));
        mLinePaintDash.setPathEffect(new DashPathEffect(new float[]{
                8f, 10f, 8f, 10f
        }, 0f));

        mPointPaint = new Paint();
        mPointPaint.setAntiAlias(true);

        animationSet = new AnimationSet(true);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        animationSet.addAnimation(barAnimation);
        drawLines(canvas);
    }

    private void drawLines(Canvas canvas) {
        if (dataSets != null && dataSets.size() > 0) {
            for (int k = 0; k < dataSets.size(); k++) {
                List<LineEntity> mLineValues = dataSets.get(k).getDataSet();
                float[] animItem = animProgress.get(k);
                int mColor = dataSets.get(k).getmColor();

                mPointPaint.setColor(mColor);
                mLinePaint.setColor(mColor);
                mLinePaintDash.setColor(mColor);

                for (int i = 0; i < mLineValues.size(); i++) {
                    LineEntity lineEntityStart = mLineValues.get(i);

                    float entityX = findFinalPointXByXValue(lineEntityStart.getmLabel()) - Utils.dp2px(pointWidth);
                    float entityY =
                            findFinalYByValue(animItem[i]);
                    findFinalYByValue(lineEntityStart.getmValue());

                    //画圆点
                    canvas.drawCircle(entityX, entityY, Utils.dp2px(pointWidth), mPointPaint);

                    if (i < mLineValues.size() - 1) {
                        LineEntity lineEntityEnd = mLineValues.get(i + 1);
                        float entityXEnd = findFinalPointXByXValue(lineEntityEnd.getmLabel()) - Utils.dp2px(pointWidth);
                        float entityYEnd =
                                findFinalYByValue(animItem[i + 1]);
//                                findFinalYByValue(lineEntityEnd.getmValue());

                        //判断是否需要画虚线
                        if (lineEntityStart.isDrawDash()) {
                            canvas.drawLine(entityX, entityY, entityXEnd, entityYEnd, mLinePaintDash);
                        } else {
                            canvas.drawLine(entityX, entityY, entityXEnd, entityYEnd, mLinePaint);
                        }
                    }

                }
            }

        }
    }

    /**
     * 设置数据源
     *
     * @param dataSets
     */
    public void setDataSets(List<DataSet> dataSets) {
        this.dataSets = dataSets;
        animProgress = new LinkedList<>();
        int duration = 1000;
        for (int i = 0; i < dataSets.size(); i++) {
            float[] aniItems = new float[dataSets.get(i).getDataSet().size()];
            animProgress.add(aniItems);

            HistogramAnimation lineAnimation = new HistogramAnimation();
            lineAnimation.setDuration(duration);
            animationSet.addAnimation(lineAnimation);

            duration += 200;
        }
    }

    // 是否使用动画
    private int flag;
    private LinkedList<float[]> animProgress;

    public void animShow(int flag) {
        this.flag = flag;
        start(flag);
//
        animationSet.setDuration(1000);
        startAnimation(animationSet);
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
                for (int i = 0; i < dataSets.size(); i++) {
                    List<LineEntity> mLineValues = dataSets.get(i).getDataSet();
                    float[] aniItem = animProgress.get(i);
                    for (int j = 0; j < aniItem.length; j++) {
                        aniItem[j] = mLineValues.get(j).getmValue() * interpolatedTime;
                    }
                    animProgress.set(i, aniItem);
                }

            } else {
                for (int i = 0; i < dataSets.size(); i++) {
                    List<LineEntity> mLineValues = dataSets.get(i).getDataSet();
                    float[] aniItem = animProgress.get(i);
                    for (int j = 0; j < aniItem.length; j++) {
                        aniItem[j] = mLineValues.get(j).getmValue();
                    }
                    animProgress.set(i, aniItem);
                }
            }
            invalidate();
        }
    }

}
