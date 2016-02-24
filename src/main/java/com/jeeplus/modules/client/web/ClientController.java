/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.client.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.config.Global;
import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.utils.excel.ImportExcel;
import com.jeeplus.modules.client.entity.Client;
import com.jeeplus.modules.client.service.ClientService;

/**
 * 客户基础信息Controller
 * @author shipping
 * @version 2016-02-24
 */
@Controller
@RequestMapping(value = "${adminPath}/client/client")
public class ClientController extends BaseController {

	@Autowired
	private ClientService clientService;
	
	@ModelAttribute
	public Client get(@RequestParam(required=false) String id) {
		Client entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = clientService.get(id);
		}
		if (entity == null){
			entity = new Client();
		}
		return entity;
	}
	
	/**
	 * 客户列表页面
	 */
	@RequiresPermissions("client:client:list")
	@RequestMapping(value = {"list", ""})
	public String list(Client client, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Client> page = clientService.findPage(new Page<Client>(request, response), client); 
		model.addAttribute("page", page);
		return "modules/client/clientList";
	}

	/**
	 * 查看，增加，编辑客户表单页面
	 */
	@RequiresPermissions(value={"client:client:view","client:client:add","client:client:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(Client client, Model model) {
		model.addAttribute("client", client);
		return "modules/client/clientForm";
	}

	/**
	 * 保存客户
	 */
	@RequiresPermissions(value={"client:client:add","client:client:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(Client client, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, client)){
			return form(client, model);
		}
		clientService.save(client);
		addMessage(redirectAttributes, "保存客户成功");
		return "redirect:"+Global.getAdminPath()+"/client/client/?repage";
	}
	
	/**
	 * 删除客户
	 */
	@RequiresPermissions("client:client:del")
	@RequestMapping(value = "delete")
	public String delete(Client client, RedirectAttributes redirectAttributes) {
		clientService.delete(client);
		addMessage(redirectAttributes, "删除客户成功");
		return "redirect:"+Global.getAdminPath()+"/client/client/?repage";
	}
	
	/**
	 * 批量删除客户
	 */
	@RequiresPermissions("client:client:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			clientService.delete(clientService.get(id));
		}
		addMessage(redirectAttributes, "删除客户成功");
		return "redirect:"+Global.getAdminPath()+"/client/client/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("client:client:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(Client client, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "客户"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Client> page = clientService.findPage(new Page<Client>(request, response, -1), client);
    		new ExportExcel("客户", Client.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出客户记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/client/client/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("client:client:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Client> list = ei.getDataList(Client.class);
			for (Client client : list){
				clientService.save(client);
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条客户记录");
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入客户失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/client/client/?repage";
    }
	
	/**
	 * 下载导入客户数据模板
	 */
	@RequiresPermissions("client:client:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "客户数据导入模板.xlsx";
    		List<Client> list = Lists.newArrayList(); 
    		new ExportExcel("客户数据", Client.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/client/client/?repage";
    }
	

}