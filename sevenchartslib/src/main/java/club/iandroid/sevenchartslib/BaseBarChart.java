package club.iandroid.sevenchartslib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import java.util.List;

import club.iandroid.sevenchartslib.entity.BarEntity;
import club.iandroid.sevenchartslib.utils.Utils;

/**
 * 柱状图基类
 */
public class BaseBarChart extends View {

    private YRender yRender;

    // 坐标轴 轴线 画笔：
    private Paint xLinePaint;
    // 坐标轴水平内部 虚线画笔
    private Paint hLinePaint;
    // 绘制X轴文本的画笔
    private Paint xLabelPaint;
    // 矩形画笔 柱状图的样式信息
    private Paint topTextPaint;
    private Paint mRenderPaintDash;
    private Paint mBarBorderPaint;

    // 显示各个柱状的数据
    private List<BarEntity> barEntities;
    // 实现动画的值
    private float[] aniProgress;
    // 在柱状图上显示数字
    private final boolean mDrawTextAtTop = true;

    // 是否使用动画
    protected int flag;
    //每屏幕显示的最多的lable数量 奇数
    private int showLableCount = 5;
    //线条左侧偏移
    private int xLeftLineOffset = 50;
    //bar的宽度
    private float barWidthPercent = 0.08f;
    //bar的间距
    private int barMargin = 0;
    //x轴文本的高度
    private int xLabelHeight = 50;
    //x轴柱状图左侧的偏移
    private int xLeftOffset = 10;
    //x轴柱状图右侧的偏移
    private int xRightOffset = 10;
    //x轴居中位置的中心点初始位置
    private int xMiddleOffset;
    private int mColor;

    protected HistogramAnimation barAnimation;

    public BaseBarChart(Context context) {
        super(context);
        init(context);
    }

    public BaseBarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BaseBarChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mColor = Color.parseColor("#73A3EB");

        barAnimation = new HistogramAnimation();
        barAnimation.setDuration(1000);

        xLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        hLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        xLabelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


        topTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        topTextPaint.setTextAlign(Align.CENTER);

        mRenderPaintDash = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRenderPaintDash.setStyle(Paint.Style.FILL);

        mBarBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBarBorderPaint.setStyle(Paint.Style.STROKE);

        // 给画笔设置颜色
        xLinePaint.setColor(Color.DKGRAY);
        hLinePaint.setColor(Color.LTGRAY);
        xLabelPaint.setColor(Color.BLACK);

    }

    public void setBarEntities(List<BarEntity> barEntities) {
        this.barEntities = barEntities;
        aniProgress = new float[barEntities.size()];
    }

    public List<BarEntity> getBarEntities() {
        return barEntities;
    }

    public void setyRender(YRender yRender) {
        this.yRender = yRender;
    }

    public int getxLabelHeight() {
        return xLabelHeight;
    }

    public void setShowLableCount(int showLableCount) {
        this.showLableCount = showLableCount;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measuredHeight = MeasureSpec.getSize(heightMeasureSpec);
        int factWidth = getCanvasWidth();
        setMeasuredDimension(factWidth, measuredHeight);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();

        //柱状图面板实际高度
        int height = getFactHeight();
        //y轴间隔高度
        int hPerHeight = yRender.gethPerHeight();
        //y轴线
        hLinePaint.setTextAlign(Align.CENTER);
        //柱状图的宽度
        int barStep = getBarWidth();
        //0坐标的高度
        int bottom = getZeroLineHeight();
        int lineWidth = getCanvasWidth();
        // 画y轴线
        drawYLines(canvas, hPerHeight, lineWidth);
        // 绘制zero的线条
        drawZeroLine(canvas, bottom, lineWidth);
        //生成柱状图的间隔距离
        generateBarMargin();
        // 绘制矩形柱状图
        drawRectBar(canvas, barStep, bottom, bottom);

        if (barEntities != null && barEntities.size() > 0) {
            // 设置底部的文字
            int columCount = barEntities.size();
            drawXLables(canvas, barStep, height, columCount);
        }
        canvas.restore();
    }

    /**
     * 获取0坐标的位置
     */
    private int getZeroLineHeight() {
        int perHeight = yRender.gethPerHeight();
        float count = ((float) yRender.getMaxValue() - 0) / (float) yRender.getvPerValue();
        int zeroHeight = Math.round(count * (float) perHeight);
        return zeroHeight;
    }

    /**
     * 实际高度
     *
     * @return
     */
    private int getFactHeight() {
        return getHeight() - Utils.dp2px(xLabelHeight);
    }


    /**
     * 绘制Y轴线条
     */
    private void drawYLines(Canvas canvas, int hPerHeight, int width) {

        for (int i = 0; i < yRender.getShowLableCount(); i++) {
            canvas.drawLine(Utils.dp2px(xLeftLineOffset), i * hPerHeight + Utils.dp2px(yRender.getyLineOffset()),
                    width, i * hPerHeight + Utils.dp2px(yRender.getyLineOffset()), hLinePaint);
        }
    }

    /**
     * 绘制0坐标的线条
     *
     * @param canvas
     */
    private void drawZeroLine(Canvas canvas, int height, int width) {
        //判断是否已经有0坐标
        if (!yRender.getmValues().contains(0)) {
            canvas.drawLine(Utils.dp2px(xLeftLineOffset), height + Utils.dp2px(yRender.getyLineOffset()), width, height + Utils.dp2px(yRender.getyLineOffset())
                    , xLinePaint);

            //画zero的文字
        }

    }

    /**
     * 绘制X轴Labels
     */
    private void drawXLables(Canvas canvas, int step, int height, int columCount) {
        xLabelPaint.setTextAlign(Align.CENTER);
        xLabelPaint.setTextSize(Utils.sp2px(10));
        xLabelPaint.setAntiAlias(true);
        xLabelPaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < columCount; i++) {
            int left = Utils.dp2px(xLeftLineOffset) + step * i + barMargin * i + (step / 2) + getxLeftOffset();

            canvas.drawText(barEntities.get(i).getmLabel(), left, height, xLabelPaint);
        }
    }

    /**
     * 绘制矩形柱状图
     */
    private void drawRectBar(Canvas canvas, int step, int zeroHeight, int bottom) {
        if (aniProgress != null && aniProgress.length > 0) {
            for (int i = 0; i < barEntities.size(); i++) {// 循环遍历将7条柱状图形画出来
                float value = aniProgress[i];
                topTextPaint.setAntiAlias(true);// 抗锯齿效果
                topTextPaint.setStyle(Paint.Style.FILL);
                topTextPaint.setTextSize(Utils.sp2px(9));// 字体大小

                mBarBorderPaint.setColor(mColor);
                mBarBorderPaint.setStrokeWidth(Utils.dp2px(1));
                Rect rect = new Rect();// 柱状图的形状

                rect.left = Utils.dp2px(xLeftLineOffset) + getxLeftOffset() + step * i + barMargin * i;
                rect.right = Utils.dp2px(xLeftLineOffset) + getxLeftOffset() + step * (i + 1) + barMargin * i;
                if (yRender.getvPerValue() != 0) {
                    //画虚体矩形
                    if (barEntities.get(i).isDrawDash()) {
                        drawDashBarRect(canvas, rect, i, step, bottom, value);
                    } else {
                        topTextPaint.setColor(mColor);
                        //实体矩形
                        drawFillBarRect(canvas, rect, i, step, bottom, value);
                    }

                }
            }
        }
    }

    /**
     * 画虚体柱状图
     *
     * @param canvas
     * @param rect
     * @param index
     * @param step
     * @param bottom
     * @param value
     */
    private void drawDashBarRect(Canvas canvas, Rect rect, int index, int step, int bottom, float value) {
        if (value >= 0) {//大于0
            float rh = bottom + Utils.dp2px(yRender.getyLineOffset()) - value * yRender.gethPerHeight() / yRender.getvPerValue();

            rect.top = Math.round(rh);
            rect.bottom = bottom + Utils.dp2px(yRender.getyLineOffset());

            int width = rect.right - rect.left;
            int height = rect.bottom - rect.top;

            //背景白色矩形
            topTextPaint.setColor(Color.WHITE);
            canvas.drawRect(rect, topTextPaint);

            mRenderPaintDash.setColor(mColor);

            float startX = rect.left;
            float startY = rect.top;
            float stopX = rect.right;
            float stopY = rect.bottom;

            int tempheightDiff = 10;
            if (height < 10) {
                tempheightDiff = 2;
            }

            if (height > width) {

                /**
                 * 右上角
                 */
                float rightStartX = stopX - tempheightDiff;
                float rightStartY = startY + tempheightDiff;
                while (rightStartX > startX) {
                    canvas.drawLine(rightStartX, startY, stopX, rightStartY, mRenderPaintDash);
                    rightStartX -= tempheightDiff;
                    rightStartY += tempheightDiff;
                }

                /**
                 * 中间
                 */
                float endY = rightStartY;
                startY += tempheightDiff / 2;

                float middelStartY = startY;
                while (endY < rect.bottom) {
                    canvas.drawLine(startX, middelStartY, stopX, endY, mRenderPaintDash);
                    middelStartY += tempheightDiff;
                    endY += tempheightDiff;
                }

                /**
                 * 左下角
                 */
                float leftStartX = startX;
                float leftStartY = middelStartY;
                float leftStopX = rect.right - tempheightDiff / 2;

                while (leftStartY < rect.bottom) {
                    canvas.drawLine(leftStartX, leftStartY, leftStopX, rect.bottom, mRenderPaintDash);
                    leftStartY += tempheightDiff;
                    leftStopX -= tempheightDiff;
                }
            } else if (height <= width) {

                /**
                 * 右上角
                 */
                float rightStartX = stopX - tempheightDiff;
                float rightStartY = startY + tempheightDiff;
                while (rightStartX > startX) {
                    if (rightStartY > stopY) {
                        rightStartY = stopY;
                    }
                    canvas.drawLine(rightStartX, startY, stopX, rightStartY, mRenderPaintDash);
                    rightStartX -= tempheightDiff;
                    rightStartY += tempheightDiff;
                }


                /**
                 * 左下角
                 */
                float leftStartX = startX;
                float leftStartY = startY + tempheightDiff / 2;
                float leftStopX = rect.right - tempheightDiff / 2;

                while (leftStartY < rect.bottom) {
                    canvas.drawLine(leftStartX, leftStartY, leftStopX, rect.bottom, mRenderPaintDash);
                    leftStartY += tempheightDiff;
                    leftStopX -= tempheightDiff;
                }
            }


            // 显示柱状图上方的数字
            if (mDrawTextAtTop) {
                int left = Utils.dp2px(xLeftLineOffset) + step * index + barMargin * index + step / 2;

                canvas.drawText(value + "", left, rh - Utils.dp2px(60)
                        + Utils.dp2px(xLabelHeight), topTextPaint);
            }
        } else if (value < 0) {
            float rbottom = bottom + Utils.dp2px(yRender.getyLineOffset()) - value * yRender.gethPerHeight() / yRender.getvPerValue();

            rect.top = bottom + Utils.dp2px(yRender.getyLineOffset());
            rect.bottom = Math.round(rbottom);

            canvas.drawRect(rect, topTextPaint);

        }

        canvas.drawRect(rect.left, rect.top, rect.right,
                rect.bottom, mBarBorderPaint);


    }

    /**
     * 画实体柱状图
     *
     * @param canvas
     * @param rect
     * @param index
     * @param step
     * @param bottom
     * @param value
     */
    private void drawFillBarRect(Canvas canvas, Rect rect, int index, int step, int bottom, float value) {
        if (value >= 0) {//大于0
            float rh = bottom + Utils.dp2px(yRender.getyLineOffset()) - value * yRender.gethPerHeight() / yRender.getvPerValue();

            rect.top = Math.round(rh);
            rect.bottom = bottom + Utils.dp2px(yRender.getyLineOffset());

            canvas.drawRect(rect, topTextPaint);
            // 显示柱状图上方的数字
            if (mDrawTextAtTop) {
                int left = Utils.dp2px(xLeftLineOffset) + step * index + barMargin * index + step / 2;

                canvas.drawText(value + "", left, rh - Utils.dp2px(30)
                        + Utils.dp2px(xLabelHeight), topTextPaint);
            }
        } else if (value < 0) {
            float rbottom = bottom + Utils.dp2px(yRender.getyLineOffset()) - value * yRender.gethPerHeight() / yRender.getvPerValue();

            rect.top = bottom + Utils.dp2px(yRender.getyLineOffset());
            rect.bottom = Math.round(rbottom);

            canvas.drawRect(rect, topTextPaint);
        }
        canvas.drawRect(rect.left, rect.top, rect.right,
                rect.bottom, mBarBorderPaint);
    }

    /**
     * 左侧柱状图的偏移量
     *
     * @return
     */
    public int getxLeftOffset() {
        return Utils.dp2px(xLeftOffset);
    }

    /**
     * 右侧柱状图的偏移量
     *
     * @return
     */
    public int getxRightOffset() {
        return Utils.dp2px(xRightOffset);
    }

    /**
     * 实际屏幕宽度
     *
     * @return
     */
    public int getCanvasWidth() {
        int factWidth = getScreenWidth() * getScreenCount();
        return factWidth;
    }

    /**
     * 屏幕宽度
     *
     * @return
     */
    public int getTotalBarWidth() {
        return getCanvasWidth() //保证居中
                - Utils.dp2px(xLeftLineOffset) * getScreenCount() * 2;
    }

    /**
     * 屏幕宽度
     *
     * @return
     */
    public int getScreenCount() {
        int count = barEntities.size() / showLableCount;
        if (barEntities.size() % showLableCount != 0) {
            count++;
        }
        return count;
    }

    /**
     * 屏幕宽度
     *
     * @return
     */
    public int getScreenWidth() {
        return getScreenSize(getContext()).widthPixels;
    }

    /**
     * 每屏幕柱状图所在view的宽度
     *
     * @return
     */
    public int getViewWidth() {
        return getScreenWidth() //保证居中
                - Utils.dp2px(xLeftLineOffset) * 2 - getxLeftOffset() * 2;

    }


    /**
     * 柱状图宽度
     *
     * @return
     */
    public int getBarWidth() {
        return Math.round(getViewWidth() * barWidthPercent);
    }


    /**
     * 获取柱状图间隔
     */
    private void generateBarMargin() {
        int width = getViewWidth();
        int perPageBarMarginTotal = width - showLableCount * getBarWidth();
        barMargin = perPageBarMarginTotal / (showLableCount - 1);
    }

    public int getBarMargin() {
        return barMargin;
    }

    /**
     * 设置点击事件，是否显示数字
     */
    /*public boolean onTouchEvent(MotionEvent event) {
        int step = (getWidth() - dp2px(30)) / 8;
        int x = (int) event.getX();
        for (int i = 0; i < 7; i++) {
            if (x > (dp2px(15) + step * (i + 1) - dp2px(15))
                    && x < (dp2px(15) + step * (i + 1) + dp2px(15))) {
                text[i] = 1;
                for (int j = 0; j < 7; j++) {
                    if (i != j) {
                        text[j] = 0;
                    }
                }
                if (Looper.getMainLooper() == Looper.myLooper()) {
                    invalidate();
                } else {
                    postInvalidate();
                }
            }
        }
        return super.onTouchEvent(event);
    }*/

    /**
     * 集成animation的一个动画类
     */
    private class HistogramAnimation extends Animation {
        protected void applyTransformation(float interpolatedTime,
                                           Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            if (interpolatedTime < 1.0f && flag == 2) {
                for (int i = 0; i < barEntities.size(); i++) {
                    aniProgress[i] = (barEntities.get(i).getmValue() * interpolatedTime);
                }
            } else {
                for (int i = 0; i < barEntities.size(); i++) {
                    aniProgress[i] = barEntities.get(i).getmValue();
                }
            }
            invalidate();
        }
    }

    /**
     * 获取屏幕相关参数
     *
     * @param context
     * @return
     */
    public static DisplayMetrics getScreenSize(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(metrics);
        return metrics;
    }

    /**
     * 根据xValue查找当前的x位置
     *
     * @param xValue
     * @return
     */
    public int findFinalXByXValue(String xValue) {
        int targetIndex = 0;
        if (barEntities != null && barEntities.size() > 0) {
            for (int i = 0; i < barEntities.size(); i++) {
                if (barEntities.get(i).getmLabel().equals(xValue)) {
                    targetIndex = i;
                    break;
                }
            }
        }
        //计算targetIndex的x轴位置
        int targetBarX = getBarWidth() * targetIndex + getBarMargin() * targetIndex + getxLeftOffset();
        return targetBarX;
    }

    /**
     * 根据xValue查找当前的x位置
     *
     * @param xValue
     * @return
     */
    public int findFinalPointXByXValue(String xValue) {
        int targetIndex = 0;
        if (barEntities != null && barEntities.size() > 0) {
            for (int i = 0; i < barEntities.size(); i++) {
                if (barEntities.get(i).getmLabel().equals(xValue)) {
                    targetIndex = i;
                    break;
                }
            }
        }
        targetIndex++;
        //计算targetIndex的x轴位置
        int targetBarX = getBarWidth() * targetIndex + getBarMargin() * targetIndex + getxLeftOffset();
        return targetBarX;
    }

    /**
     * 根据yValue获得y坐标
     *
     * @param yValue
     * @return
     */
    public float findFinalYByValue(float yValue) {
        int bottom = getZeroLineHeight();
        float rh = bottom + Utils.dp2px(yRender.getyLineOffset()) - yValue * yRender.gethPerHeight() / yRender.getvPerValue();
        return rh;
    }

    /**
     * x轴居中的位置
     *
     * @return
     */
    public int findCenterX() {
        return (getScreenWidth() - yRender.getyWidthDefault() * 2) / 2;
    }

}