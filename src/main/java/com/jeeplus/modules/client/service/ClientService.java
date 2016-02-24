/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.client.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.client.entity.Client;
import com.jeeplus.modules.client.dao.ClientDao;

/**
 * 客户基础信息Service
 * @author shipping
 * @version 2016-02-24
 */
@Service
@Transactional(readOnly = true)
public class ClientService extends CrudService<ClientDao, Client> {

	public Client get(String id) {
		return super.get(id);
	}
	
	public List<Client> findList(Client client) {
		return super.findList(client);
	}
	
	public Page<Client> findPage(Page<Client> page, Client client) {
		return super.findPage(page, client);
	}
	
	@Transactional(readOnly = false)
	public void save(Client client) {
		super.save(client);
	}
	
	@Transactional(readOnly = false)
	public void delete(Client client) {
		super.delete(client);
	}
	
}