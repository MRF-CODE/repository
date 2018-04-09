package com.hzyc.ccs.tools;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.hzyc.ccs.model.Vip;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * demo
 * <p>
 * ClassName: TestExcel
 * </p>
 * <p>
 * Copyright: (c)2016 jastar,All rights reserved.
 * </p>
 * 
 * @author jastar
 * @date 2016-5-13
 */
public class Daoru {

	   
    private String filePath;  
    private List list = new ArrayList();  
    private List<Vip> vlist = new ArrayList<Vip>();
    InputStream stream = null;
      
    public Daoru(InputStream filePath){  
        this.stream = filePath ;  
    }  
      
    public List<Vip> readExcel() throws IOException, BiffException{  
        //创建输入流  
       // InputStream stream = this.stream;  
        //获取Excel文件对象  
        Workbook  rwb = Workbook.getWorkbook(stream); 
        //获取文件的指定工作表 默认的第一个  
        Sheet sheet = rwb.getSheet(0);    
        //行数(表头的目录不需要，从1开始)  
        for(int i=1; i<sheet.getRows(); i++){  
             //创建一个数组 用来存储每一列的值  
            String[] str = new String[sheet.getColumns()];  
            Cell cell = null;  
            //创建对象
            Vip v = new Vip();
            
            //列数  
            for(int j=0; j<sheet.getColumns(); j++){  
              //获取第i行，第j列的值  
             
            	  cell = sheet.getCell(j,i); 
            	  if(j == 1){
            		  v.setHyName(cell.getContents());
            	  }
            	  if(j == 2){
            		  v.setHyJf(cell.getContents());
            	  }
            	  if(j == 4){
            		  v.setHyRemainMoney(cell.getContents());
            	  }
            	  if(j == 5){
            		  v.setHyKindCode("1");
            	  }
            	  if(j == 6){
            		  v.setHyTel(cell.getContents());
            	  }
            	  if(j == 8){
            		  v.setHyDate(cell.getContents());
            	  }
            	  if(j == 13){
            		  v.setHyAddress(cell.getContents());
            	  }
            	  if(j == 14){
            		  v.setHyBz(cell.getContents());
            	  }
                  str[j] = cell.getContents();  
             // System.out.println( str[j]);
            }  
          //把刚获取的列存入list  
            if(v.getHyRemainMoney()!=null){
            	v.setHyTotal(v.getHyRemainMoney());
            }
          vlist.add(v);
        // System.out.println( v.getHyName()+v.getHyCode());
        }  
        return vlist;
    }  
    public void outData(){  
         for(int i=0;i<list.size();i++){  
             String[] str = (String[])list.get(i);  
             for(int j=0;j<str.length;j++){  
            	 
              System.out.print(str[j]+'\t');  
             }  
             System.out.println();  
         }  
    }  
    public void print(){
    	for(Vip v : vlist){
    		System.out.print(v.getHyName()+v.getHyJf()+"-"+v.getHyRemainMoney());
    		System.out.print(v.getHyKindCode()+v.getHyTel()+"-"+v.getHyDate());
    		System.out.print(v.getHyAddress()+v.getHyBz());
    		System.out.println();
    	}
    }
      
    public static void main(String args[]) throws BiffException, IOException{  
    	//Daoru excel = new Daoru("D:/Users/Administrator/Downloads/会员导入模板.xls");  
      // / excel.readExcel();  
       // excel.outData();  
       // excel.print();
    }  
}
