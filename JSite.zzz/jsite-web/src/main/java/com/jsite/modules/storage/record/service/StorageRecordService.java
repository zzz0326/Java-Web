/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/baseweb/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.storage.record.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsite.common.persistence.Page;
import com.jsite.common.service.CrudService;
import com.jsite.modules.storage.record.entity.StorageRecord;
import com.jsite.modules.storage.record.dao.StorageRecordDao;

/**
 * 仓储记录Service
 * @author zzz
 * @version 2019-05-19
 */
@Service
@Transactional(readOnly = true)
public class StorageRecordService extends CrudService<StorageRecordDao, StorageRecord> {

	public StorageRecord get(String id) {
		return super.get(id);
	}
	
	public List<StorageRecord> findList(StorageRecord storageRecord) {
		return super.findList(storageRecord);
	}
	
	public Page<StorageRecord> findPage(Page<StorageRecord> page, StorageRecord storageRecord) {
		return super.findPage(page, storageRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(StorageRecord storageRecord) {
		super.save(storageRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(StorageRecord storageRecord) {
		super.delete(storageRecord);
	}
	
}