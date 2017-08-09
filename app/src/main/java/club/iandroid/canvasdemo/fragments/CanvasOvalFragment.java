package club.iandroid.canvasdemo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import club.iandroid.canvasdemo.R;

/**
 * 画椭圆
 */
public class CanvasOvalFragment extends Fragment {


    public CanvasOvalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_canvas_oval, container, false);
    }

}
