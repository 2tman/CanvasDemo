package club.iandroid.canvasdemo.canvasview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 绘制圆弧
 * Created by 加荣 on 2017/8/9.
 */

public class CustomArcView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CustomArcView(Context context) {
        super(context);
    }

    public CustomArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL);//填充模式
//        canvas.drawArc(200, 100, 800, 500, -110, 100, true, paint);
        RectF rectFOval = new RectF(200, 100, 500, 500);
        canvas.drawArc(rectFOval, -110, 110, true, paint);//绘制扇形

        canvas.drawArc(rectFOval, 20, 140, false, paint);//绘制弧形

        paint.setStyle(Paint.Style.STROKE);//画线模式
        canvas.drawArc(rectFOval, 180, 60, false, paint);//绘制不封口的弧形
    }
}
