package com.hlsofficesystem.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @ClassName: ExcelUtil
 * @Description:TODO(Excel表格数据格式化工具类)
 * @author: hucheng
 * @date: 2019年7月22日 下午1:02:03
 * @Copyright: 2019 www.xxxx.com Inc. All rights reserved.
 *             注意：本内容仅限于xxxxxxxxx传阅，禁止外泄以及用于其他的商业目
 */
public class ExcelUtil {
	/**
	 * 格式化单元格返回其内容 格式化成string返回。
	 * 
	 * @param cell
	 * @return
	 */
	public static String formatCell(Cell cell) {
		if (cell == null) {
			return "";
		} else {
			if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
				return String.valueOf(cell.getBooleanCellValue());
			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				return String.valueOf(cell.getNumericCellValue());
			} else {
				return String.valueOf(cell.getStringCellValue());
			}
		}
	}

	/**
	 * 
	 * 返回 日期 date 2019/3/28 14:18:00 这种类型的可以。
	 */
	public static Date formatDate(Cell cell) throws ParseException {

		String str = cell.toString();

		Date date = null;
		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			date = new SimpleDateFormat("yyyy/MM/dd").parse(str);
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			date = cell.getDateCellValue();
		}
		return date;
	}

	/**
	 * 返回 BigDecimal 数据
	 */
	public static BigDecimal formatBigDecimal(Cell cell) throws ParseException {
		String str = ExcelUtil.formatCell(cell);
		System.out.println(str+"------bigd");
		BigDecimal num = null;
		if (!str.equals("")||str!=null) {
			num = new BigDecimal(str);
		}
		return num;
	}

	/**
	 * 根据对应文件后缀，返回 workbook对象
	 */
	public static Workbook creatWorkbook(String filename, MultipartFile uploadFile) {
		System.out.println(filename+"=====rrrrrrrrrrrrrrrrrrrrrrr====="+uploadFile);
		// 1. 定义excel对象变量
		Workbook workbook = null;
		try {
			// 2. 判断后缀.决定使用的解析方式. 决定如何创建具体的对象
			if (filename.endsWith("xls")) {
				System.out.println("执行的是xls");
				// Excel-2003
				workbook = new HSSFWorkbook(uploadFile.getInputStream());
			} else if (filename.endsWith("xlsx")) {
				System.out.println("执行的是xlsx");
				// Excel-2007
				System.out.println(uploadFile);
				workbook = new XSSFWorkbook(uploadFile.getInputStream());
				
			} else {
				// 未知内容
				//return null;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return workbook;
	}
	
	/**   
	 * @Title: format   
	 * @Description: TODO(格式化字符串去除英文大小写及特殊符号)   
	 * @param: @param s
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String format(String string){         
		String str=
		string.replaceAll("[`qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM~!@#$%^&*()+=|{}';',\\[\\]<>/?~！@#￥%……& amp;*（）——+|{}【】‘；：:”“’。，？|-]", ""); 
		return str;
	}  
	
	
	//@Test
	/**   
	 * @Title: getstoreSubjectNumber   
	 * @Description: TODO(商店科目转换成对应的科目编号)   
	 * @param: @param storeSubject
	 * @param: @return      
	 * @return: String    科目名称
	 * @throws   
	 */  
	public static String getSubjectNumber(String storeSubject) {
		
		if (storeSubject!=null) {
        
		if (storeSubject.contains(".")) {
			int lastIndexOf = storeSubject.lastIndexOf(".");
			storeSubject = storeSubject.substring(lastIndexOf+1);
			System.out.println(storeSubject);
		} else if(storeSubject.contains("、")){
			int lastIndexOf = storeSubject.lastIndexOf("、");
			storeSubject = storeSubject.substring(lastIndexOf+1);
			System.out.println(storeSubject);
		}else {
			//storeSubject = storeSubject.trim();
			//System.err.println(storeSubject+"----------------------");
		}
		
		storeSubject = format(storeSubject);
		System.err.println(storeSubject);
		
		if (storeSubject.equals("产品销售收入")) {
			storeSubject = "AC01";
		}
		if (storeSubject.equals("产成品净收入")) {
			storeSubject = "AC0101";
		}
		if (storeSubject.equals("产成品收入")) {
			storeSubject = "AC010101";
		}
		if (storeSubject.equals("商业折扣")) {
			storeSubject = "AC010102";
		}
		if (storeSubject.equals("借出货物收入")) {
			storeSubject = "AC0102";
		}
		if (storeSubject.equals("减产品销售成本")) {
			storeSubject = "AC010201";
		}
		if (storeSubject.equals("产成品成本")) {
			storeSubject = "AC01020101";
		}
		if (storeSubject.equals("借出货物成本")) {
			storeSubject = "AC01020102";
		}
		if (storeSubject.equals("产品销售费用")) {
			storeSubject = "AC0103";
		}
		if (storeSubject.equals("抽成")) {
			storeSubject = "AC010301";
		}
		if (storeSubject.equals("报废")) {
			storeSubject = "AC010302";
		}
		if (storeSubject.equals("共有成本")) {
			storeSubject = "AC010303";
		}
		if (storeSubject.equals("盘盈盘亏")) {
			storeSubject = "AC010304";
		}
		if (storeSubject.equals("货赔")) {
			storeSubject = "AC010305";
		}
		
		
		if (storeSubject.equals("产品销售利润")) {
			storeSubject = "AC02";
		}
		if (storeSubject.equals("减管理费用")) {
			storeSubject = "AC0201";
		}
		if (storeSubject.equals("租金")) {
			storeSubject = "AC020101";
		}
		if (storeSubject.equals("水费")) {
			storeSubject = "AC020102";
		}
		if (storeSubject.equals("电费")) {
			storeSubject = "AC020103";
		}
		if (storeSubject.equals("工资、营运奖金、福利等")) {
			storeSubject = "AC020104";
		}
		if (storeSubject.equals("餐厅活动经费")) {
			storeSubject = "AC020105";
		}
		if (storeSubject.equals("煤气")) {
			storeSubject = "AC020106";
		}
		if (storeSubject.equals("外购零星设备")) {
			storeSubject = "AC020107";
		}
		if (storeSubject.equals("配送购设备货单开")) {
			storeSubject = "AC020108";
		}
		if (storeSubject.equals("设备抽成")) {
			storeSubject = "AC020109";
		}
		if (storeSubject.equals("营运薪酬")) {
			storeSubject = "AC020110";
		}
		if (storeSubject.equals("税含国税、地税")) {
			storeSubject = "AC020111";
		}
		if (storeSubject.equals("工商管理、年检等")) {
			storeSubject = "AC020112";
		}
		if (storeSubject.equals("卫生费")) {
			storeSubject = "AC020113";
		}
		if (storeSubject.equals("电话费")) {
			storeSubject = "AC020114";
		}
		if (storeSubject.equals("市交费车费")) {
			storeSubject = "AC020115";
		}
		if (storeSubject.equals("背胶款、喷绘、代垫款等")) {
			storeSubject = "AC020116";
		}
		if (storeSubject.equals("推广费")) {
			storeSubject = "AC020117";
		}
		if (storeSubject.equals("广告单")) {
			storeSubject = "AC020118";
		}
		if (storeSubject.equals("零星费用、办公费")) {
			storeSubject = "AC020119";
		}
		if (storeSubject.equals("招待费")) {
			storeSubject = "AC020120";
		}
		if (storeSubject.equals("汇款手续费")) {
			storeSubject = "AC020121";
		}
		if (storeSubject.equals("维修费")) {
			storeSubject = "AC020122";
		}
		if (storeSubject.equals("保险基金")) {
			storeSubject = "AC020123";
		}
		if (storeSubject.equals("运费")) {
			storeSubject = "AC020124";
		}
		if (storeSubject.equals("消杀费")) {
			storeSubject = "AC020125";
		}
		if (storeSubject.equals("咨询费")) {
			storeSubject = "AC020126";
		}
		if (storeSubject.equals("其他费用")) {
			storeSubject = "AC020127";
		}
		
		
		if (storeSubject.equals("营业利润")) {
			storeSubject = "AC03";
		}
		if (storeSubject.equals("加投资收益")) {
			storeSubject = "AC0301";
		}
		if (storeSubject.equals("营业外收入")) {
			storeSubject = "AC0302";
		}
		if (storeSubject.equals("其他业务收入")) {
			storeSubject = "AC0303";
		}
		if (storeSubject.equals("房租、设备等收入")) {
			storeSubject = "AC030301";
		}
		if (storeSubject.equals("废油收入")) {
			storeSubject = "AC030302";
		}
		if (storeSubject.equals("纸皮等废品收入")) {
			storeSubject = "AC030303";
		}
		if (storeSubject.equals("货物赔款及存款扣款")) {
			storeSubject = "AC030304";
		}
		if (storeSubject.equals("减营业外支出")) {
			storeSubject = "AC0304";
		}
		if (storeSubject.equals("资金流失")) {
			storeSubject = "AC030401";
		}
		if (storeSubject.equals("调账")) {
			storeSubject = "AC030402";
		}
		if (storeSubject.equals("奖金")) {
			storeSubject = "AC030403";
		}
		if (storeSubject.equals("加以前年度损益调整")) {
			storeSubject = "AC0305";
		}
		
		
		if (storeSubject.equals("利润总额")) {
			storeSubject = "AC04";
		}
		if (storeSubject.equals("减所得税")) {
			storeSubject = "AC0401";
		}
		
		
		if (storeSubject.equals("净利润")) {
			storeSubject = "AC05";
		}
		
		
		if (storeSubject.equals("毛利率")) {
			storeSubject = "AC06";
		}
		if (storeSubject.equals("上年未分配利润")) {
			storeSubject = "AC0601"; 
		}
		if (storeSubject.equals("本月已分配利润")) {
			storeSubject = "AC0602";
		}
		if (storeSubject.equals("本年未分配利润")) {
			storeSubject = "AC0603";
		}
		if (storeSubject.equals("月平均租金")) {
			storeSubject = "AC0604";
		}
		if (storeSubject.equals("实际营运利润")) {
			storeSubject = "AC0605";
		}
		if (storeSubject.equals("装修购设备等转增资本分红")) {
			storeSubject = "AC0606";
		}
		if (storeSubject.equals("实际分红")) {
			storeSubject = "AC0607";
		}
		
		
		if (storeSubject.equals("实际管理费用剔除其他及付租金月租金")) {
			storeSubject = "AC07";
		}
		
		
		if (storeSubject.equals("实际营运利润剔除营业外支出")) {
			storeSubject = "AC08";
		}
		if (storeSubject.equals("复核公式")) {
			storeSubject = "AC0801";
		}
		
	}	
		//System.out.println(storeSubject);
		return storeSubject;
		
	}
	
	
	/**   
	 * @Title: deleteFile   
	 * @Description: TODO(删除Excel文件)   
	 * @param: @param path
	 * @param: @return      
	 * @return: boolean      
	 * @throws   
	 */  
	public static boolean deleteFile(String path) {
		File file = new File(path);
		boolean flag = false;
		if (file!=null) {
			File[] listFiles = file.listFiles();
			System.out.println(file);
			for (File delFile : listFiles) {
				System.out.println(delFile.getName());
				if (delFile.exists()&&delFile.isFile()) {
					if (!delFile.isDirectory()) {
						if (delFile.getName().endsWith(".xls")||delFile.getName().endsWith(".xlsx")) {
							delFile.delete();
							flag = true;
						}
					}
				}
				
			}	
		}
		return flag;
			
	}
	 

}
