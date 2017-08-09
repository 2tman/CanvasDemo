package club.iandroid.canvasdemo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import club.iandroid.canvasdemo.R;

/**
 * 圆角矩形
 */
public class CanvasRoundRectFragment extends Fragment {


    public CanvasRoundRectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_canvas_round_rect, container, false);
    }

}
