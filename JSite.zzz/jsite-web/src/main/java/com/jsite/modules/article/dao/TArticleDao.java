/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/baseweb/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.article.dao;

import com.jsite.common.persistence.CrudDao;
import com.jsite.common.persistence.annotation.MyBatisDao;
import com.jsite.modules.article.entity.TArticle;

/**
 * 新闻管理DAO接口
 * @author 豆奶
 * @version 2019-04-04
 */
@MyBatisDao
public interface TArticleDao extends CrudDao<TArticle> {
	
}