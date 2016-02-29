/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.project.web;

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
import com.jeeplus.modules.project.entity.ShippingProject;
import com.jeeplus.modules.project.service.ShippingProjectService;

/**
 * 船运项目Controller
 * @author shipping
 * @version 2016-02-29
 */
@Controller
@RequestMapping(value = "${adminPath}/project/shippingProject")
public class ShippingProjectController extends BaseController {

	@Autowired
	private ShippingProjectService shippingProjectService;
	
	@ModelAttribute
	public ShippingProject get(@RequestParam(required=false) String id) {
		ShippingProject entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = shippingProjectService.get(id);
		}
		if (entity == null){
			entity = new ShippingProject();
		}
		return entity;
	}
	
	/**
	 * 项目列表页面
	 */
	@RequiresPermissions("project:shippingProject:list")
	@RequestMapping(value = {"list", ""})
	public String list(ShippingProject shippingProject, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShippingProject> page = shippingProjectService.findPage(new Page<ShippingProject>(request, response), shippingProject); 
		model.addAttribute("page", page);
		return "modules/project/shippingProjectList";
	}

	/**
	 * 查看，增加，编辑项目表单页面
	 */
	@RequiresPermissions(value={"project:shippingProject:view","project:shippingProject:add","project:shippingProject:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(ShippingProject shippingProject, Model model) {
		model.addAttribute("shippingProject", shippingProject);
		return "modules/project/shippingProjectForm";
	}

	/**
	 * 保存项目
	 */
	@RequiresPermissions(value={"project:shippingProject:add","project:shippingProject:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(ShippingProject shippingProject, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, shippingProject)){
			return form(shippingProject, model);
		}
		shippingProjectService.save(shippingProject);
		addMessage(redirectAttributes, "保存项目成功");
		return "redirect:"+Global.getAdminPath()+"/project/shippingProject/?repage";
	}
	
	/**
	 * 删除项目
	 */
	@RequiresPermissions("project:shippingProject:del")
	@RequestMapping(value = "delete")
	public String delete(ShippingProject shippingProject, RedirectAttributes redirectAttributes) {
		shippingProjectService.delete(shippingProject);
		addMessage(redirectAttributes, "删除项目成功");
		return "redirect:"+Global.getAdminPath()+"/project/shippingProject/?repage";
	}
	
	/**
	 * 批量删除项目
	 */
	@RequiresPermissions("project:shippingProject:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			shippingProjectService.delete(shippingProjectService.get(id));
		}
		addMessage(redirectAttributes, "删除项目成功");
		return "redirect:"+Global.getAdminPath()+"/project/shippingProject/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("project:shippingProject:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(ShippingProject shippingProject, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "项目"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<ShippingProject> page = shippingProjectService.findPage(new Page<ShippingProject>(request, response, -1), shippingProject);
    		new ExportExcel("项目", ShippingProject.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出项目记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/project/shippingProject/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("project:shippingProject:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<ShippingProject> list = ei.getDataList(ShippingProject.class);
			for (ShippingProject shippingProject : list){
				shippingProjectService.save(shippingProject);
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条项目记录");
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入项目失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/project/shippingProject/?repage";
    }
	
	/**
	 * 下载导入项目数据模板
	 */
	@RequiresPermissions("project:shippingProject:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "项目数据导入模板.xlsx";
    		List<ShippingProject> list = Lists.newArrayList(); 
    		new ExportExcel("项目数据", ShippingProject.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/project/shippingProject/?repage";
    }
	

}