package com.hlsofficesystem.service;



import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.hlsofficesystem.bean.Gross;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hucheng
 * @since 2019-07-22
 */
public interface GrossService extends IService<Gross> {

	//导入Excel报表数据到mysqlDB
	boolean excelToMysqlDB(List<Gross> grosses);
}
