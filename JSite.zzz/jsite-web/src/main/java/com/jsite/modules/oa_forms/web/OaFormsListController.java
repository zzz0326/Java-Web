/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/baseweb/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.oa_forms.web;

import com.jsite.common.config.Global;
import com.jsite.common.lang.StringUtils;
import com.jsite.common.persistence.Page;
import com.jsite.common.utils.UploadUtils4;
import com.jsite.common.web.BaseController;
import com.jsite.modules.oa_forms.entity.OaFormsList;
import com.jsite.modules.oa_forms.service.OaFormsListService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 报表查看Controller
 * @author Dora
 * @version 2019-04-20
 */
@Controller
@RequestMapping(value = "${adminPath}/oa_forms/oaFormsList")
public class OaFormsListController extends BaseController {

	@Autowired
	private OaFormsListService oaFormsListService;
	
	@ModelAttribute
	public OaFormsList get(@RequestParam(required=false) String id) {
		OaFormsList entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaFormsListService.get(id);
		}
		if (entity == null){
			entity = new OaFormsList();
		}
		return entity;
	}
	
	@RequiresPermissions("oa_forms:oaFormsList:view")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/oa_forms/oaFormsListList";
	}
	
	@RequiresPermissions("oa_forms:oaFormsList:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<OaFormsList> listData(OaFormsList oaFormsList, HttpServletRequest request, HttpServletResponse response) {
		Page<OaFormsList> page = oaFormsListService.findPage(new Page<>(request, response), oaFormsList);
		return page;
	}

	@RequiresPermissions("oa_forms:oaFormsList:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public String save(OaFormsList oaFormsList) {
		oaFormsListService.save(oaFormsList);
		return renderResult(Global.TRUE, "保存报表信息成功");
	}
	
	@RequiresPermissions("oa_forms:oaFormsList:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(OaFormsList oaFormsList) {
		oaFormsListService.delete(oaFormsList);
		return renderResult(Global.TRUE, "删除报表成功");
	}

	@RequiresPermissions("oa_forms:oaFormsList:view")
	@RequestMapping(value = "download")
	public void download(OaFormsList oaFormsList, HttpServletRequest request, HttpServletResponse response) {
		UploadUtils4.getInstance().downloadFile(oaFormsList.getPath(), request, response);
	}





}