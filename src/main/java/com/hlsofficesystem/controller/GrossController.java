package com.hlsofficesystem.controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hlsofficesystem.bean.Gross;
import com.hlsofficesystem.bean.GrossQuery;
import com.hlsofficesystem.bean.Message;
import com.hlsofficesystem.service.GrossService;
import com.hlsofficesystem.utils.GrossUploadExcel;
import com.hlsofficesystem.utils.Pager;
import com.hlsofficesystem.utils.RegionalprofitUploadExcel;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hucheng
 * @since 2019-07-22
 */
@Controller
@RequestMapping("/gross")
public class GrossController {

	@Autowired
	private  GrossService grossService;
	
	/**   
	 * @Title: uploadExcel   
	 * @Description: TODO(导入Excel表格数据到mysqlDB)   
	 * @param: @param uploadFile 上传文件
	 * @param: @param request	请求
	 * @param: @return      
	 * @return: Message   提示信息及返回数据分装对象   
	 * @throws   
	 */  
	@RequestMapping(value = "/uploadExcel")
	@ResponseBody
	public Message uploadExcel(MultipartFile uploadFile,HttpServletRequest request) {
		System.out.println(uploadFile);
		Message message = new Message();
		if (uploadFile!=null) {
			  try {
				  String path = ResourceUtils.getFile("classpath:static/exceltemplate/upload").getPath();
				  String realPath = path.replace("target\\classes\\static\\exceltemplate\\upload", "src\\main\\resources\\static\\exceltemplate\\upload");
	               //1.上传文件
	               //String realPath = request.getRealPath("static/exceltemplate/upload");
	               System.out.println(realPath);
	               //2.得到文件的名称
				  String filename = uploadFile.getOriginalFilename();
				  System.out.println(filename);
				  //3.上传文件
				  uploadFile.transferTo(new File(realPath+"\\"+filename));
				  System.out.println("uploadurl+++++++"+uploadFile);
				  //4、给数据库添加数据
				  List<Gross> grosses = GrossUploadExcel.grossUploadExcel(filename,realPath);
				  for (Gross gross : grosses) {
				       System.out.println("----------"+gross);
				    }
				  boolean boo = grossService.excelToMysqlDB(grosses);
				  if (boo) {
				  	message.setMessage("数据导入成功！");
				return message;
				  } else {
				message.setMessage("数据导入失败！");
				return message;
				  }
	           } catch (Exception e) {
	        	   message.setMessage("导入文件内容为空！");
	        	   e.printStackTrace();
	           }
		} else {
			message.setMessage("请选择要导入的Excel！");
			return message;	
		}
		return message;
	}
	
	/**   
	 * @Title: downloadExcel   
	 * @Description: TODO(gross导入文件模板下载)   
	 * @param: @param request
	 * @param: @return      
	 * @return: ResponseEntity<byte[]>      
	 * @throws   
	 */  
	@GetMapping(value = "/downloadExcel")
	public ResponseEntity<byte[]> downloadExcel() {
		ResponseEntity<byte[]> resp=null;
		String path = System.getProperty("user.dir");
		String serverpath = path.replace("officesystem", "officesystem\\src\\main\\resources\\static\\exceltemplate\\gross.xls");
		//System.out.println(serverpath);
		try {
			   File f=new File(serverpath);
	           System.out.println(serverpath);
	           //检查服务器上有没有模板文件，没有就创建
	           if(!f.exists()){
	        	   RegionalprofitUploadExcel.regionalprofitDownloadExcel(serverpath);
	           }
			
           //创建http头信息的对象
           HttpHeaders header=new HttpHeaders();
           //标记以流的方式做出响应
           header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
           //设置以弹窗的方式提示用户下载
           //attachment 表示以附件的形式响应给客户端
           header.setContentDispositionFormData("attachment", URLEncoder.encode("gross.xls","utf-8"));
           resp = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(f), header, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
		}
	
	
	/**   
	 * @Title: selectAllGross   
	 * @Description: TODO(gross数据展示（模糊查询+分页）)   
	 * @param: @param grossQuery 查询条件分装对象
	 * @param: @param pageIndex	 起始页
	 * @param: @param pageSize	每页显示条数
	 * @param: @param modelmap
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	@RequestMapping(value = "/showList/{pageIndex}/{pageSize}")
	public String selectAllGross(
			GrossQuery grossQuery,
			@PathVariable("pageIndex")Integer pageIndex,
			@PathVariable("pageSize")Integer pageSize,
			ModelMap modelmap
			) {
		System.out.println(grossQuery+"----"+pageIndex+"----"+pageSize);
		//分页工具
		Page<Gross> page = new Page<Gross>(pageIndex, pageSize);
		//查询数据，封装查询条件
		EntityWrapper<Gross> entityWrapper = new EntityWrapper<Gross>();
		if (grossQuery!=null&&grossQuery.getTitle()!=null&&!grossQuery.getTitle().equals("")) {
			entityWrapper.like("restaurantName", grossQuery.getTitle());
		}
		if (grossQuery!=null&&grossQuery.getStartDate()!=null&&!grossQuery.getStartDate().equals("")) {
			entityWrapper.ge("date",grossQuery.getStartDate());
		}
		if (grossQuery!=null&&grossQuery.getEndDate()!=null&&!grossQuery.getEndDate().equals("")) {
			entityWrapper.le("date",grossQuery.getEndDate());
		}
	 	entityWrapper.orderBy("id").last("desc");
		
		//符合条件查询出的结果集
		Page<Gross> results = grossService.selectPage(page, entityWrapper);
		//获取总条数
		Integer totalCount = ((Long)results.getTotal()).intValue();
		//获取总页数
		Integer pageCount = ((Long)results.getPages()).intValue();
		//查询是否有上一页
		//boolean hasPrevious = results.hasPrevious();
		//查询是否有下一页
		//boolean hasNext = results.hasNext();
		 
		 //查询到每页的数据集合
		 List<Gross> grosses = results.getRecords();
		/*
		 * for (House house : houses) { Housetype housetype =
		 * housetypeService.selectById(house.getTypeid());
		 * house.setHousetype(housetype); }
		 
		 for (Gross gross : grosses) {
			System.out.println(gross);
		}*/
		 //用自定义的工具类分装数据，返回页面显示
		Pager<Gross> pager = new Pager<Gross>(pageIndex, pageSize, pageCount, totalCount, grosses);
		
		modelmap.put("pager", pager);
		modelmap.put("query", grossQuery);
		
		return "/templates/grossExcelList.jsp";
		
	}
	
	
}

