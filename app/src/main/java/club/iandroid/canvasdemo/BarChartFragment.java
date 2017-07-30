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
import club.iandroid.sevenchartslib.entity.BarEntity;
import club.iandroid.sevenchartslib.entity.DataSet;
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
    private List<LineEntity> lineEntitiesExpedient = new ArrayList<>();
    private List<LineEntity> lineEntitiesIncome = new ArrayList<>();
    private List<BarEntity> barEntities = new ArrayList<>();

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
        initDataCombo(mBarChart.getyRender());
        addCombEvent();
    }

    public void initDataCombo(YRender yRender) {
        yRender.setMinValue(-1000);
        yRender.setMaxValue(5000);
        yRender.generateValues();

        for (int i = 0; i < 30; i++) {
            String label = "label-" + i;
            xLables.add(label);
            float randIndex = (float) i * 0.6f;

            LineEntity lineEntityIncome = new LineEntity((float) Math.random() * 600 * randIndex, label);
            LineEntity lineEntityExpedient = new LineEntity((float) Math.random() * 500 * randIndex, label);

            if (i > 6) {
                lineEntityIncome.setDrawDash(true);
                lineEntityExpedient.setDrawDash(true);
            }

            lineEntitiesIncome.add(lineEntityIncome);
            lineEntitiesExpedient.add(lineEntityExpedient);

            if (i > 4 && i < 7) {
                barEntities.add(new BarEntity((float) Math.random() * -600 * randIndex, label));
            } else {
                BarEntity barEntity = new BarEntity((float) Math.random() * 600 * randIndex, label);
                if (i > 6) {
                    barEntity.setDrawDash(true);
                }
                barEntities.add(barEntity);
            }
        }

        List<DataSet> dataSets = new ArrayList<>();

        DataSet dataSetIncome = new DataSet();
        dataSetIncome.setDataSet(lineEntitiesIncome);
        dataSetIncome.setmColor(Color.parseColor("#19BAA9"));

        DataSet dataSetExpedient = new DataSet();
        dataSetExpedient.setDataSet(lineEntitiesExpedient);
        dataSetExpedient.setmColor(Color.parseColor("#FF9C38"));

        dataSets.add(dataSetIncome);
        dataSets.add(dataSetExpedient);

        mBarChart.getBaseBarChart().setBarEntities(barEntities);
        mBarChart.getBaseBarChart().setDataSets(dataSets);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (isVisible()) {
            mBarChart.animShow();
        }
    }

    private void addCombEvent() {
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
