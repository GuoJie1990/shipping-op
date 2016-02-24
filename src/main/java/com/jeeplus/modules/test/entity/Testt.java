/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.test.entity;


import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * testEntity
 * @author test
 * @version 2016-02-23
 */
public class Testt extends DataEntity<Testt> {
	
	private static final long serialVersionUID = 1L;
	
	public Testt() {
		super();
	}

	public Testt(String id){
		super(id);
	}

}