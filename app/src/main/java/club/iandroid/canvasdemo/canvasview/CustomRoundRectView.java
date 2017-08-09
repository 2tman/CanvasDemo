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
 *      提前创建好 Paint 对象，
 *      重写 onDraw()，把绘制代码写在 onDraw() 里面，
 * 就是自定义绘制最基本的实现。
 *
 * Created by 加荣 on 2017/8/9.
 */

public class CustomRoundRectView extends View {

    //提前创建好Paint对象 抗锯齿
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//    Paint paint = new Paint();

    public CustomRoundRectView(Context context) {
        super(context);
    }

    public CustomRoundRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRoundRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //重写onDraw(),把绘制代码写在onDraw()里面

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * paint类的几个常用的方法：
         */
        //默认值是填充模式FILL
//        paint.setStyle(Paint.Style.FILL);//设置绘制模式 实心 填充模式
        paint.setStyle(Paint.Style.STROKE);//设置绘制模式 空心 画线模式
//        paint.setStyle(Paint.Style.FILL_AND_STROKE);//既画线又填充

        paint.setColor(Color.RED);//设置颜色
        paint.setStrokeWidth(20);//设置线条宽度
        paint.setTextSize(12);//设置文字大小
//        paint.setAntiAlias(true);//设置抗锯齿开关


        /**
         * 绘制圆角矩形
         */
//        canvas.drawRoundRect(100, 100, 500, 300, 50, 50, paint);
        canvas.drawRoundRect(new RectF(100, 100, 500, 300), 20, 20, paint);
    }
}
