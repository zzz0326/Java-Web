/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/baseweb/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.article.web;

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
import com.jsite.modules.article.entity.TArticle;
import com.jsite.modules.article.service.TArticleService;

/**
 * 新闻管理Controller
 * @author 豆奶
 * @version 2019-04-04
 */
@Controller
@RequestMapping(value = "${adminPath}/article/tArticle")
public class TArticleController extends BaseController {

	@Autowired
	private TArticleService tArticleService;
	
	@ModelAttribute
	public TArticle get(@RequestParam(required=false) String id) {
		TArticle entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tArticleService.get(id);
		}
		if (entity == null){
			entity = new TArticle();
		}
		return entity;
	}
	
	@RequiresPermissions("article:tArticle:view")
	@RequestMapping(value = {"list", ""})
	public String list() {
		return "modules/article/tArticleList";
	}
	
	@RequiresPermissions("article:tArticle:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<TArticle> listData(TArticle tArticle, HttpServletRequest request, HttpServletResponse response) {
		Page<TArticle> page = tArticleService.findPage(new Page<>(request, response), tArticle);
		return page;
	}

	@RequiresPermissions("article:tArticle:view")
	@RequestMapping(value = "form")
	public String form(TArticle tArticle, Model model) {
		model.addAttribute("tArticle", tArticle);
		return "modules/article/tArticleForm";
	}

	@RequiresPermissions("article:tArticle:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public String save(TArticle tArticle) {
		tArticleService.save(tArticle);
		return renderResult(Global.TRUE, "保存新闻成功");
	}
	
	@RequiresPermissions("article:tArticle:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(TArticle tArticle) {
		tArticleService.delete(tArticle);
		return renderResult(Global.TRUE, "删除新闻成功");
	}

}