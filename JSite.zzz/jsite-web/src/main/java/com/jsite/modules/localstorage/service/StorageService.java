/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/lruijun/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.localstorage.service;

import com.google.common.collect.Maps;
import com.jsite.common.lang.StringUtils;
import com.jsite.common.persistence.Page;
import com.jsite.common.service.CrudService;
import com.jsite.modules.flowable.service.FlowTaskService;
import com.jsite.modules.flowable.utils.FlowableUtils;
import com.jsite.modules.storage.entity.StorageGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

import com.jsite.modules.localstorage.entity.Storage;
import com.jsite.modules.localstorage.dao.StorageDao;

/**
 * 111Service
 * @author liuruijun
 * @version 2019-04-24
 */
@Service
@Transactional(readOnly = true)
public class StorageService extends CrudService<StorageDao, Storage> {

	public Page<Storage> findPage(Page<Storage> page, Storage storage) {
		return super.findPage(page, storage);
	}

	@Autowired
	private FlowTaskService actTaskService;

	@Transactional(readOnly = false)
	public String save(Storage storage, Map<String, Object> variables) {
		String businessTable = FlowableUtils.getBusinessTable("storage");
		if (StringUtils.isBlank(businessTable)) {
		    return "流程启动失败，请先设置业务表";
        }

		// 申请发起 保存业务数据
		if (StringUtils.isBlank(storage.getId())){
			storage.preInsert();
			dao.insert(storage);

			String procIns = actTaskService.startProcess("storage", businessTable, storage.getId(), variables);

			logger.debug("流程已启动，流程ID："+ procIns);

			return "流程已启动，流程ID："+ procIns;
		}

		// 重新编辑申请
		else{
			storage.preUpdate();
			dao.update(storage);

			storage.getAct().setComment(storage.getAct().isPass()?"[重申] ":"[销毁] " + storage.getAct().getComment());

			// 完成流程任务
			variables.put("auditPass", storage.getAct().isPass());
			actTaskService.complete(storage.getAct().getTaskId(), storage.getAct().getProcInsId(), storage.getAct().getComment(), variables);

            return "流程已" + (storage.getAct().isPass()?"[重申] ":"[销毁] ");
		}
	}

	@Transactional(readOnly = false)
	public String auditSave(Storage storage) {

		// 设置意见
		storage.getAct().setComment((storage.getAct().isPass()?"[同意] ":"[驳回] ")+storage.getAct().getComment());
		storage.preUpdate();

		// 对不同环节的业务逻辑进行操作
		String taskDefKey = storage.getAct().getTaskDefKey();
        // 流程变量，设置分支条件的变量值
		Map<String, Object> vars = Maps.newHashMap();

        // TODO taskDefKey对应流程XML文件中userTask id
        // TODO 下面的审批环节需要根据不同的业务需求进行修改，可以参考请假流程Demo
		// 审批环节1
		if ("audit1".equals(taskDefKey)){
//			leave.setDeptLeadText(leave.getAct().getComment());
//			dao.updateLeadText(leave);
		}
		// 审批环节2
		else if ("audit2".equals(taskDefKey)){
//			leave.setHrText(leave.getAct().getComment());
//			dao.updateHRText(leave);
		}
		// 重新修改
		else if ("modifyApply".equals(taskDefKey)){
//			dao.update(storage);
		}

		// 未知环节，直接返回
		else{
			return "未知环节";
		}

        vars.put("auditPass", storage.getAct().isPass());
		// 提交流程任务

		actTaskService.complete(storage.getAct().getTaskId(), storage.getAct().getProcInsId(), storage.getAct().getComment(), vars);

		return "处理成功";
	}

}