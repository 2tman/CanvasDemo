package club.iandroid.canvasdemo.paints;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 1、基本颜色
     canvas.drawColor/RGB()/ARGB()--颜色参数

     canvas.drawBitmap()--bitmap参数

     canvas 图形和文字绘制--paint参数
 * Created by 加荣 on 2017/8/18.
 */

public class PaintColor extends View {
    Paint paint = new Paint();

    public PaintColor(Context context) {
        super(context);
    }

    public PaintColor(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PaintColor(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
        canvas.drawRect(30, 30, 230, 180, paint);

        paint.setColor(Color.parseColor("#ff9800"));
        canvas.drawLine(300, 30, 450, 180, paint);

        paint.setColor(Color.parseColor("#391e63"));
        canvas.drawText("hencoder", 500, 130, paint);
    }
}
