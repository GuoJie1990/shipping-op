/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.client.dao;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.client.entity.Client;

/**
 * 客户基础信息DAO接口
 * @author shipping
 * @version 2016-02-24
 */
@MyBatisDao
public interface ClientDao extends CrudDao<Client> {
	
}