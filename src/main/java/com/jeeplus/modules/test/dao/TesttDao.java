/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.test.dao;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.test.entity.Testt;

/**
 * testDAO接口
 * @author test
 * @version 2016-02-23
 */
@MyBatisDao
public interface TesttDao extends CrudDao<Testt> {
	
}