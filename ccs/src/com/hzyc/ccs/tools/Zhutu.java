package com.hzyc.ccs.tools;
import java.awt.Color;
import java.awt.Font;
import java.io.*; 
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.data.*; 
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.*; 
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.*; 
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;

import com.hzyc.ccs.model.Tu;
/** 
* 该类用于演示最简单的柱状图生成
* @author Winter Lau 
*/ 
public class Zhutu { 
    public static void main(String[] args) throws IOException{ 
       // CategoryDataset dataset = getDataSet(); 
    	String sql="SELECT SUBSTR(dd_time,1,10),SUM(yingshou_money) FROM orders WHERE SUBSTR(dd_time,1,10) <='2017-09-22' AND SUBSTR(dd_time,1,12) >= '2017-09-14' GROUP BY SUBSTR(dd_time,1,10)";
    	JDBCTools jt = new JDBCTools();
    	List<Tu> uList = new ArrayList<Tu>();
    	ArrayList<HashMap<String,String>> tList = jt.find(sql);
    	HashMap<String,Double> oneMap = new HashMap<String,Double>();
    	for(int i = 0;i<tList.size();i++){
    		Tu tu = new Tu();
    		tu.setDate(tList.get(i).get("SUBSTR(dd_time,1,10)"));
    		tu.setMoney(Double.parseDouble(tList.get(i).get("SUM(yingshou_money)")));
    		uList.add(tu);
    	}
    	
    	CategoryDataset data = getDataSet(uList); 
    	for(Map.Entry<String,Double> entry :oneMap.entrySet()){
			
			System.out.println(entry.getValue()+entry.getKey());
		}
        JFreeChart chart = Zhutu.createChart(data);                    
        BingTu.saveAsFile(chart, "D:\\fruit.jpg", 1000, 300);
    } 
 // 根据DefaultPieDataset创建JFreeChart对象      
    public static JFreeChart createChart(CategoryDataset data) {    
    	 JFreeChart chart = ChartFactory.createBarChart3D( 
                 "", // 图表标题
                 "日期", // 目录轴的显示标签
                 "收入", // 数值轴的显示标签
                 (CategoryDataset) data, // 数据集
                  PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                  true,  // 是否显示图例(对于简单的柱状图必须是 false)
                  false, // 是否生成工具
                  false  // 是否生成 URL 链接
                  ); 
    	 	
				//设置柱状图 主标题的文字
				chart.setTitle(new TextTitle("按天统计柱形图",new Font("宋 体",Font.BOLD+Font.ITALIC,20)));
				
				//设置柱状图最下方说明的文字
				chart.getLegend().setItemFont(new Font("微软雅黑",Font.BOLD,12));
				
				CategoryPlot plot = (CategoryPlot) chart.getPlot();
				//设置柱形图的颜色
				BarRenderer3D customBarRenderer = (BarRenderer3D) plot.getRenderer();
	    	 	customBarRenderer.setSeriesPaint(0, Color.decode("#24F4DB"));
				CategoryAxis axis = plot.getDomainAxis();
				//设置X轴坐标上标题的文字
				axis.setLabelFont(new Font("微软雅黑",Font.BOLD,22));
				//设置X轴坐标上的文字，
				axis.setTickLabelFont(new Font("微软雅黑",Font.BOLD,12));
				
				ValueAxis valueAxis = plot.getRangeAxis();
				//设置Y轴坐标上标题的文字
				valueAxis.setLabelFont(new Font("微软雅黑",Font.BOLD,12));
				//设置Y轴坐标上的文字
				valueAxis.setTickLabelFont(new Font("sans-serif",Font.BOLD,12));
        return chart;      
    }      
    /** 
    * 获取一个演示用的简单数据集对象
    * @return 
    */ 
    public static CategoryDataset getDataSet(List<Tu> uList) { 
        DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
        /*for(Map.Entry<String,Double> entry :oneMap.entrySet()){
			
			dataset.addValue(entry.getValue(), "销售额",entry.getKey());
		}*/
        for(int i = 0;i<uList.size();i++){
        	dataset.addValue(uList.get(i).getMoney(), "", uList.get(i).getDate());
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
