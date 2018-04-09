package com.hzyc.ccs.tools;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.*; 
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jfree.data.*; 
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.chart.*; 
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
/** 
* 用于演示饼图的生成
* @author Winter Lau 
*/ 
/**
 * @author Administrator
 *
 */
public class BingTu { 
    public static void main(String[] args) throws IOException{ 
    	String sql="SELECT sell_store,SUM(yingshou_money) FROM orders GROUP BY sell_store";
    	JDBCTools jt = new JDBCTools();
    	ArrayList<HashMap<String,String>> tList = jt.find(sql);
    	HashMap<String,Double> oneMap = new HashMap<String,Double>();
    	for(int i = 0;i<tList.size();i++){
    		oneMap.put(tList.get(i).get("sell_store"), Double.parseDouble(tList.get(i).get("SUM(yingshou_money)")));
    	}
        DefaultPieDataset data = getDataSet(oneMap); 
        
        JFreeChart freeChart = createChart(data);    
        BingTu.saveAsFile(freeChart, "D:\\fruit.jpg", 1000, 300);
    } 
    
 // 根据DefaultPieDataset创建JFreeChart对象      
    public static JFreeChart createChart(DefaultPieDataset data) {    
    	JFreeChart chart = ChartFactory.createPieChart3D("总店与分店数据对比图",  // 图表标题
    	        data, 
    	        true, // 是否显示图例
    	        false, 
    	        false 
    	        ); 
    	        Font titleFont = new Font("黑体", Font.BOLD, 20);  
    	        TextTitle textTitle = chart.getTitle();  
    	        textTitle.setFont(titleFont);// 为标题设置上字体  
    	          
    	        Font plotFont = new Font("宋体", Font.PLAIN, 16);  
    	        PiePlot plot = (PiePlot) chart.getPlot();  
    	        plot.setLabelFont(plotFont); // 为饼图元素设置上字体  
    	          
    	        Font LegendFont = new Font("楷体", Font.PLAIN, 18);  
    	        LegendTitle legend = chart.getLegend(0);  
    	        legend.setItemFont(LegendFont);// 为图例说明设置字体  
    	        // 写图表对象到文件，参照柱状图生成源码
    	        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} {1} {2}",NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));// 显示百分比  
    	        plot.setSectionOutlinesVisible(false);
    	        return chart;      
    }      
    /** 
    * 获取一个演示用的简单数据集对象
    * @return 
    */ 
    public static DefaultPieDataset getDataSet(HashMap<String,Double> oneMap) { 
        DefaultPieDataset dataset = new DefaultPieDataset(); 
        for(Map.Entry<String,Double> entry :oneMap.entrySet()){
			
			dataset.setValue(entry.getKey(), entry.getValue());
		}
        return dataset; 
    } 
    
    /**
     * @param chart
     * @param outputPath
     * @param weight
     * @param height
     *保存文件
     */
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
 
    		       //ChartUtilities.writeChartAsJPEG(outputPath,1.0f,chart,400,300,null); 
    		        ChartUtilities.writeChartAsJPEG(out, chart,weight, height);
 
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
    
}