/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/baseweb/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.storage.record.dao;

import com.jsite.common.persistence.CrudDao;
import com.jsite.common.persistence.annotation.MyBatisDao;
import com.jsite.modules.storage.record.entity.StorageRecord;

/**
 * 仓储记录DAO接口
 * @author zzz
 * @version 2019-05-19
 */
@MyBatisDao
public interface StorageRecordDao extends CrudDao<StorageRecord> {
	
}