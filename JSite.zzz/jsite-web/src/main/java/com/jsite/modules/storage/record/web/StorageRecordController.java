/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/baseweb/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.storage.record.web;

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
import com.jsite.modules.storage.record.entity.StorageRecord;
import com.jsite.modules.storage.record.service.StorageRecordService;

/**
 * 仓储记录Controller
 * @author zzz
 * @version 2019-05-19
 */
@Controller
@RequestMapping(value = "${adminPath}/storage.record/storageRecord")
public class StorageRecordController extends BaseController {

	@Autowired
	private StorageRecordService storageRecordService;
	
	@ModelAttribute
	public StorageRecord get(@RequestParam(required=false) String id) {
		StorageRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = storageRecordService.get(id);
		}
		if (entity == null){
			entity = new StorageRecord();
		}
		return entity;
	}
	
	@RequiresPermissions("storage.record:storageRecord:view")
	@RequestMapping(value = {"list", ""})
	//默认和/list都访问下面这个页面
	public String list() {
		return "modules/storage/record/storageRecordList";
	}
	//路径里的点要用/来代替
	
	@RequiresPermissions("storage.record:storageRecord:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<StorageRecord> listData(StorageRecord storageRecord, HttpServletRequest request, HttpServletResponse response) {
		Page<StorageRecord> page = storageRecordService.findPage(new Page<>(request, response), storageRecord);
		return page;
	}

	@RequiresPermissions("storage.record:storageRecord:view")
	@RequestMapping(value = "form")
	public String form(StorageRecord storageRecord, Model model) {
		model.addAttribute("storageRecord", storageRecord);
		return "modules/storage.record/storageRecordForm";
	}

	@RequiresPermissions("storage.record:storageRecord:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public String save(StorageRecord storageRecord) {
		storageRecordService.save(storageRecord);
		return renderResult(Global.TRUE, "保存记录保存成功成功");
	}
	
	@RequiresPermissions("storage.record:storageRecord:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(StorageRecord storageRecord) {
		storageRecordService.delete(storageRecord);
		return renderResult(Global.TRUE, "删除记录保存成功成功");
	}



}