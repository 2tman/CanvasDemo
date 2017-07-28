package club.iandroid.canvasdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import club.iandroid.sevenchartslib.BarChart;
import club.iandroid.sevenchartslib.ComboChart;
import club.iandroid.sevenchartslib.entity.LineEntity;
import club.iandroid.sevenchartslib.utils.LogUtils;
import club.iandroid.sevenchartslib.YRender;

/**
 * Created by 加荣 on 2017/7/28.
 */

public class BarChartFragment extends Fragment {

    private ComboChart mBarChart;
    private EditText et_index;
    private Button btn_go;
    private List<String> xLables = new ArrayList<>();
    private List<LineEntity> lineEntities = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bar_chart, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        et_index = (EditText) view.findViewById(R.id.et_index);
        btn_go = (Button) view.findViewById(R.id.btn_go);

        mBarChart = (ComboChart) view.findViewById(R.id.mBarChart);
        initData(mBarChart.getyRender());
        addEvent();
    }

    public void initData(YRender yRender) {
        yRender.setMinValue(-1000);
        yRender.setMaxValue(5000);
        yRender.generateValues();

        for (int i = 0; i < 10; i++) {
            String label = "label-" + i;
            xLables.add(label);
            lineEntities.add(new LineEntity((float) Math.random() * 800 * (i + 1), label));
        }
        mBarChart.getBaseBarChart().setxLableValues(xLables);
        mBarChart.getBaseBarChart().setLineValues(lineEntities);
        mBarChart.getBaseBarChart().setmColor(Color.parseColor("#19BAA9"));
    }


    @Override
    public void onResume() {
        super.onResume();
        if(isVisible()) {
            mBarChart.getBaseBarChart().animShow(2);
        }
    }

    private void addEvent() {
        mBarChart.setOnDragListener(new ComboChart.OnDragListener() {
            @Override
            public void onDragToLeft(int position) {
                LogUtils.log("滑到了最左边");
            }

            @Override
            public void onDragToRight(int position) {
                LogUtils.log("滑到了最右边");
            }
        });
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int targetIndex = Integer.parseInt(et_index.getText().toString());
                if (targetIndex < xLables.size()) {
                    String lable = xLables.get(targetIndex);
                    mBarChart.centerToXLabels(lable);
                }
            }
        });
    }
}
