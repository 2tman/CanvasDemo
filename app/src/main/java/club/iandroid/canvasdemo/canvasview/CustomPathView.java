package club.iandroid.canvasdemo.canvasview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * drawPath
 * path有两类方法，一类是直接描述路径的，另一类是辅助的设置或计算
 * Created by 加荣 on 2017/8/9.
 */

public class CustomPathView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();//初始化Path对象

    {
        //使用path对图形进行描述
//        path.addArc(200, 200, 400, 400, -225, 225);
       /* path.addArc(new RectF(200, 200, 400, 400), -225, 225);
        path.arcTo(new RectF(400, 200, 600, 400), -180, 225, false);
        path.lineTo(400, 542);*/

        /**
         * 第一组：addXxx()  -- 添加子图形
         *
         */
        //添加圆
        //x，y, radius是基本信息，最有一个参数dir是画圆的路径的防线
//        path.addCircle(float x, float y, float radius, Direction dir);
       /* path.addCircle(200, 200, 100, Path.Direction.CW);

        //添加椭圆
        path.addOval(new RectF(100, 400, 400, 600), Path.Direction.CW);

        //添加圆角矩形
        path.addRoundRect(new RectF(100, 500, 400, 700), 20, 20, Path.Direction.CW);*/

       paint.setStyle(Paint.Style.STROKE);
        /**
         * 第二组：xxxTo() -- 画线（直线或曲线）
         */
//        path.lineTo(100, 100);//由当前位置向(100, 100)画一条直线
//        path.rLineTo(100, 0);//由当前位置向正向100的位置画一条直线
////        path.lineTo(200, 100);
//
//        path.moveTo(200, 100);//移动
//        path.lineTo(200, 400);


        /**
         * 画弧形
         * arcTo(RectF oval, float startAngle, float sweepAngle, boolean forceMoveTo)
         * / arcTo(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean forceMoveTo)
         * / arcTo(RectF oval, float startAngle, float sweepAngle) 画弧形
         */
//        path.arcTo(new RectF(200, 200, 300, 300), -90, 90, true); // 强制移动到弧形起点（无痕迹）
//        path.arcTo(new RectF(300, 300, 400, 400), -90, 90, false); // 强制移动到弧形起点（有痕迹）

        /**
         * addArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle)
         * / addArc(RectF oval, float startAngle, float sweepAngle)

         又是一个弧形的方法。一个叫 arcTo ，一个叫 addArc()，都是弧形，区别在哪里？
         其实很简单： addArc() 只是一个直接使用了 forceMoveTo = true 的简化版 arcTo() 。
         */
//        path.addArc(new RectF(100, 100, 300, 300), -90, 90);


        /**
         * 贝塞尔曲线
         */
        /*path.quadTo();
        path.rQuadTo();*/

        //子图形未封闭
//        path.moveTo(100, 100);
//        path.lineTo(200, 100);
//        path.lineTo(150, 150);
//        path.close(); // 使用 close() 封闭子图形。等价于 path.lineTo(100, 100)
        //close()和lineTo(起点坐标)是完全等价的

        /**
         * 另外，不是所有的子图形都需要使用 close() 来封闭。当需要填充图形时（即 Paint.Style 为 FILL 或 FILL_AND_STROKE），Path 会自动封闭子图形。
         */
        paint.setStyle(Paint.Style.FILL);
        path.moveTo(100, 100);
        path.lineTo(200, 100);
        path.lineTo(150, 150);
        // 这里只绘制了两条边，但由于 Style 是 FILL ，所以绘制时会自动封口

        /**
         * Path 方法第二类：辅助的设置或计算

         这类方法的使用场景比较少，我在这里就不多讲了，只讲其中一个方法： setFillType(FillType fillType)。

         Path.setFillType(Path.FillType ft) 设置填充方式

         前面在说 dir 参数的时候提到， Path.setFillType(fillType) 是用来设置图形自相交时的填充算法的：
         */
    }

    public CustomPathView(Context context) {
        super(context);
    }

    public CustomPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);//绘制出path描述的图形（心形）
    }
}
