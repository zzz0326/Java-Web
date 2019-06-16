/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/baseweb/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.oa_forms.web;

import com.jsite.common.config.Global;
import com.jsite.common.lang.StringUtils;
import com.jsite.common.persistence.Page;
import com.jsite.common.web.BaseController;
import com.jsite.modules.oa_forms.entity.FormsList;
import com.jsite.modules.oa_forms.service.FormsListService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 报表查看Controller
 * @author Dora
 * @version 2019-04-16
 */
@Controller
@RequestMapping(value = "${adminPath}/oa_forms/formsList")
public class FormsListController extends BaseController {

	@Autowired
	private FormsListService formsListService;
	
	@ModelAttribute
	public FormsList get(@RequestParam(required=false) String id) {
		FormsList entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = formsListService.get(id);
		}
		if (entity == null){
			entity = new FormsList();
		}
		return entity;
	}
	
	@RequiresPermissions("oa_forms:formsList:view")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/oa_forms/formsListList";
	}
	
	@RequiresPermissions("oa_forms:formsList:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<FormsList> listData(FormsList formsList, HttpServletRequest request, HttpServletResponse response) {
		Page<FormsList> page = formsListService.findPage(new Page<>(request, response), formsList);
		return page;
	}

	@RequiresPermissions("oa_forms:formsList:view")
	@RequestMapping(value = "form")
	public String form(FormsList formsList, Model model) {
		model.addAttribute("formsList", formsList);
		return "modules/oa_forms/formsListForm";
	}

	@RequiresPermissions("oa_forms:formsList:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public String save(FormsList formsList) {
		formsListService.save(formsList);
		return renderResult(Global.TRUE, "保存报表信息成功");
	}
	
	@RequiresPermissions("oa_forms:formsList:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(FormsList formsList) {
		formsListService.delete(formsList);
		return renderResult(Global.TRUE, "删除报表信息成功");
	}




}