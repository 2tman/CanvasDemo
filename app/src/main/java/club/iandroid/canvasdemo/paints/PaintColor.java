package club.iandroid.canvasdemo.paints;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.ComposeShader;
import android.graphics.LightingColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import club.iandroid.canvasdemo.R;

/**
 * 1、基本颜色
 * canvas.drawColor/RGB()/ARGB()--颜色参数
 * <p>
 * canvas.drawBitmap()--bitmap参数
 * <p>
 * canvas 图形和文字绘制--paint参数
 * Created by 加荣 on 2017/8/18.
 */

public class PaintColor extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public PaintColor(Context context) {
        super(context);
        initView();
    }

    public PaintColor(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PaintColor(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        setLayerType(View.LAYER_TYPE_SOFTWARE, paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 设置颜色一
         */
        /*paint.setColor(Color.RED);
        canvas.drawRect(30, 30, 230, 180, paint);

        paint.setColor(Color.parseColor("#ff9800"));
        canvas.drawLine(300, 30, 450, 180, paint);

        paint.setColor(Color.parseColor("#391e63"));
        canvas.drawText("hencoder", 500, 130, paint);

        paint.setARGB(100, 255, 0, 0);
        canvas.drawRect(0, 0, 200, 200, paint);*/

        /**
         * 设置颜色二
         *
         *  LinearGradient; 线形渐变
         RadialGradient; 辐射渐变
         SweepGradient; 扫描渐变
         BitmapShader;
         ComposeShader;
         */

        /**
         * 线形渐变
         * float x0, float y0, float x1, float y1, 渐变的两个端点的位置
         * int color0, int color1,端点 的颜色
         TileMode tile 端点范围之外的着色规则，类型是TileMode，一共有3个值：CLAMP,MIRROR 和REPEAT

         */
//        Shader shader = new LinearGradient(100, 100, 500, 500,
//                Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
//        paint.setShader(shader);
//        canvas.drawCircle(300, 300, 200, paint);

        /**
         * 辐射渐变
         *  centerX centerY：辐射中心的坐标
         radius：辐射半径
         centerColor：辐射中心的颜色
         edgeColor：辐射边缘的颜色
         tileMode：辐射范围之外的着色模式
         */
//        Shader shaderRadia = new RadialGradient(300, 300, 200,
//                Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"), Shader.TileMode.REPEAT);
//        paint.setShader(shaderRadia);
//        canvas.drawCircle(300, 300, 200, paint);

        /**
         * 扫描渐变
         *  cx cy ：扫描的中心
         color0：扫描的起始颜色
         color1：扫描的终止颜色
         */
//        Shader shaderSweep = new SweepGradient(300, 300, Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"));
//        paint.setShader(shaderSweep);
//        canvas.drawCircle(300, 300, 200, paint);

        /**
         * BitmapShader
         * bitmap：用来做模板的 Bitmap 对象
         tileX：横向的 TileMode
         tileY：纵向的 TileMode。
         */
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test);
        Shader shaderBitmap = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        paint.setShader(shaderBitmap);
//        canvas.drawCircle(300, 300, 200, paint);

        /**
         * 混合着色器
         * ComposeShader
         *
         * ProterDuff.Mode
         * 是用来指定两个图像共同绘制时的颜色策略的，它是一个enum，不同的Mode可以指定不同的策略
         *
         */
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Shader shaderBitmap1 = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        Shader shader = new ComposeShader(shaderBitmap, shaderBitmap1, PorterDuff.Mode.SRC_OVER);
        paint.setShader(shader);


        /**
         * ColorFilter
         * 颜色过滤
         * 三个子类
         *  LightingColorFilter;//用来模拟简单的光照效果
         PorterDuffColorFilter;//
         ColorMatrixColorFilter;
         */
        ColorFilter lightingColorFilter = new LightingColorFilter(0x00ffff, 0x300000);
        paint.setColorFilter(lightingColorFilter);


        ColorFilter proterColorFilter = new PorterDuffColorFilter(Color.parseColor("#eeeeee"), PorterDuff.Mode.DST_ATOP);
        paint.setColorFilter(proterColorFilter);

        canvas.drawCircle(300, 300, 200, paint);
    }
}
