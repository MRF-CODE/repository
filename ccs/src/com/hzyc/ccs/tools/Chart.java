package com.hzyc.ccs.tools;

import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class Chart {
	public void creative(double[] value,String[] rowKey,String[] columnKey){

	    StandardChartTheme mChartTheme = new StandardChartTheme("CN");
	    mChartTheme.setLargeFont(new Font("黑体", Font.BOLD, 20));
	    mChartTheme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 15));
	    mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));
	    ChartFactory.setChartTheme(mChartTheme);		
	    CategoryDataset mDataset = SetData(value,rowKey,columnKey);
	    JFreeChart mChart = ChartFactory.createLineChart(
	        "折线图",//图名字
	        "月份",//横坐标
	        "数量",//纵坐标
	        mDataset,//数据集
	        PlotOrientation.VERTICAL,
	        true, // 显示图例
	        true, // 采用标准生成器 
	        false);// 是否生成超链接
	    
	    CategoryPlot mPlot = (CategoryPlot)mChart.getPlot();
	    mPlot.setBackgroundPaint(Color.LIGHT_GRAY);
	    mPlot.setRangeGridlinePaint(Color.BLUE);//背景底部横虚线
	    mPlot.setOutlinePaint(Color.RED);//边界线
	    
	    ChartFrame mChartFrame = new ChartFrame("折线图", mChart);
	    mChartFrame.pack();
	    mChartFrame.setVisible(true);

	}
	public static CategoryDataset SetData(double[] value,String[] rowKey,String[] columnKey){
		 DefaultCategoryDataset mDataset = new DefaultCategoryDataset();
		 for(int i = 0;i<value.length;i++){
			 mDataset.setValue(value[i],rowKey[i],columnKey[i]);
		 }
		 return mDataset;
	}
	 public static void main(String[] args) {
		 Chart c = new Chart();
		 double[] value={1,2,3,4,5,6,7,8,9,10,11,12};
		 String[] rowKey={"First","First","First","First","First","First","First","First","First","First","First","First","First","First"};
		 String[] columnKey={"1","2","3","4","5","6","7","8","9","10","11","12"};
		 c.creative(value, rowKey, columnKey);
	}
}
