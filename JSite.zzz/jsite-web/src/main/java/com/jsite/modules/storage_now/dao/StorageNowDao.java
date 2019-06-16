/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/baseweb/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.storage_now.dao;

import com.jsite.common.persistence.CrudDao;
import com.jsite.common.persistence.annotation.MyBatisDao;
import com.jsite.modules.storage_now.entity.StorageNow;

/**
 * 查询现有库存DAO接口
 * @author zzz
 * @version 2019-05-21
 */
@MyBatisDao
public interface StorageNowDao extends CrudDao<StorageNow> {
	
}