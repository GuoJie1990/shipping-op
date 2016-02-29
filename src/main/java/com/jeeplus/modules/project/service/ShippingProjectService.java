/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.project.entity.ShippingProject;
import com.jeeplus.modules.project.dao.ShippingProjectDao;

/**
 * 船运项目Service
 * @author shipping
 * @version 2016-02-29
 */
@Service
@Transactional(readOnly = true)
public class ShippingProjectService extends CrudService<ShippingProjectDao, ShippingProject> {

	public ShippingProject get(String id) {
		return super.get(id);
	}
	
	public List<ShippingProject> findList(ShippingProject shippingProject) {
		return super.findList(shippingProject);
	}
	
	public Page<ShippingProject> findPage(Page<ShippingProject> page, ShippingProject shippingProject) {
		return super.findPage(page, shippingProject);
	}
	
	@Transactional(readOnly = false)
	public void save(ShippingProject shippingProject) {
		super.save(shippingProject);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShippingProject shippingProject) {
		super.delete(shippingProject);
	}
	
}