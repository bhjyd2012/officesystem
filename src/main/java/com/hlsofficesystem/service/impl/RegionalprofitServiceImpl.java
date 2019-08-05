package com.hlsofficesystem.service.impl;

import com.hlsofficesystem.bean.Regionalprofit;
import com.hlsofficesystem.mapper.RegionalprofitMapper;
import com.hlsofficesystem.service.RegionalprofitService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hucheng
 * @since 2019-07-25
 */
@Service
public class RegionalprofitServiceImpl extends ServiceImpl<RegionalprofitMapper, Regionalprofit> implements RegionalprofitService {
	@Autowired
	private RegionalprofitMapper regionalprofitMapper;
	
	/**   
	 * <p>Title: excelToMysqlDB</p>   
	 * <p>Description: </p>   
	 * @param regionalprofits
	 * @return   
	 * @see com.upload.service.RegionalprofitService#excelToMysqlDB(java.util.List)   
	 */ 
	public boolean excelToMysqlDB(List<Regionalprofit> regionalprofits) {
		// TODO Auto-generated method stub
		return this.insertBatch(regionalprofits);
	}

}
