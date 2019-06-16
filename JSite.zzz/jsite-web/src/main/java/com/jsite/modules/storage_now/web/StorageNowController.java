/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/baseweb/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.storage_now.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jsite.common.config.Global;
import com.jsite.common.persistence.Page;
import com.jsite.common.web.BaseController;
import com.jsite.common.lang.StringUtils;
import com.jsite.modules.storage_now.entity.StorageNow;
import com.jsite.modules.storage_now.service.StorageNowService;

/**
 * 查询现有库存Controller
 * @author zzz
 * @version 2019-05-21
 */
@Controller
@RequestMapping(value = "${adminPath}/storage_now/storageNow")
public class StorageNowController extends BaseController {

	@Autowired
	private StorageNowService storageNowService;
	
	@ModelAttribute
	public StorageNow get(@RequestParam(required=false) String id) {
		StorageNow entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = storageNowService.get(id);
		}
		if (entity == null){
			entity = new StorageNow();
		}
		return entity;
	}
	
	@RequiresPermissions("storage_now:storageNow:view")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/storage_now/storageNowList";
	}
	
	@RequiresPermissions("storage_now:storageNow:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<StorageNow> listData(StorageNow storageNow, HttpServletRequest request, HttpServletResponse response) {
		Page<StorageNow> page = storageNowService.findPage(new Page<>(request, response), storageNow);
		return page;
	}

	@RequiresPermissions("storage_now:storageNow:view")
	@RequestMapping(value = "form")
	public String form(StorageNow storageNow, Model model) {
		model.addAttribute("storageNow", storageNow);
		return "modules/storage_now/storageNowForm";
	}

	@RequiresPermissions("storage_now:storageNow:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public String save(StorageNow storageNow) {
		storageNowService.save(storageNow);
		return renderResult(Global.TRUE, "保存成功成功");
	}
	
	@RequiresPermissions("storage_now:storageNow:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(StorageNow storageNow) {
		storageNowService.delete(storageNow);
		return renderResult(Global.TRUE, "删除成功成功");
	}

}