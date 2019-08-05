package com.hlsofficesystem.bean;

/**   
 * @ClassName:  GrossQuery   
 * @Description:TODO(分装查询条件参数)   
 * @author: hucheng 
 * @date:   2019年7月24日 上午11:08:18       
 * @Copyright: 2019 www.xxxx.com Inc. All rights reserved. 
 * 注意：本内容仅限于xxxxxxxxx传阅，禁止外泄以及用于其他的商业目
*/  
public class GrossQuery {
	private String title;//标题信息
	private String startDate;//开始时间
	private String endDate;//结束时间
	public GrossQuery() {
		super();
	}
	public GrossQuery(String title, String startDate, String endDate) {
		super();
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "GrossQuery [title=" + title + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	
}
