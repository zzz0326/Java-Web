/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/lruijun/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.oa_forms.web;

import com.google.common.collect.Maps;
import com.jsite.common.config.Global;
import com.jsite.common.lang.StringUtils;
import com.jsite.common.utils.UploadUtils4;
import com.jsite.common.web.BaseController;
import com.jsite.modules.oa_forms.entity.OaFormsList;
import com.jsite.modules.oa_forms.entity.OaFormsUploadProcess;
import com.jsite.modules.oa_forms.service.OaFormsListService;
import com.jsite.modules.oa_forms.service.OaFormsUploadProcessService;
import com.jsite.modules.sys.entity.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 报表流程Controller
 * @author liuruijun
 * @version 2019-04-19
 */
@Controller
@RequestMapping(value = "${adminPath}/oa_forms/oaFormsUploadProcess")
public class OaFormsUploadProcessController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected OaFormsUploadProcessService oaFormsUploadProcessService;

	@Autowired
    protected OaFormsListService oaFormsListService;

	@ModelAttribute
	public OaFormsUploadProcess get(@RequestParam(required=false) String id){
		OaFormsUploadProcess entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaFormsUploadProcessService.get(id);
		}
		if (entity == null){
			entity = new OaFormsUploadProcess();
		}
		return entity;
	}

	@RequiresPermissions("oa_forms:oaFormsUploadProcess:view")
	@RequestMapping(value = {"form"})
	public String form(OaFormsUploadProcess oaFormsUploadProcess, Model model) {
		String view = "oaFormsUploadProcessForm";
		if (StringUtils.isNotBlank(oaFormsUploadProcess.getId())) {
			String taskDefKey = oaFormsUploadProcess.getAct().getTaskDefKey();

			// 查看工单
			if (oaFormsUploadProcess.getAct().isFinishTask()) {
				view = "oaFormsUploadProcessView";
			}
			// 调整申请
			else if ("modifyApply".equals(taskDefKey)) {
				view = "oaFormsUploadProcessForm";
			}
			// 上传环节
			else {
				view = "oaFormsUploadProcessUpload";
			}
		}

		model.addAttribute("oaFormsUploadProcess", oaFormsUploadProcess);
		return "modules/oa_forms/" + view;
	}


	@RequiresPermissions("oa_forms:oaFormsUploadProcess:edit")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public String save(OaFormsUploadProcess oaFormsUploadProcess) {
		try {
			Map<String, Object> variables = Maps.newHashMap();

			String message = oaFormsUploadProcessService.save(oaFormsUploadProcess, variables);

			return renderResult(Global.TRUE, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return renderResult(Global.FALSE, "系统内部错误！");
	}

	@RequiresPermissions("oa_forms:oaFormsUploadProcess:edit")
	@RequestMapping(value = "saveAudit")
	@ResponseBody
	public String saveAudit(OaFormsUploadProcess oaFormsUploadProcess, Model model) {
		String message = oaFormsUploadProcessService.uploadFile(oaFormsUploadProcess);
		return renderResult(Global.TRUE, message);
	}

	@RequiresPermissions("oa_forms:oaFormsUploadProcess:edit")
	@RequestMapping(value = "uploadFile")
	public String uploadFile(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/oa_forms/oa_formFileUpload";
	}

	@RequiresPermissions("oa_forms:oaFormsUploadProcess:edit")
	@RequestMapping(value = "up", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String up(OaFormsUploadProcess oaFormsUploadProcess, User user, HttpServletRequest request) {
		List<UploadUtils4.UploadResult> result = UploadUtils4.getInstance().uploadFile(request);

		for (UploadUtils4.UploadResult uploadResult : result) {
			logger.info("上传结果----uploadResult----" + uploadResult.toString());

			if (!uploadResult.err) {
                OaFormsList entity = new OaFormsList();
				if (StringUtils.isBlank(uploadResult.chunk) || uploadResult.merged) {// 写入数据库

					entity.setFilename(uploadResult.fileName);
					entity.setFilesize(uploadResult.fields.get("size"));
					entity.setPath(uploadResult.fileRltvPath);
					entity.setUploadtime(new Date());
                    String taskDefKey = oaFormsUploadProcess.getAct().getTaskDefKey();
					if ("upload_data_form".equals(taskDefKey)) entity.setFormType("DATAF");
					else entity.setFormType("NORML");
					entity.setUploader("1");
                    oaFormsListService.save(entity);

					return renderResult(Global.TRUE, "上传成功");
				}

			}
		}

		return renderResult(Global.FALSE, "上传失败");
	}
}