package club.iandroid.canvasdemo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import club.iandroid.canvasdemo.R;

/**
 * 贝塞尔曲线演示
 * 贝塞尔曲线：贝塞尔曲线是几何上的一种曲线。
 * 它通过起点、控制点和终点来描述一条曲线，主要用于计算机图形学。
 * 概念总是说着容易听着难，总之使用它可以绘制很多圆润又好看的图形，
 * 但要把它熟练掌握、灵活使用却是不容易的。
 * 不过还好的是，一般情况下，贝塞尔曲线并没有什么用处，只在少数场景下绘制一些特殊图形的时候才会用到，
 * 所以如果你还没掌握自定义绘制，可以先把贝塞尔曲线放一放，稍后再学也完全没问题。
 * 至于怎么学，贝塞尔曲线的知识网上一搜一大把，我这里就不讲了。
 *
 * quadTo(float x1, float y1, float x2, float y2)
 * / rQuadTo(float dx1, float dy1, float dx2, float dy2) 画二次贝塞尔曲线
 *
 * cubicTo(float x1, float y1, float x2, float y2, float x3, float y3)
 * / rCubicTo(float x1, float y1, float x2, float y2, float x3, float y3) 画三次贝塞尔曲线
 */
public class CanvasQuadFragment extends Fragment {


    public CanvasQuadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_canvas_quad, container, false);
    }

}
