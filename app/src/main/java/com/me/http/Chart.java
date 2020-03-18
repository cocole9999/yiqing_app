package com.me.http;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Chart extends Fragment {

//    private TextView textView ;
    private BarChart chart;
    private XAxis xAxis;
    private ViewModel viewModel;
    private List<City> list = new ArrayList<City>();
    private String [] str = new String[7];
    public Chart() {
        // Required empty public constructor
    }

    public void getData(){
        ArrayList<BarEntry> values = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            BarEntry barEntry = new BarEntry(i,Float.valueOf(list.get(i).getConfirm()));
            values.add(barEntry);
        }

        BarDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "确诊人数");
            set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
            set1.setDrawValues(false);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            chart.setData(data);

            chart.setFitBars(true);
        }
        //绘制图表
        chart.invalidate();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_chart, container, false);
//        textView = inflate.findViewById(R.id.textView2);
        chart = inflate.findViewById(R.id.chart);
        viewModel = ViewModelProviders.of(requireActivity()).get(ViewModel.class);
        list = viewModel.getList();
        for (int i = 0; i < 7; i++) {
            str[i] = list.get(i).getName();
        }
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        textView.setText(viewModel.getList().get(0).getName());
        /***图表设置***/
        //背景颜色
        chart.getDescription().setEnabled(false);

        //设置最大值条目，超出之后不会有值
        chart.setMaxVisibleValueCount(60);

        //分别在x轴和y轴上进行缩放
        chart.setPinchZoom(true);
        //设置剩余统计图的阴影
        chart.setDrawBarShadow(false);
        //设置网格布局
        chart.setDrawGridBackground(true);
        //通过自定义一个x轴标签来实现2,015 有分割符符bug
        ValueFormatter custom = new MyValueFormatter(" 1",str);
        //获取x轴线
        XAxis xAxis = chart.getXAxis();

        //设置x轴的显示位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置网格布局
        xAxis.setDrawGridLines(true);
        //图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
        xAxis.setAvoidFirstLastClipping(false);
        //绘制标签  指x轴上的对应数值 默认true
        xAxis.setDrawLabels(true);
        xAxis.setValueFormatter(custom);
        //缩放后x 轴数据重叠问题
        xAxis.setGranularityEnabled(true);
        //获取右边y标签
        YAxis axisRight = chart.getAxisRight();
        axisRight.setStartAtZero(true);
        //获取左边y轴的标签
        YAxis axisLeft = chart.getAxisLeft();
        //设置Y轴数值 从零开始
        axisLeft.setStartAtZero(true);

        chart.getAxisLeft().setDrawGridLines(false);
        //设置动画时间
        chart.animateXY(600,600);

        chart.getLegend().setEnabled(true);

        getData();
        //设置柱形统计图上的值

        chart.getData().setValueTextSize(10);
        for (IDataSet set : chart.getData().getDataSets()){
            set.setDrawValues(!set.isDrawValuesEnabled());
        }
    }
}
