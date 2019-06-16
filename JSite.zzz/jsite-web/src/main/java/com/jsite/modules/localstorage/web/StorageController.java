/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/lruijun/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.localstorage.web;

import com.google.common.collect.Maps;
import com.jsite.common.config.Global;
import com.jsite.common.lang.StringUtils;
import com.jsite.common.persistence.Page;
import com.jsite.common.web.BaseController;
import com.jsite.modules.localstorage.dao.StorageDao;
import com.jsite.modules.storage.entity.StorageGet;

import com.jsite.modules.storage.record.dao.StorageRecordDao;
import com.jsite.modules.storage.record.entity.StorageRecord;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import com.jsite.modules.localstorage.entity.Storage;
import com.jsite.modules.localstorage.service.StorageService;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * 111Controller
 * @author liuruijun
 * @version 2019-04-24
 */
@Controller
@RequestMapping(value = "${adminPath}/localstorage/storage")
public class StorageController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	StorageDao storageDao;

	@Resource
	StorageRecordDao storageRecordDao;

	@Autowired
	protected StorageService storageService;

	@ModelAttribute
	public Storage get(@RequestParam(required=false) String id){
		Storage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = storageService.get(id);
		}
		if (entity == null){
			entity = new Storage();
		}
		return entity;
	}

	@RequiresPermissions("localstorage:storage:view")
	@RequestMapping(value = {"form"})
	public String form(Storage storage, Model model) {
		String view = "storageForm";
		if (StringUtils.isNotBlank(storage.getId())){
			String taskDefKey = storage.getAct().getTaskDefKey();

			// 查看工单
			if (storage.getAct().isFinishTask()) {
				view = "storageView";
			}

            // 调整申请
			else if ("modifyApply".equals(taskDefKey)) {
				view = "storageForm";
			}

			// 审核环节
			else {
				view = "storageAudit";
			}
		}

		model.addAttribute("storage", storage);
		return "modules/localstorage/" + view;
	}

	@RequiresPermissions("localstorage:storage:edit")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public String save(Storage storage) {
		try {
			Map<String, Object> variables = Maps.newHashMap();

			String message = storageService.save(storage, variables);

			return renderResult(Global.TRUE, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return renderResult(Global.FALSE, "系统内部错误！");
	}

	@RequiresPermissions("localstorage:storage:edit")
	@RequestMapping(value = "saveAudit")
	@ResponseBody
	public String saveAudit(Storage storage, Model model) {
		String message = storageService.auditSave(storage);
		return renderResult(Global.TRUE, message);
	}

	@RequiresPermissions("localstorage:storage:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Storage> listData(Storage storage, javax.servlet.http.HttpServletRequest request, HttpServletResponse response) {
		Page<Storage> page = storageService.findPage(new Page<>(request, response), storage);
		return page;
	}

	@RequiresPermissions("localstorage:storage:view")
	@RequestMapping(value = "saveNeed")
	@ResponseBody
	public void saveNeed(Storage storage, HttpServletResponse response){
	        storageDao.update(storage);
	}


    @RequiresPermissions("localstorage:storage:view")
    @RequestMapping(value = "recoder")
    @ResponseBody
    public void recoder(StorageRecord storageRecord){
        storageRecordDao.insert(storageRecord);
    }

}