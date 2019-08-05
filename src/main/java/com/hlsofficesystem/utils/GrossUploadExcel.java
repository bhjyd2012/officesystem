package com.hlsofficesystem.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.javassist.expr.NewArray;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hlsofficesystem.bean.Gross;



public class GrossUploadExcel {
	
 
	/**   
	 * @Title: grossUploadExcel   
	 * @Description: TODO(把Excel表格数据存放进list集合中，进行批量插入)   
	 * @param: @param uploadFile 上传的File文件
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: List<Gross>      
	 * @throws   
	 */  
	public static List<Gross> grossUploadExcel(MultipartFile uploadFile)throws Exception{
		List<Gross> grosses = new ArrayList<Gross>();
		/*
		 * File file = new File(pathUrl); InputStream inputStream = new
		 * FileInputStream(file); POIFSFileSystem fs = new POIFSFileSystem(inputStream);
		 */
		String filename = uploadFile.getOriginalFilename();
		System.out.println("web++++:"+filename);
		//创建excel对象变量
		Workbook workbook = ExcelUtil.creatWorkbook(filename, uploadFile);
		System.out.println("web++++:"+workbook);
		Sheet sheet = workbook.getSheetAt(0);//获取第一个sheet
		System.out.println("sheet:"+sheet);
		if (sheet!=null) {
			//遍历行ROW
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);//每一行
				if (row==null) {
					continue;
				}
				//创建存放数据的对象
				Gross gross = new Gross();
				//遍历列cell
				for (int j = 0; j <=row.getLastCellNum(); j++) {
					Cell cell = row.getCell(j);//每一列
					if (cell==null) {
					continue;
					}
					setGross(j, gross, cell);
					
				}
				System.out.println("===="+gross);
				//把Excel数据添加进list集合里
				grosses.add(gross);
			}
			
		}
	
		workbook.close();
		return grosses;
	}
	
	/**   
	 * @Title: grossDownloadExcel   
	 * @Description: TODO(服务器上没有gross模板Excel就去创建)   
	 * @param: @param outputStream      
	 * @return: void      
	 * @throws   
	 */  
	public static void grossDownloadExcel(String url)throws Exception{
			System.out.println("创建模板文件！");
			Workbook web = new HSSFWorkbook();
			Sheet createSheet = web.createSheet("gross");
			
			Row createRow = createSheet.createRow(0);//创建第一行
			createRow.createCell(0).setCellValue("序号");
			createRow.createCell(1).setCellValue("经理");
			createRow.createCell(2).setCellValue("督导");
			createRow.createCell(3).setCellValue("餐厅编号");
			createRow.createCell(4).setCellValue("餐厅名称");
			createRow.createCell(5).setCellValue("日期");
			createRow.createCell(6).setCellValue("收入类型");
			createRow.createCell(7).setCellValue("TC");
			createRow.createCell(8).setCellValue("营业额");
			createRow.createCell(9).setCellValue("物料成本");
			createRow.createCell(10).setCellValue("毛利");
			createRow.createCell(11).setCellValue("毛利率");
			createRow.createCell(12).setCellValue("上周同比营业额");
			createRow.createCell(13).setCellValue("上周同比毛利");
			createRow.createCell(14).setCellValue("上周同比毛利率");
			
			Row createRow1 = createSheet.createRow(1);//创建第二行
			createRow1.createCell(0).setCellValue("x");
			createRow1.createCell(1).setCellValue("xx");
			createRow1.createCell(2).setCellValue("xx");
			createRow1.createCell(3).setCellValue("x");
			createRow1.createCell(4).setCellValue("xxx餐厅");
			createRow1.createCell(5).setCellValue("yyyy/MM/dd");
			createRow1.createCell(6).setCellValue("xx");
			createRow1.createCell(7).setCellValue("*.**");
			createRow1.createCell(8).setCellValue("*.**");
			createRow1.createCell(9).setCellValue("*.**");
			createRow1.createCell(10).setCellValue("*.**");
			createRow1.createCell(11).setCellValue("*.****");
			createRow1.createCell(12).setCellValue("*.**");
			createRow1.createCell(13).setCellValue("*.**");
			createRow1.createCell(14).setCellValue("*.****");
			
			
			File file = new File(url); 
			System.out.println(file.getPath());
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			web.write(fileOutputStream);
			fileOutputStream.close();
		
		
	}
	
	
	/**   
	 * @Title: setGross   
	 * @Description: TODO(gross表格读取判断条件)   
	 * @param: @param j
	 * @param: @param gross
	 * @param: @param cell
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: Gross      
	 * @throws   
	 */  
	private static Gross setGross(Integer j,Gross gross,Cell cell) throws Exception{
		
			if (j==1) {
				gross.setManager(ExcelUtil.formatCell(cell));
			}
			if (j==2) {
				gross.setSupervisor(ExcelUtil.formatCell(cell));
			}
			if (j==3) {
				gross.setRestaurantNum(ExcelUtil.formatCell(cell).replace(".0", ""));
			}
			if (j==4) {
				gross.setRestaurantName(ExcelUtil.formatCell(cell));
			}
			if (j==5) {
				gross.setDate(ExcelUtil.formatDate(cell));
		    }
			if (j==6) {
				gross.setIncomeType(ExcelUtil.formatCell(cell));
			}
		
			if (j==7) {
				gross.setTc(ExcelUtil.formatBigDecimal(cell));
			}
			if (j==8) {
				gross.setTurnover(ExcelUtil.formatBigDecimal(cell));
				
			}
			if (j==9) {
				gross.setMaterialCost(ExcelUtil.formatBigDecimal(cell));
			}
			if (j==10) {
				gross.setGrossProfit(ExcelUtil.formatBigDecimal(cell));
			}
			if (j==11) {
				gross.setGrossRate(ExcelUtil.formatBigDecimal(cell));
			}
			if (j==12) {
				gross.setLwturnover(ExcelUtil.formatBigDecimal(cell));
				 
			}
			if (j==13) {
				gross.setLwgrossProfit(ExcelUtil.formatBigDecimal(cell));
				
			}
			if (j==14) {
				gross.setLwgrossRate(ExcelUtil.formatBigDecimal(cell));
				
			}
		
		System.err.println(gross);
		return gross;
	}
	
	
}
