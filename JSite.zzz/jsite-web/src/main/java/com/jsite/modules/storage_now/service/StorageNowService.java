/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/baseweb/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.storage_now.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsite.common.persistence.Page;
import com.jsite.common.service.CrudService;
import com.jsite.modules.storage_now.entity.StorageNow;
import com.jsite.modules.storage_now.dao.StorageNowDao;

/**
 * 查询现有库存Service
 * @author zzz
 * @version 2019-05-21
 */
@Service
@Transactional(readOnly = true)
public class StorageNowService extends CrudService<StorageNowDao, StorageNow> {

	public StorageNow get(String id) {
		return super.get(id);
	}
	
	public List<StorageNow> findList(StorageNow storageNow) {
		return super.findList(storageNow);
	}
	
	public Page<StorageNow> findPage(Page<StorageNow> page, StorageNow storageNow) {
		return super.findPage(page, storageNow);
	}
	
	@Transactional(readOnly = false)
	public void save(StorageNow storageNow) {
		super.save(storageNow);
	}
	
	@Transactional(readOnly = false)
	public void delete(StorageNow storageNow) {
		super.delete(storageNow);
	}
	
}