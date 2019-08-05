package com.hlsofficesystem.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import com.hlsofficesystem.bean.Regionalprofit;





public class RegionalprofitUploadExcel {
	public static List<Regionalprofit> regionalprofitUploadExcel(MultipartFile uploadFile)throws Exception{
		List<Regionalprofit> regionalprofits = new ArrayList<Regionalprofit>();
		
		String filename = uploadFile.getOriginalFilename();
		
		//创建excel对象变量
		Workbook workbook = ExcelUtil.creatWorkbook(filename, uploadFile);
		
		Sheet sheet = workbook.getSheetAt(0);//获取第一个sheet
		if (sheet!=null) {
			//遍历行ROW
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);//每一行
				if (row==null) {
					continue;
				}
				System.out.println("row=========================");
				//创建存放数据的对象
				Regionalprofit regionalprofit = new Regionalprofit();
				//遍历列cell
				for (int j = 0; j <= row.getLastCellNum(); j++) {
					Cell cell = row.getCell(j);//每一列
					if (cell==null) {
						continue;
					}
					//System.out.println(sheet.getRow(3).getCell(1).getDateCellValue());
				    //setRegionalprofit = setRegionalprofit(j, regionalprofit,cell,sheet.getRow(0));
					setRegionalprofit(j, regionalprofit,cell,sheet.getRow(0));
				}
				//把Excel数据添加进list集合里
				System.out.println(regionalprofit);
				regionalprofits.add(regionalprofit);
			}
			
		} else {
			System.out.println("sheet为空！");
		}
		
		workbook.close();
		return regionalprofits;
	}
	
	
	/**   
	 * @Title: setRegionalprofit   
	 * @Description: TODO(Regionalprofit表格读取判断条件)   
	 * @param: @param j
	 * @param: @param regionalprofit
	 * @param: @param cell
	 * @param: @param row
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: Regionalprofit      
	 * @throws   
	 */  
	private static Regionalprofit setRegionalprofit(Integer j,Regionalprofit regionalprofit,Cell cell,Row row) throws Exception{
		
			if (j==0) {
				regionalprofit.setStoreNumber(ExcelUtil.formatCell(cell));
			}
			if (j==1) {
				regionalprofit.setStoreName(ExcelUtil.formatCell(cell));
			}
			if (j==2) {
				String cellValue = ExcelUtil.formatCell(cell);
				regionalprofit.setStoreSubjects(ExcelUtil.getSubjectNumber(cellValue));
			}
			if (j==3) {
				regionalprofit.setStoreLastYearStatistics(ExcelUtil.formatBigDecimal(cell));
			}
			if (j==4) {
				regionalprofit.setStoreTheYearStatistics(ExcelUtil.formatBigDecimal(cell));
			}
			//System.err.println(row.getLastCellNum()-1);
			regionalprofit.setStoreTheYearStatisticsDate(ExcelUtil.formatDate(row.getCell(row.getLastCellNum()-1)));
		    
			return regionalprofit;
			
		}
	

	/**   
	 * @Title: regionalprofitDownloadExcel   
	 * @Description: TODO(服务器上没有regionalprofit模板Excel就去创建)   
	 * @param: @param url
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws   
	 */  
	public static void regionalprofitDownloadExcel(String url)throws Exception{
		System.out.println("创建模板文件！");
		Workbook web = new HSSFWorkbook();
		Sheet createSheet = web.createSheet("regionalprofit");
		
		Row createRow = createSheet.createRow(0);//创建第一行
		createRow.createCell(0).setCellValue("商店编号");
		createRow.createCell(1).setCellValue("店名");
		createRow.createCell(2).setCellValue("项目名称");
		createRow.createCell(3).setCellValue("2018/05/01");
		createRow.createCell(4).setCellValue("2019/05/01");
		
		Row createRow1 = createSheet.createRow(1);//创建第二行
		createRow1.createCell(0).setCellValue("A01");
		createRow1.createCell(1).setCellValue("松江新南");
		createRow1.createCell(2).setCellValue("一、产品销售收入");
		createRow1.createCell(3).setCellValue("68033.27");
		createRow1.createCell(4).setCellValue("108107.12");
		
		File file = new File(url); 
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		web.write(fileOutputStream);
		fileOutputStream.close();
	}
	
	
}
