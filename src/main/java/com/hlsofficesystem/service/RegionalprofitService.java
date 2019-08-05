package com.hlsofficesystem.service;


import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.hlsofficesystem.bean.Regionalprofit;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hucheng
 * @since 2019-07-25
 */
public interface RegionalprofitService extends IService<Regionalprofit> {
	    //导入Excel报表数据到mysqlDB
		boolean excelToMysqlDB(List<Regionalprofit> regionalprofits);
}
