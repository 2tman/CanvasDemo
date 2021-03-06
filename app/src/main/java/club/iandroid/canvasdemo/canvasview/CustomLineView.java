package club.iandroid.canvasdemo.canvasview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义View
 * 自定义绘制的上手非常容易：
 * 提前创建好 Paint 对象，
 * 重写 onDraw()，把绘制代码写在 onDraw() 里面，
 * 就是自定义绘制最基本的实现。
 * <p>
 * 绘制椭圆
 * Created by 加荣 on 2017/8/9.
 */

public class CustomLineView extends View {

    //提前创建好Paint对象 抗锯齿
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//    Paint paint = new Paint();

    public CustomLineView(Context context) {
        super(context);
    }

    public CustomLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //重写onDraw(),把绘制代码写在onDraw()里面

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 由于直线不是封闭图形，所以paint.setStyle 对直线没有影响
         * paint类的几个常用的方法：
         */


        paint.setColor(Color.RED);//设置颜色
        paint.setStrokeWidth(20);//设置线条宽度
        paint.setTextSize(12);//设置文字大小
//        paint.setAntiAlias(true);//设置抗锯齿开关


        canvas.drawLine(100, 200, 400, 500, paint);

        /**
         * 批量画线
         * 四个为一组
         */
        float[] points = {20, 20, 120, 20,
                            70, 20, 70, 120,
                            20, 120, 120, 120,
                            150, 20, 250, 20,
                            150, 20, 150, 120,
                            250, 20, 250, 120,
                            150, 120, 250, 120};
        canvas.drawLines(points, paint);
    }
}
