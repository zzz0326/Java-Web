package com.jsite.modules.test.web;

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
 *
 * @Description： 登录
 * @author [ Wenfeng.Huang ] on [2018年8月24日下午5:31:13]
 * @Modified By： [修改人] on [修改日期] for [修改说明]
 *
 */
@Controller
@RequestMapping("${adminPath}/index")
public class Index extends BaseController {

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

        @RequestMapping(value = {"list", ""})
        public String list() {
            return "modules/index";
        }


    @RequestMapping(value = "listData")
    @ResponseBody
    public Page<TArticle> listData(TArticle tArticle, HttpServletRequest request, HttpServletResponse response) {
        Page<TArticle> page = tArticleService.findPage(new Page<>(request, response), tArticle);
        return page;
    }

    @RequestMapping(value = "form")
    public String form(TArticle tArticle, Model model) {
        model.addAttribute("tArticle", tArticle);
        return "modules/index";
    }

    /*
    @RequestMapping(value = "listData")
    @ResponseBody
    public Page<TestData> listData(TestData testData, HttpServletRequest request, HttpServletResponse response) {
        Page<TestData> page = testDataService.findPage(new Page<>(request, response), testData);
        return page;
    }

    @RequestMapping(value = "form")
    public String form(TestData testData, Model model) {
        model.addAttribute("testData", testData);
        return "modules/test/testDataForm";
    }*/


}
