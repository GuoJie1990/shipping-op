/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.client.entity;

import org.hibernate.validator.constraints.Length;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 客户基础信息Entity
 * @author shipping
 * @version 2016-02-24
 */
public class Client extends DataEntity<Client> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 姓名
	private String address;		// 地址
	private String owner;		// 负责人
	private String phone;		// 电话
	private String bank;		// 银行信息
	private String industry;		// 行业
	
	public Client() {
		super();
	}

	public Client(String id){
		super(id);
	}

	@Length(min=0, max=64, message="姓名长度必须介于 0 和 64 之间")
	@ExcelField(title="姓名", align=2, sort=7)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="地址长度必须介于 0 和 255 之间")
	@ExcelField(title="地址", align=2, sort=8)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=64, message="负责人长度必须介于 0 和 64 之间")
	@ExcelField(title="负责人", align=2, sort=9)
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	@Length(min=0, max=64, message="电话长度必须介于 0 和 64 之间")
	@ExcelField(title="电话", align=2, sort=10)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=64, message="银行信息长度必须介于 0 和 64 之间")
	@ExcelField(title="银行信息", align=2, sort=11)
	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}
	
	@Length(min=0, max=255, message="行业长度必须介于 0 和 255 之间")
	@ExcelField(title="行业", align=2, sort=12)
	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
}