/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.test.web;

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
import com.jeeplus.modules.test.entity.Testt;
import com.jeeplus.modules.test.service.TesttService;

/**
 * testController
 * @author test
 * @version 2016-02-23
 */
@Controller
@RequestMapping(value = "${adminPath}/test/testt")
public class TesttController extends BaseController {

	@Autowired
	private TesttService testtService;
	
	@ModelAttribute
	public Testt get(@RequestParam(required=false) String id) {
		Testt entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = testtService.get(id);
		}
		if (entity == null){
			entity = new Testt();
		}
		return entity;
	}
	
	/**
	 * test列表页面
	 */
	@RequiresPermissions("test:testt:list")
	@RequestMapping(value = {"list", ""})
	public String list(Testt testt, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Testt> page = testtService.findPage(new Page<Testt>(request, response), testt); 
		model.addAttribute("page", page);
		return "modules/test/testtList";
	}

	/**
	 * 查看，增加，编辑test表单页面
	 */
	@RequiresPermissions(value={"test:testt:view","test:testt:add","test:testt:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(Testt testt, Model model) {
		model.addAttribute("testt", testt);
		return "modules/test/testtForm";
	}

	/**
	 * 保存test
	 */
	@RequiresPermissions(value={"test:testt:add","test:testt:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(Testt testt, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, testt)){
			return form(testt, model);
		}
		testtService.save(testt);
		addMessage(redirectAttributes, "保存test成功");
		return "redirect:"+Global.getAdminPath()+"/test/testt/?repage";
	}
	
	/**
	 * 删除test
	 */
	@RequiresPermissions("test:testt:del")
	@RequestMapping(value = "delete")
	public String delete(Testt testt, RedirectAttributes redirectAttributes) {
		testtService.delete(testt);
		addMessage(redirectAttributes, "删除test成功");
		return "redirect:"+Global.getAdminPath()+"/test/testt/?repage";
	}
	
	/**
	 * 批量删除test
	 */
	@RequiresPermissions("test:testt:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			testtService.delete(testtService.get(id));
		}
		addMessage(redirectAttributes, "删除test成功");
		return "redirect:"+Global.getAdminPath()+"/test/testt/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("test:testt:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(Testt testt, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "test"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Testt> page = testtService.findPage(new Page<Testt>(request, response, -1), testt);
    		new ExportExcel("test", Testt.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出test记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/test/testt/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("test:testt:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Testt> list = ei.getDataList(Testt.class);
			for (Testt testt : list){
				testtService.save(testt);
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条test记录");
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入test失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/test/testt/?repage";
    }
	
	/**
	 * 下载导入test数据模板
	 */
	@RequiresPermissions("test:testt:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "test数据导入模板.xlsx";
    		List<Testt> list = Lists.newArrayList(); 
    		new ExportExcel("test数据", Testt.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/test/testt/?repage";
    }
	

}