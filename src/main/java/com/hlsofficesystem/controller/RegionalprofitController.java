package com.hlsofficesystem.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hlsofficesystem.bean.GrossQuery;
import com.hlsofficesystem.bean.Message;
import com.hlsofficesystem.bean.Regionalprofit;
import com.hlsofficesystem.bean.Subject;
import com.hlsofficesystem.service.RegionalprofitService;
import com.hlsofficesystem.service.SubjectService;
import com.hlsofficesystem.utils.Pager;
import com.hlsofficesystem.utils.RegionalprofitUploadExcel;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hucheng
 * @since 2019-07-25
 */
@Controller
@RequestMapping("/regionalprofit")
public class RegionalprofitController {
	@Autowired
	private RegionalprofitService regionalprofitService;
	@Autowired
	private SubjectService subjectService; 
	
	@RequestMapping(value = "/uploadExcel")
	@ResponseBody
	public Message uploadExcel(MultipartFile uploadFile,HttpServletRequest request) {
		
		Message message = new Message();
		
		if (uploadFile!=null) {
			  try {
	               //1.上传文件
	            @SuppressWarnings("deprecation")
				String realPath = request.getRealPath("/WEB-INF/static/exceltemplate/upload");
	               System.out.println(realPath);
	               //2.得到文件的名称
	               String filename = uploadFile.getOriginalFilename();
	               System.out.println(filename);
	               //3.上传文件
	               uploadFile.transferTo(new File(realPath+"/"+filename));
	               //4、给数据库添加数据
				 List<Regionalprofit> regionalprofit = RegionalprofitUploadExcel.regionalprofitUploadExcel(uploadFile);
				 
				 boolean boo = regionalprofitService.insertBatch(regionalprofit);
				 if (boo) {
				 //5、删除服务器上上传的文件   
					/*File deleteFile = new File(realPath+"/"+filename);
					System.out.println(deleteFile.exists());
					if (deleteFile.exists()) {
					 deleteFile.delete();
					}*/
				 	message.setMessage("数据导入成功！");
				 	return message;
				 } else {
					 message.setMessage("数据导入失败！");
					return message;
				 }
	           } catch (Exception e) {
	        	   message.setMessage("导入文件内容为空！");
	           }
		} else {
			message.setMessage("请选择要导入的Excel！");
			return message;	
		}
		return message;
	}
	
	/**   
	 * @Title: downloadExcel   
	 * @Description: TODO(regionalprofit导入文件模板下载)   
	 * @param: @param request
	 * @param: @return      
	 * @return: ResponseEntity<byte[]>      
	 * @throws   
	 */  
	@RequestMapping(value = "/downloadExcel")
	public ResponseEntity<byte[]> downloadExcel(HttpServletRequest request) {
		ResponseEntity<byte[]> resp=null;
		try {
		   @SuppressWarnings("deprecation")
		   String serverpath=request.getRealPath("/WEB-INF/static/exceltemplate/regionalprofit.xls");
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
           header.setContentDispositionFormData("attachment", URLEncoder.encode("regionalprofit.xls","utf-8"));
           resp = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(f), header, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
		
	}
	
	/**   
	 * @Title: selectAllRegionalprofit   
	 * @Description: TODO(regionalprofit数据展示（模糊查询+分页）)   
	 * @param: @param regionalprofitQuery 查询条件分装对象
	 * @param: @param pageIndex  起始页
	 * @param: @param pageSize	 每页显示条数
	 * @param: @param modelmap
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	@RequestMapping(value = "/showList/{pageIndex}/{pageSize}")
	public String selectAllRegionalprofit(
			GrossQuery regionalprofitQuery,
			@PathVariable("pageIndex")Integer pageIndex,
			@PathVariable("pageSize")Integer pageSize,
			ModelMap modelmap
			) {
		System.out.println(regionalprofitQuery+"----"+pageIndex+"----"+pageSize);
		//分页工具
		Page<Regionalprofit> page = new Page<Regionalprofit>(pageIndex, pageSize);
		//查询数据，封装查询条件
		EntityWrapper<Regionalprofit> entityWrapper = new EntityWrapper<Regionalprofit>();
		if (regionalprofitQuery!=null&&regionalprofitQuery.getTitle()!=null&&!regionalprofitQuery.getTitle().equals("")) {
			entityWrapper.like("StoreName", regionalprofitQuery.getTitle());
		}
		if (regionalprofitQuery!=null&&regionalprofitQuery.getStartDate()!=null&&!regionalprofitQuery.getStartDate().equals("")) {
			entityWrapper.gt("StoreTheYearStatisticsDate",regionalprofitQuery.getStartDate());
		}
		if (regionalprofitQuery!=null&&regionalprofitQuery.getEndDate()!=null&&!regionalprofitQuery.getEndDate().equals("")) {
			entityWrapper.lt("StoreTheYearStatisticsDate",regionalprofitQuery.getEndDate());
		}
	 	entityWrapper.orderBy("id").last("asc");
	 	//符合条件查询出的结果集
		Page<Regionalprofit> results = regionalprofitService.selectPage(page, entityWrapper);
		//获取总条数
		Integer totalCount = ((Long)results.getTotal()).intValue();
		//获取总页数
		Integer pageCount = ((Long)results.getPages()).intValue();
		//查询到每页的数据集合
		 List<Regionalprofit> regionalprofits = results.getRecords();
		 for (Regionalprofit regionalprofit : regionalprofits) {
			 Subject subject = subjectService.selectById(regionalprofit.getStoreSubjects());
			 regionalprofit.setSubject(subject);
			System.out.println(regionalprofit);
		}
		 //用自定义的工具类分装数据，返回页面显示
		Pager<Regionalprofit> pager = new Pager<Regionalprofit>(pageIndex, pageSize, pageCount, totalCount, regionalprofits);
		
		modelmap.put("pager", pager);
		modelmap.put("query", regionalprofitQuery);
		
		return "/templates/regionalprofitExcelList.jsp";
		
	}
	

    
    
	
}

