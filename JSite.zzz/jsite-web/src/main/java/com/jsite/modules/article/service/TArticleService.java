/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/baseweb/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.article.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsite.common.persistence.Page;
import com.jsite.common.service.CrudService;
import com.jsite.modules.article.entity.TArticle;
import com.jsite.modules.article.dao.TArticleDao;

/**
 * 新闻管理Service
 * @author 豆奶
 * @version 2019-04-04
 */
@Service
@Transactional(readOnly = true)
public class TArticleService extends CrudService<TArticleDao, TArticle> {

	public TArticle get(String id) {
		return super.get(id);
	}
	
	public List<TArticle> findList(TArticle tArticle) {
		return super.findList(tArticle);
	}
	
	public Page<TArticle> findPage(Page<TArticle> page, TArticle tArticle) {
		return super.findPage(page, tArticle);
	}
	
	@Transactional(readOnly = false)
	public void save(TArticle tArticle) {
		super.save(tArticle);
	}
	
	@Transactional(readOnly = false)
	public void delete(TArticle tArticle) {
		super.delete(tArticle);
	}
	
}