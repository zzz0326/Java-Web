/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/baseweb/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.storage_diary.web;

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
import com.jsite.modules.storage_diary.entity.StorageDiary;
import com.jsite.modules.storage_diary.service.StorageDiaryService;

/**
 * 仓储流水Controller
 * @author zzz
 * @version 2019-05-21
 */
@Controller
@RequestMapping(value = "${adminPath}/storage_diary/storageDiary")
public class StorageDiaryController extends BaseController {

	@Autowired
	private StorageDiaryService storageDiaryService;
	
	@ModelAttribute
	public StorageDiary get(@RequestParam(required=false) String id) {
		StorageDiary entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = storageDiaryService.get(id);
		}
		if (entity == null){
			entity = new StorageDiary();
		}
		return entity;
	}
	
	@RequiresPermissions("storage_diary:storageDiary:view")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/storage_diary/storageDiaryList";
	}
	
	@RequiresPermissions("storage_diary:storageDiary:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<StorageDiary> listData(StorageDiary storageDiary, HttpServletRequest request, HttpServletResponse response) {
		Page<StorageDiary> page = storageDiaryService.findPage(new Page<>(request, response), storageDiary);
		return page;
	}

	@RequiresPermissions("storage_diary:storageDiary:view")
	@RequestMapping(value = "form")
	public String form(StorageDiary storageDiary, Model model) {
		model.addAttribute("storageDiary", storageDiary);
		return "modules/storage_diary/storageDiaryForm";
	}

	@RequiresPermissions("storage_diary:storageDiary:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public String save(StorageDiary storageDiary) {
		storageDiaryService.save(storageDiary);
		return renderResult(Global.TRUE, "保存出入库流水账成功");
	}
	
	@RequiresPermissions("storage_diary:storageDiary:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(StorageDiary storageDiary) {
		storageDiaryService.delete(storageDiary);
		return renderResult(Global.TRUE, "删除出入库流水账成功");
	}

}