package com.jsite.modules.oa_forms.web;

import com.jsite.common.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "${adminPath}/oa_forms")
public class FormViewController extends BaseController {
    @RequiresPermissions("oa_forms:view:dataform")
    @RequestMapping(value = "view/dataform")
    String addDataForm(@RequestParam String formid, HttpServletRequest request, HttpServletResponse response) {
        return "modules/oa_forms/dataform";
    }

    @RequiresPermissions("oa_forms:upload:dataform")
    @RequestMapping(value = "upload/form")
    String addForm(HttpServletRequest request, HttpServletResponse response) {
        return "modules/oa_forms/form";
    }
}
