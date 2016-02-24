/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.test.entity.Testt;
import com.jeeplus.modules.test.dao.TesttDao;

/**
 * testService
 * @author test
 * @version 2016-02-23
 */
@Service
@Transactional(readOnly = true)
public class TesttService extends CrudService<TesttDao, Testt> {

	public Testt get(String id) {
		return super.get(id);
	}
	
	public List<Testt> findList(Testt testt) {
		return super.findList(testt);
	}
	
	public Page<Testt> findPage(Page<Testt> page, Testt testt) {
		return super.findPage(page, testt);
	}
	
	@Transactional(readOnly = false)
	public void save(Testt testt) {
		super.save(testt);
	}
	
	@Transactional(readOnly = false)
	public void delete(Testt testt) {
		super.delete(testt);
	}
	
}