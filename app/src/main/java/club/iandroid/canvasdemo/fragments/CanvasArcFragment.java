package club.iandroid.canvasdemo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import club.iandroid.canvasdemo.R;

/**
 * 绘制圆弧
 * drawArc() 是使用一个椭圆来描述弧形的。
 * left, top, right, bottom 描述的是这个弧形所在的椭圆；
 * startAngle 是弧形的起始角度（x 轴的正向，即正右的方向，是 0 度的位置；顺时针为正角度，逆时针为负角度），
 * sweepAngle 是弧形划过的角度；
 * useCenter 表示是否连接到圆心，如果不连接到圆心，就是弧形，如果连接到圆心，就是扇形。
 */
public class CanvasArcFragment extends Fragment {


    public CanvasArcFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_canvas_arc, container, false);
    }

}
