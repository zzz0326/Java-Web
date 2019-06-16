/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/baseweb/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.oa_forms.dao;

import com.jsite.common.persistence.CrudDao;
import com.jsite.common.persistence.annotation.MyBatisDao;
import com.jsite.modules.oa_forms.entity.FormsList;

/**
 * 报表查看DAO接口
 * @author Dora
 * @version 2019-04-16
 */
@MyBatisDao
public interface FormsListDao extends CrudDao<FormsList> {
	
}