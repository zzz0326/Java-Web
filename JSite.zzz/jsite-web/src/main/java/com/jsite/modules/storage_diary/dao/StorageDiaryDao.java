/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/baseweb/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.storage_diary.dao;

import com.jsite.common.persistence.CrudDao;
import com.jsite.common.persistence.annotation.MyBatisDao;
import com.jsite.modules.storage_diary.entity.StorageDiary;

/**
 * 仓储流水DAO接口
 * @author zzz
 * @version 2019-05-21
 */
@MyBatisDao
public interface StorageDiaryDao extends CrudDao<StorageDiary> {
	
}