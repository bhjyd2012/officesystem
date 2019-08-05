package com.hlsofficesystem.bean;

import java.util.HashMap;
import java.util.Map;

/**   
 * @ClassName:  Message   
 * @Description:TODO(返回提示信息和分页信息给前端的封装类)   
 * @author: hucheng 
 * @date:   2019年7月22日 上午11:19:56       
 * @Copyright: 2019 www.xxxx.com Inc. All rights reserved. 
 * 注意：本内容仅限于xxxxxxxxx传阅，禁止外泄以及用于其他的商业目
*/ 
public class Message {

	/** @Fields code : TODO(状态码：100表示成功，200表示失败) */   
	private int code;
	
	/** @Fields message : TODO(提示信息) */   
	private String message;
	
	/**  @Fields extend : TODO(用户要返回给浏览器的数据) */   
	private Map<String, Object> extend = new HashMap<String,Object>();
	
	/**   
	 * @Title: success   
	 * @Description: TODO(状态码100-处理成功信息提示)   
	 * @param: @return      
	 * @return: Message      
	 * @throws   
	 */  
	public static Message success() {
		Message message = new Message();
		message.setCode(100);
		message.setMessage("处理成功！");
		return message;
		
	}
	
	/**   
	 * @Title: fail   
	 * @Description: TODO(状态码200-处理失败信息提示)   
	 * @param: @return      
	 * @return: Message      
	 * @throws   
	 */  
	public static Message fail() {
		Message message = new Message();
		message.setCode(200);
		message.setMessage("处理失败！");
		return message;
		
	}
	
	/**   
	 * @Title: add   
	 * @Description: TODO(自定义快捷添加方法)   
	 * @param: @param key（设置的键）
	 * @param: @param object（存储的对象）
	 * @param: @return      
	 * @return: Message      
	 * @throws   
	 */  
	public Message add(String key,Object object){
		this.getExtend().put(key, object);
		return this;
		
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getExtend() {
		return extend;
	}

	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}
	
	
}
