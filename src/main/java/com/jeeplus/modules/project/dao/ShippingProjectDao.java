/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.project.dao;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.project.entity.ShippingProject;

/**
 * 船运项目DAO接口
 * @author shipping
 * @version 2016-02-29
 */
@MyBatisDao
public interface ShippingProjectDao extends CrudDao<ShippingProject> {
	
}