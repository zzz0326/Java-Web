/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/lruijun/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.oa_forms.service;

import com.google.common.collect.Maps;
import com.jsite.common.lang.StringUtils;
import com.jsite.common.service.CrudService;
import com.jsite.modules.flowable.service.FlowTaskService;
import com.jsite.modules.flowable.utils.FlowableUtils;
import com.jsite.modules.oa_forms.dao.OaFormsUploadProcessDao;
import com.jsite.modules.oa_forms.entity.OaFormsList;
import com.jsite.modules.oa_forms.entity.OaFormsUploadProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 报表流程Service
 * @author liuruijun
 * @version 2019-04-19
 */
@Service
@Transactional(readOnly = true)
public class OaFormsUploadProcessService extends CrudService<OaFormsUploadProcessDao, OaFormsUploadProcess> {

	@Autowired
	private FlowTaskService actTaskService;

	@Transactional(readOnly = false)
	public String save(OaFormsUploadProcess oaFormsUploadProcess, Map<String, Object> variables) {
		String businessTable = FlowableUtils.getBusinessTable("oaFormsUploadProcess");
		if (StringUtils.isBlank(businessTable)) {
		    return "流程启动失败，请先设置业务表";
        }

		// 申请发起 保存业务数据
		if (StringUtils.isBlank(oaFormsUploadProcess.getId())){
			oaFormsUploadProcess.preInsert();
			dao.insert(oaFormsUploadProcess);

			String procIns = actTaskService.startProcess("oaFormsUploadProcess", businessTable, oaFormsUploadProcess.getId(), variables);

			logger.debug("流程已启动，流程ID："+ procIns);

			return "流程已启动，流程ID："+ procIns;
		}

		// 重新编辑申请
		else{
			oaFormsUploadProcess.preUpdate();
			dao.update(oaFormsUploadProcess);

			oaFormsUploadProcess.getAct().setComment(oaFormsUploadProcess.getAct().isPass()?"[重申] ":"[销毁] " + oaFormsUploadProcess.getAct().getComment());

			// 完成流程任务
			variables.put("auditPass", oaFormsUploadProcess.getAct().isPass());
			actTaskService.complete(oaFormsUploadProcess.getAct().getTaskId(), oaFormsUploadProcess.getAct().getProcInsId(), oaFormsUploadProcess.getAct().getComment(), variables);

            return "流程已" + (oaFormsUploadProcess.getAct().isPass()?"[重申] ":"[销毁] ");
		}
	}

	public void saveFile(OaFormsList formsList){

	}

	@Transactional(readOnly = false)
	public String uploadFile(OaFormsUploadProcess oaFormsUploadProcess) {

		// 设置意见
		// oaFormsUploadProcess.getAct().setComment((oaFormsUploadProcess.getAct().isPass()?"[同意] ":"[驳回] ")+oaFormsUploadProcess.getAct().getComment());
		// oaFormsUploadProcess.preUpdate();

		// 对不同环节的业务逻辑进行操作
		String taskDefKey = oaFormsUploadProcess.getAct().getTaskDefKey();
        // 流程变量，设置分支条件的变量值
		Map<String, Object> vars = Maps.newHashMap();

        // TODO taskDefKey对应流程XML文件中userTask id
        // TODO 下面的审批环节需要根据不同的业务需求进行修改，可以参考请假流程Demo
		// 上传环节
		if ("upload_data_form".equals(taskDefKey)){ // 财务部
			oaFormsUploadProcess.uploadFormExcel();
			//dao.setUploadedFile(oaFormsUploadProcess);
		}
		else if ("upload_farm_form".equals(taskDefKey)){ // 车间
			oaFormsUploadProcess.uploadFormExcel();
			//dao.setUploadedFile(oaFormsUploadProcess);
		}
		else if ("upload_career_form".equals(taskDefKey)){ // 事业部
			oaFormsUploadProcess.uploadFormExcel();
			//dao.setUploadedFile(oaFormsUploadProcess);
		}
		else if ("upload_sales_form".equals(taskDefKey)){ // 销售部
			oaFormsUploadProcess.uploadFormExcel();
			//dao.setUploadedFile(oaFormsUploadProcess);
		}

		// 未知环节，直接返回
		else{
			return "未知环节";
		}

        vars.put("auditPass", oaFormsUploadProcess.getAct().isPass());
		// 提交流程任务

		actTaskService.complete(oaFormsUploadProcess.getAct().getTaskId(), oaFormsUploadProcess.getAct().getProcInsId(), oaFormsUploadProcess.getAct().getComment(), vars);

		return "处理成功";
	}

}