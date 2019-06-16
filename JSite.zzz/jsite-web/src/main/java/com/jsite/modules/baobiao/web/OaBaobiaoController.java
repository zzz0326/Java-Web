/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/lruijun/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.baobiao.web;

import com.google.common.collect.Maps;
import com.jsite.common.config.Global;
import com.jsite.common.lang.StringUtils;
import com.jsite.common.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import com.jsite.modules.baobiao.entity.OaBaobiao;
import com.jsite.modules.baobiao.service.OaBaobiaoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 报表管理Controller
 * @author liuruijun
 * @version 2019-04-17
 */
@Controller
@RequestMapping(value = "${adminPath}/baobiao/oaBaobiao")
public class OaBaobiaoController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected OaBaobiaoService oaBaobiaoService;

	@ModelAttribute
	public OaBaobiao get(@RequestParam(required=false) String id){
		OaBaobiao entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaBaobiaoService.get(id);
		}
		if (entity == null){
			entity = new OaBaobiao();
		}
		return entity;
	}

	@RequiresPermissions("baobiao:oaBaobiao:view")
	@RequestMapping(value = {"form"})
	public String form(OaBaobiao oaBaobiao, Model model) {
		String view = "oaBaobiaoForm";
		if (StringUtils.isNotBlank(oaBaobiao.getId())){
			String taskDefKey = oaBaobiao.getAct().getTaskDefKey();

			// 查看工单
			if (oaBaobiao.getAct().isFinishTask()) {
				view = "oaBaobiaoView";
			}

            // 调整申请
			else if ("modifyApply".equals(taskDefKey)) {
				view = "oaBaobiaoForm";
			}

			// 审核环节
			else {
				view = "oaBaobiaoAudit";
			}
		}

		model.addAttribute("oaBaobiao", oaBaobiao);
		return "modules/baobiao/" + view;
	}

	@RequiresPermissions("baobiao:oaBaobiao:edit")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public String save(OaBaobiao oaBaobiao) {
		try {
			Map<String, Object> variables = Maps.newHashMap();

			String message = oaBaobiaoService.save(oaBaobiao, variables);

			return renderResult(Global.TRUE, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return renderResult(Global.FALSE, "系统内部错误！");
	}

	@RequiresPermissions("baobiao:oaBaobiao:edit")
	@RequestMapping(value = "saveAudit")
	@ResponseBody
	public String saveAudit(OaBaobiao oaBaobiao, Model model) {
		String message = oaBaobiaoService.auditSave(oaBaobiao);
		return renderResult(Global.TRUE, message);
	}

	@RequestMapping(value = "uploadFile")
	public String uploadFile(@RequestParam(value = "报表文件", required = true) String fileTreeId, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("fileTreeId", fileTreeId);
	//	model.addAttribute("oaBaobiao", oaBaobiao);
	//	return "modules/baobiao/upload";
		return "modules/file/sysFileUpload";
	}


}