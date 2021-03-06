package com.hzyc.ccs.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;

/**
 * Excel导入导出
 * 
 * @author 林计钦
 * @version 1.0 Feb 7, 2014 4:14:51 PM
 */
public class ExcelTest {

    /**
     * 导入(导入到内存)
     */
    @Test
    public void importExcel() {
        Workbook book = null;
        try {
            book = Workbook.getWorkbook(new File("D:/test/测试.xls"));
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            int rows=sheet.getRows();
            int columns=sheet.getColumns();
            // 遍历每行每列的单元格
            for(int i=0;i<rows;i++){
                for(int j=0;j<columns;j++){
                    Cell cell = sheet.getCell(j, i);
                    String result = cell.getContents();
                    if(j==0){
                        System.out.print("姓名："+result+" ");
                    }
                    if(j==1){
                        System.out.print("年龄："+result+" ");
                    }
                    if((j+1)%2==0){ 
                        System.out.println();
                    }
                }
            }
            System.out.println("========");
            // 得到第一列第一行的单元格
            Cell cell1 = sheet.getCell(0, 0);
            String result = cell1.getContents();
            System.out.println(result);
            System.out.println("========");
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(book!=null){
                book.close();
            }
        }
    }

    /**
     * 导出(导出到磁盘)
     */
    @Test
    public void exportExcel() {
        WritableWorkbook book = null;
        try {
            // 打开文件
            book = Workbook.createWorkbook(new File("D:/test/测试.xls"));
            // 生成名为"学生"的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("学生", 0);
            // 指定单元格位置是第一列第一行(0, 0)以及单元格内容为张三
            Label label = new Label(0, 0, "张三");
            // 将定义好的单元格添加到工作表中
            sheet.addCell(label);
            // 保存数字的单元格必须使用Number的完整包路径
            jxl.write.Number number = new jxl.write.Number(1, 0, 30);
            sheet.addCell(number);
            // 写入数据并关闭文件
            book.write();
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(book!=null){
                try {
                    book.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            }
        }
    }
    
    /**
     * 对象数据写入到Excel
     */
    @Test
    public void writeExcel() {
        WritableWorkbook book = null;
        try {
            // 打开文件
            book = Workbook.createWorkbook(new File("D:/test/stu.xls"));
            // 生成名为"学生"的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("学生", 0);
            
            List<Student> stuList=queryStudentList();
            if(stuList!=null && !stuList.isEmpty()){
                for(int i=0; i<stuList.size(); i++){
                    sheet.addCell(new Label(0, i, stuList.get(i).getName()));
                    sheet.addCell(new Number(1, i, stuList.get(i).getAge()));
                }
            }
            
            // 写入数据并关闭文件
            book.write();
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(book!=null){
                try {
                    book.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            }
        }
    
    }
    
    /**
     * 读取Excel数据到内存
     */
    @Test
    public void readExcel() {
        Workbook book = null;
        try {
            // 打开文件
            book = Workbook.getWorkbook(new File("D:/test/stu.xls"));
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            int rows=sheet.getRows();
            int columns=sheet.getColumns();
            List<Student> stuList=new ArrayList<Student>();
            // 遍历每行每列的单元格
            for(int i=0;i<rows;i++){
                Student stu = new Student();
                for(int j=0;j<columns;j++){
                    Cell cell = sheet.getCell(j, i);
                    String result = cell.getContents();
                    if(j==0){
                        stu.setName(result);
                    }
                    if(j==1){
                        stu.setAge(NumberUtils.toInt(result));
                    }
                    if((j+1)%2==0){
                        stuList.add(stu);
                        stu=null;
                    }
                }
            }
            
            //遍历数据
            for(Student stu : stuList){
                System.out.println(String.format("姓名：%s, 年龄：%s", 
                        stu.getName(), stu.getAge()));
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(book!=null){
                try {
                    book.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            }
        }
    
    }
    
    /**
     * 图片写入Excel，只支持png图片
     */
    @Test
    public void writeImg() {
        WritableWorkbook wwb = null;
        try {
            wwb = Workbook.createWorkbook(new File("D:/test/image.xls"));
            WritableSheet ws = wwb.createSheet("图片", 0);
            File file = new File("D:\\test\\png.png");
            //前两位是起始格，后两位是图片占多少个格，并非是位置
            WritableImage image = new WritableImage(1, 4, 6, 18, file);
            ws.addImage(image);
            wwb.write();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(wwb!=null){
                try {
                    wwb.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }  
            }
        }
    }
    
    private List<Student> queryStudentList(){
        List<Student> stuList=new ArrayList<Student>();
        stuList.add(new Student("zhangsan", 20));
        stuList.add(new Student("lisi", 25));
        stuList.add(new Student("wangwu", 30));
        return stuList;
    }
    
    public class Student {
        private String name;
        private int age;

        public Student() {
        }

        public Student(String name, int age) {
            super();
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}