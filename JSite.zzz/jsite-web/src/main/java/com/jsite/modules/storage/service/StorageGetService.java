/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/lruijun/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.storage.service;

import com.google.common.collect.Maps;
import com.jsite.common.lang.StringUtils;
import com.jsite.common.persistence.Page;
import com.jsite.common.service.CrudService;
import com.jsite.modules.flowable.service.FlowTaskService;
import com.jsite.modules.flowable.utils.FlowableUtils;
import com.jsite.modules.storage.record.dao.StorageRecordDao;
import com.jsite.modules.storage.record.entity.StorageRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jsite.modules.storage.entity.StorageGet;
import com.jsite.modules.storage.dao.StorageGetDao;

import javax.annotation.Resource;

/**
 * 仓储流程Service
 * @author liuruijun
 * @version 2019-04-22
 */
@Service
@Transactional(readOnly = true)
public class StorageGetService extends CrudService<StorageGetDao, StorageGet> {

	public Page<StorageGet> findPage(Page<StorageGet> page, StorageGet storageGet) {
		return super.findPage(page, storageGet);
	}

	@Resource
	StorageRecordDao storageRecordDao;

	@Autowired
	private FlowTaskService actTaskService;

	@Transactional(readOnly = false)
	public String save(StorageGet storageGet, Map<String, Object> variables) {
		String businessTable = FlowableUtils.getBusinessTable("storageGet");
		if (StringUtils.isBlank(businessTable)) {
		    return "流程启动失败，请先设置业务表";
        }

		// 申请发起 保存业务数据
		if (StringUtils.isBlank(storageGet.getId())){
			storageGet.preInsert();
			dao.insert(storageGet);

			String procIns = actTaskService.startProcess("storageGet", businessTable, storageGet.getId(), variables);//流程编号
			//这是为了调用这个函数 才这样写的 流程id 可以直接从storageGet.getprocinsid()获取
			Date time = storageGet.getCreateDate();//获取开始时间
			List<StorageRecord> list = storageRecordDao.findList(new StorageRecord());
			for(int i = 0;i<list.size();i++){
				StorageRecord storageRecord = list.get(i);
				if(storageRecord.getProcessId()==null){
					storageRecord.setProcessId(procIns);
					storageRecord.setStarttiem(time);
					storageRecordDao.update(storageRecord);
				}
			}
			StorageRecord storageRecord;

			logger.debug("流程已启动，流程ID："+ procIns);

			return "流程已启动，流程ID："+ procIns;
		}

		// 重新编辑申请
		else{
			storageGet.preUpdate();
			dao.update(storageGet);

			storageGet.getAct().setComment(storageGet.getAct().isPass()?"[重申] ":"[销毁] " + storageGet.getAct().getComment());

			// 完成流程任务
			variables.put("auditPass", storageGet.getAct().isPass());
			actTaskService.complete(storageGet.getAct().getTaskId(), storageGet.getAct().getProcInsId(), storageGet.getAct().getComment(), variables);

            return "流程已" + (storageGet.getAct().isPass()?"[重申] ":"[销毁] ");
		}
	}

	@Transactional(readOnly = false)
	public String auditSave(StorageGet storageGet) {

		// 设置意见
		storageGet.getAct().setComment((storageGet.getAct().isPass()?"[同意] ":"[驳回] ")+storageGet.getAct().getComment());
		storageGet.preUpdate();

		// 对不同环节的业务逻辑进行操作
		String taskDefKey = storageGet.getAct().getTaskDefKey();
        // 流程变量，设置分支条件的变量值
		Map<String, Object> vars = Maps.newHashMap();

        // TODO taskDefKey对应流程XML文件中userTask id
        // TODO 下面的审批环节需要根据不同的业务需求进行修改，可以参考请假流程Demo
		// 审批环节1
		if ("Workshop_leader".equals(taskDefKey)){//车间主任
			storageGet.uploadFormExcel();
		}
		// 审批环节2
		else if ("audit2".equals(taskDefKey)){
//			leave.setHrText(leave.getAct().getComment());
//			dao.updateHRText(leave);
		}
		// 重新修改
		else if ("modifyApply".equals(taskDefKey)){//发起者
			storageGet.uploadFormExcel();
		}

		// 未知环节，直接返回
		else{
			return "未知环节";
		}

        vars.put("auditPass", storageGet.getAct().isPass());
		// 提交流程任务

		actTaskService.complete(storageGet.getAct().getTaskId(), storageGet.getAct().getProcInsId(), storageGet.getAct().getComment(), vars);

		return "处理成功";
	}

}