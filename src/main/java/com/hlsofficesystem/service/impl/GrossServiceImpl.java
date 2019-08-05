package com.hlsofficesystem.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hlsofficesystem.bean.Gross;
import com.hlsofficesystem.mapper.GrossMapper;
import com.hlsofficesystem.service.GrossService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hucheng
 * @since 2019-07-22
 */
@Service
public class GrossServiceImpl extends ServiceImpl<GrossMapper, Gross> implements GrossService {
	@Autowired
	private GrossMapper grossMapper;
	
	/**   
	 * <p>Title: excelToMysqlDB</p>   
	 * <p>Description: </p>   
	 * @param grosses
	 * @return   
	 * @see com.upload.service.GrossService#excelToMysqlDB(java.util.List)   
	 */ 
	@Transactional
	public boolean excelToMysqlDB(List<Gross> grosses) {
		// TODO Auto-generated method stub
		return this.insertBatch(grosses);
	}

}
