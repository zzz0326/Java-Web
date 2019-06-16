/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/lruijun/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.baobiao.service;

import com.google.common.collect.Maps;
import com.jsite.common.lang.StringUtils;
import com.jsite.common.service.CrudService;
import com.jsite.modules.flowable.service.FlowTaskService;
import com.jsite.modules.flowable.utils.FlowableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

import com.jsite.modules.baobiao.entity.OaBaobiao;
import com.jsite.modules.baobiao.dao.OaBaobiaoDao;

/**
 * 报表管理Service
 * @author liuruijun
 * @version 2019-04-17
 */
@Service
@Transactional(readOnly = true)
public class OaBaobiaoService extends CrudService<OaBaobiaoDao, OaBaobiao> {

	@Autowired
	private FlowTaskService actTaskService;

	@Transactional(readOnly = false)
	public String save(OaBaobiao oaBaobiao, Map<String, Object> variables) {
		String businessTable = FlowableUtils.getBusinessTable("oaBaobiao");
		if (StringUtils.isBlank(businessTable)) {
		    return "流程启动失败，请先设置业务表";
        }

		// 申请发起 保存业务数据
		if (StringUtils.isBlank(oaBaobiao.getId())){
			oaBaobiao.preInsert();
			dao.insert(oaBaobiao);

			String procIns = actTaskService.startProcess("oaBaobiao", businessTable, oaBaobiao.getId(), variables);

			logger.debug("流程已启动，流程ID："+ procIns);

			return "流程已启动，流程ID："+ procIns;
		}

		// 重新编辑申请
		else{
			oaBaobiao.preUpdate();
			dao.update(oaBaobiao);

			oaBaobiao.getAct().setComment(oaBaobiao.getAct().isPass()?"[重申] ":"[销毁] " + oaBaobiao.getAct().getComment());

			// 完成流程任务
			variables.put("auditPass", oaBaobiao.getAct().isPass());
			actTaskService.complete(oaBaobiao.getAct().getTaskId(), oaBaobiao.getAct().getProcInsId(), oaBaobiao.getAct().getComment(), variables);

            return "流程已" + (oaBaobiao.getAct().isPass()?"[重申] ":"[销毁] ");
		}
	}

	@Transactional(readOnly = false)
	public String auditSave(OaBaobiao oaBaobiao) {

		// 设置意见
		oaBaobiao.getAct().setComment((oaBaobiao.getAct().isPass()?"[同意] ":"[驳回] ")+oaBaobiao.getAct().getComment());
		oaBaobiao.preUpdate();

		// 对不同环节的业务逻辑进行操作
		String taskDefKey = oaBaobiao.getAct().getTaskDefKey();
        // 流程变量，设置分支条件的变量值
		Map<String, Object> vars = Maps.newHashMap();

        // TODO taskDefKey对应流程XML文件中userTask id
        // TODO 下面的审批环节需要根据不同的业务需求进行修改，可以参考请假流程Demo
		// 审批环节1
		if ("deptLeaderAudit".equals(taskDefKey)){
			oaBaobiao.setDeptLeadText(oaBaobiao.getAct().getComment());
			dao.updateLeadText(oaBaobiao);
		}
		// 审批环节2
		else if ("hrAudit".equals(taskDefKey)){
			oaBaobiao.setHrText(oaBaobiao.getAct().getComment());
			dao.updateHRText(oaBaobiao);
		}
		// 重新修改
		else if ("modifyApply".equals(taskDefKey)){
			dao.update(oaBaobiao);
		}

		// 未知环节，直接返回
		else{
			return "未知环节";
		}

        vars.put("auditPass", oaBaobiao.getAct().isPass());
		// 提交流程任务

		actTaskService.complete(oaBaobiao.getAct().getTaskId(), oaBaobiao.getAct().getProcInsId(), oaBaobiao.getAct().getComment(), vars);

		return "处理成功";
	}

}