/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/baseweb/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.friend_link.web;

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
import com.jsite.modules.friend_link.entity.TCmsFriendlink;
import com.jsite.modules.friend_link.service.TCmsFriendlinkService;

/**
 * 友情链接Controller
 * @author 豆奶
 * @version 2019-04-11
 */
@Controller
@RequestMapping(value = "${adminPath}/friend_link/tCmsFriendlink")
public class TCmsFriendlinkController extends BaseController {

	@Autowired
	private TCmsFriendlinkService tCmsFriendlinkService;
	
	@ModelAttribute
	public TCmsFriendlink get(@RequestParam(required=false) String id) {
		TCmsFriendlink entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tCmsFriendlinkService.get(id);
		}
		if (entity == null){
			entity = new TCmsFriendlink();
		}
		return entity;
	}
	
	@RequiresPermissions("friend_link:tCmsFriendlink:view")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/friend_link/tCmsFriendlinkList";
	}
	
	@RequiresPermissions("friend_link:tCmsFriendlink:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<TCmsFriendlink> listData(TCmsFriendlink tCmsFriendlink, HttpServletRequest request, HttpServletResponse response) {
		Page<TCmsFriendlink> page = tCmsFriendlinkService.findPage(new Page<>(request, response), tCmsFriendlink);
		return page;
	}

	@RequiresPermissions("friend_link:tCmsFriendlink:view")
	@RequestMapping(value = "form")
	public String form(TCmsFriendlink tCmsFriendlink, Model model) {
		model.addAttribute("tCmsFriendlink", tCmsFriendlink);
		return "modules/friend_link/tCmsFriendlinkForm";
	}

	@RequiresPermissions("friend_link:tCmsFriendlink:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public String save(TCmsFriendlink tCmsFriendlink) {
		tCmsFriendlinkService.save(tCmsFriendlink);
		return renderResult(Global.TRUE, "保存友情链接成功");
	}
	
	@RequiresPermissions("friend_link:tCmsFriendlink:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(TCmsFriendlink tCmsFriendlink) {
		tCmsFriendlinkService.delete(tCmsFriendlink);
		return renderResult(Global.TRUE, "删除友情链接成功");
	}

}