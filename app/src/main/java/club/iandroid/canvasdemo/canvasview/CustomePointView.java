package club.iandroid.canvasdemo.canvasview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 画点
 * Created by 加荣 on 2017/8/9.
 */

public class CustomePointView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CustomePointView(Context context) {
        super(context);
    }

    public CustomePointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomePointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //点的大小可以通过下面设置
        paint.setStrokeWidth(20);
        //点的形状可以通过setStrokeCap来设置
//        paint.setStrokeCap(Paint.Cap.ROUND);//设置圆形的点
        paint.setStrokeCap(Paint.Cap.SQUARE);//方形点
//        paint.setStrokeCap(Paint.Cap.BUTT);//方形点
        canvas.drawPoint(50, 50, paint);

        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(Color.BLUE);
        float[] points = {0, 0, 50, 50, 50, 100, 100, 50, 100, 100, 150, 50, 150, 100};
        // 绘制四个点：(50, 50) (50, 100) (100, 50) (100, 100)
        canvas.drawPoints(points, 2 /* 跳过两个数，即前两个 0 */,
                8 /* 一共绘制 8 个数（4 个点）*/, paint);

        /**
         * 随机散列
         */
        float[] randPoints = new float[20];
        for(int i=0;i<randPoints.length;i++){
            randPoints[i] = getrandom()+i*20;
        }
        paint.setColor(Color.YELLOW);
        canvas.drawPoints(randPoints, paint);
    }

    private float getrandom() {
        return (float) (Math.random() * 600);
    }
}
