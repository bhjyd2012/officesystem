package com.hlsofficesystem.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hlsofficesystem.utils.ExcelUtil;



/**   
 * @ClassName:  DeleteFileExcelController   
 * @Description:TODO(定时删除上传的Excel文件)   
 * @author: hucheng 
 * @date:   2019年7月30日 下午4:57:31       
 * @Copyright: 2019 www.xxxx.com Inc. All rights reserved. 
 * 注意：本内容仅限于xxxxxxxxx传阅，禁止外泄以及用于其他的商业目
*/  
@Component
public class DeleteFileExcelController {
	/**   
     * @Title: deleteFileWithCron   
     * @Description: TODO(定时删除上传的Excel文件)   
     * @param:       
     * @return: void      
     * @throws   
     */  
    //@Scheduled(cron = "0 15 10 ? * SUN")//每周日上午10点15分清理上传文件夹  
	@Scheduled(cron = "0 0 11 * * ?")
    public void deleteFileWithCron() {  
		
		System.out.println("开始清空上传文件夹中Excel文件");
		String path = System.getProperty("user.dir");
		String serverpath = path.replace("officesystem", "officesystem/src/main/resources/static/exceltemplate/upload/");
		/*String realPath = this.getClass().getResource("/").getPath();
		String urlString = realPath.replaceAll("/target/classes/", "/src/main/webapp/WEB-INF/static/exceltemplate/upload/");*/
		if (ExcelUtil.deleteFile(serverpath)) {
			System.out.println("删除成功！");
		} else {
			System.out.println("没有可删除Excel文件！");
		}

    } 
	
	
}
