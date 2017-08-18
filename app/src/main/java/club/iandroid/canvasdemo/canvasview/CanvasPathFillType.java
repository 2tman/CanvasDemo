package club.iandroid.canvasdemo.canvasview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 加荣 on 2017/8/10.
 */

public class CanvasPathFillType extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path path = new Path();

    {
        /**
         * 奇偶原则，
         * 对于平面中的任意一点，向任意方向射出一条射线，这条射线和图形相交的次数（相交才算，相切不算哦）
         * 如果是奇数，则这个点被认为在图形内部，是要被涂色的区域；
         * 如果是偶数，则这个点被认为在图形外部，是不被涂色的区域。
         */
        path.setFillType(Path.FillType.EVEN_ODD);

        paint.setColor(Color.GREEN);
        path.addCircle(200, 200, 100, Path.Direction.CW);

        paint.setColor(Color.GRAY);
        path.addCircle(200, 200, 60, Path.Direction.CW);
    }

    public CanvasPathFillType(Context context) {
        super(context);
    }

    public CanvasPathFillType(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasPathFillType(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }
}
