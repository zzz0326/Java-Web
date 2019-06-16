/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/baseweb/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.supply.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsite.modules.sys.entity.Dict;
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
import com.jsite.modules.supply.entity.SupplyInf;
import com.jsite.modules.supply.service.SupplyInfService;

import java.util.List;

/**
 * 关联查询Controller
 * @author zzz
 * @version 2019-05-22
 */
@Controller
@RequestMapping(value = "${adminPath}/supply/supplyInf")
public class SupplyInfController extends BaseController {

	@Autowired
	private SupplyInfService supplyInfService;
	
	@ModelAttribute
	public SupplyInf get(@RequestParam(required=false) String id) {
		SupplyInf entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = supplyInfService.get(id);
		}
		if (entity == null){
			entity = new SupplyInf();
		}
		return entity;
	}
	
	@RequiresPermissions("supply:supplyInf:view")
	@RequestMapping(value = {"list", ""})
	public String list(Model model) {
        List<SupplyInf> supplierList = supplyInfService.findSupplierListList();
        model.addAttribute("supplierList", supplierList);
        //增添用于查询的组
        return "modules/supply/supplyInfList";
	}
	
	@RequiresPermissions("supply:supplyInf:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<SupplyInf> listData(SupplyInf supplyInf,HttpServletRequest request, HttpServletResponse response) {

		Page<SupplyInf> page = supplyInfService.findPage(new Page<>(request, response), supplyInf);
		//需要在xml文件中的findpage函数中添加相应的语句
		return page;
	}




	@RequiresPermissions("supply:supplyInf:view")
	@RequestMapping(value = "form")
	public String form(SupplyInf supplyInf, Model model) {

		model.addAttribute("supplyInf", supplyInf);
		return "modules/supply/supplyInfForm";
	}

	@RequiresPermissions("supply:supplyInf:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public String save(SupplyInf supplyInf) {
		supplyInfService.save(supplyInf);
		return renderResult(Global.TRUE, "保存关联查询成功");
	}
	
	@RequiresPermissions("supply:supplyInf:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(SupplyInf supplyInf) {
		supplyInfService.delete(supplyInf);
		return renderResult(Global.TRUE, "删除关联查询成功");
	}

}