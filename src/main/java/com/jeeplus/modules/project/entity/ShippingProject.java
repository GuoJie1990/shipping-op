/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.project.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 船运项目Entity
 * @author shipping
 * @version 2016-02-29
 */
public class ShippingProject extends DataEntity<ShippingProject> {
	
	private static final long serialVersionUID = 1L;
	private Integer clientId;		// 客户id
	private String shipName;		// 船名
	private Integer onLoad;		// 装港
	private Integer offLoad;		// 卸港
	private Date commencingDay;		// commencing_day
	private Date cancellingDay;		// cancelling_day
	private String cargoName;		// 货物名
	private String cargoWeight;		// 货量
	private Double initCharge;		// 运费
	private String contract;		// 合同电子版
	private String hire;		// hire
	
	public ShippingProject() {
		super();
	}

	public ShippingProject(String id){
		super(id);
	}

	@ExcelField(title="客户id", align=2, sort=7)
	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	
	@Length(min=0, max=64, message="船名长度必须介于 0 和 64 之间")
	@ExcelField(title="船名", align=2, sort=8)
	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	
	@ExcelField(title="装港", align=2, sort=9)
	public Integer getOnLoad() {
		return onLoad;
	}

	public void setOnLoad(Integer onLoad) {
		this.onLoad = onLoad;
	}
	
	@ExcelField(title="卸港", align=2, sort=10)
	public Integer getOffLoad() {
		return offLoad;
	}

	public void setOffLoad(Integer offLoad) {
		this.offLoad = offLoad;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="commencing_day", align=2, sort=11)
	public Date getCommencingDay() {
		return commencingDay;
	}

	public void setCommencingDay(Date commencingDay) {
		this.commencingDay = commencingDay;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="cancelling_day", align=2, sort=12)
	public Date getCancellingDay() {
		return cancellingDay;
	}

	public void setCancellingDay(Date cancellingDay) {
		this.cancellingDay = cancellingDay;
	}
	
	@Length(min=0, max=64, message="货物名长度必须介于 0 和 64 之间")
	@ExcelField(title="货物名", align=2, sort=13)
	public String getCargoName() {
		return cargoName;
	}

	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}
	
	@Length(min=0, max=64, message="货量长度必须介于 0 和 64 之间")
	@ExcelField(title="货量", align=2, sort=14)
	public String getCargoWeight() {
		return cargoWeight;
	}

	public void setCargoWeight(String cargoWeight) {
		this.cargoWeight = cargoWeight;
	}
	
	@ExcelField(title="运费", align=2, sort=15)
	public Double getInitCharge() {
		return initCharge;
	}

	public void setInitCharge(Double initCharge) {
		this.initCharge = initCharge;
	}
	
	@Length(min=0, max=64, message="合同电子版长度必须介于 0 和 64 之间")
	@ExcelField(title="合同电子版", align=2, sort=16)
	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}
	
	@Length(min=0, max=64, message="hire长度必须介于 0 和 64 之间")
	@ExcelField(title="hire", align=2, sort=17)
	public String getHire() {
		return hire;
	}

	public void setHire(String hire) {
		this.hire = hire;
	}
	
}