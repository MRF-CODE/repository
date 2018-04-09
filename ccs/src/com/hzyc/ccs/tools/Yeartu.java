package com.hzyc.ccs.tools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.File;   
import java.io.FileNotFoundException;   
import java.io.FileOutputStream;   
import java.io.IOException;   
import java.util.Date;

import org.jfree.chart.ChartFactory;   
import org.jfree.chart.ChartUtilities;   
import org.jfree.chart.JFreeChart;   
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;   
import org.jfree.chart.plot.XYPlot;   
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;   
import org.jfree.data.xy.XYSeries;   
import org.jfree.data.xy.XYSeriesCollection;   

import com.hzyc.ccs.dao.CountDao;
 
 
 
public class Yeartu {     
 
 
 
    /**     
 
     * 创建JFreeChart LineXY Chart（折线图）     
 
     */     
 
        public static void main(String[] args) {      
 
        //步骤1：创建XYDataset对象（准备数据）      
        	
        	CountDao cd = new CountDao();
        	String[] value = cd.countByYear("admin");
        	Double[] ds=new Double[value.length];
        	for(int i=0;i<value.length;i++){
        	ds[i]=Double.valueOf(value[i]);
        	}
        XYDataset dataset = createXYDataset(ds);      
 
        //步骤2：根据Dataset 生成JFreeChart对象，以及做相应的设置      
 
        JFreeChart freeChart = createChart(dataset);      
 
        //步骤3：将JFreeChart对象输出到文件，Servlet输出流等      
 
        saveAsFile(freeChart, "F:\\jfreechart\\line.png", 900, 600);      
 
        }     
 
 
 
    // 保存为文件      
 
    public static void saveAsFile(JFreeChart chart, String outputPath,      
 
            int weight, int height) {      
 
        FileOutputStream out = null;      
 
        try {      
 
            File outFile = new File(outputPath);      
 
            if (!outFile.getParentFile().exists()) {      
 
                outFile.getParentFile().mkdirs();      
 
            }      
 
            out = new FileOutputStream(outputPath);      
 
            // 保存为PNG      
 
            ChartUtilities.writeChartAsPNG(out, chart, weight, height);      
 
            // 保存为JPEG      
 
            // ChartUtilities.writeChartAsJPEG(out, chart, weight, height);      
 
            out.flush();      
 
        } catch (FileNotFoundException e) {      
 
            e.printStackTrace();      
 
        } catch (IOException e) {      
 
            e.printStackTrace();      
 
        } finally {      
 
            if (out != null) {      
 
                try {      
 
                    out.close();      
                } catch (IOException e) {      
                    // do nothing      
                }      
            }      
        }      
    }      
    
    // 根据XYDataset创建JFreeChart对象      
    public static JFreeChart createChart(XYDataset dataset) {    
    	StandardChartTheme mChartTheme = new StandardChartTheme("CN");
        mChartTheme.setLargeFont(new Font("黑体", Font.BOLD, 20));
        mChartTheme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 15));
        mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));
        ChartFactory.setChartTheme(mChartTheme);	
        // 创建JFreeChart对象：ChartFactory.createXYLineChart      
    	JFreeChart jfreechart = ChartFactory.createXYLineChart(
                "折线图",//图名字
                "月份",//横坐标
                "收益",//纵坐标
                dataset,//数据集
                PlotOrientation.VERTICAL,
                true, // 显示图例
                true, // 采用标准生成器 
                false);// 是否生成超链接   
        
 
        // 使用CategoryPlot设置各种参数。以下设置可以省略。      
        XYPlot plot = (XYPlot)jfreechart.getPlot();      
        // 背景色 透明度      
        plot.setBackgroundAlpha(0.5f);      
        // 前景色 透明度      
        plot.setForegroundAlpha(0.5f);      
        // 其它设置可以参考XYPlot类      
        plot.setBackgroundPaint(Color.gray);
        XYLineAndShapeRenderer lasp = (XYLineAndShapeRenderer) plot.getRenderer();
        lasp.setSeriesStroke(0, new BasicStroke(5F));
        return jfreechart;      
    }      
 
    /**     
     * 创建XYDataset对象     
     *      
     */     
 
    public static XYDataset createXYDataset(Double[] value) {      
        XYSeries xyseries1 = new XYSeries("One");      
        Date date  = new Date();
		int year = date.getYear() + 1900 ;
		int year1 = year-2;
		int year2 = year-1;
		int year4 = year+1;
		int year5 = year+2;
        xyseries1.add(year1, value[0]);      
        xyseries1.add(year2, value[1]);      
        xyseries1.add(year, value[2]);  
        xyseries1.add(year4, value[3]);      
        xyseries1.add(year5, value[4]);      
 
 
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();      
 
        xySeriesCollection.addSeries(xyseries1);      
 
        return xySeriesCollection;      
    }      
}