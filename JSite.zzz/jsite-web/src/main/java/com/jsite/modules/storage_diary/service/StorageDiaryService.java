/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/baseweb/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.storage_diary.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsite.common.persistence.Page;
import com.jsite.common.service.CrudService;
import com.jsite.modules.storage_diary.entity.StorageDiary;
import com.jsite.modules.storage_diary.dao.StorageDiaryDao;

/**
 * 仓储流水Service
 * @author zzz
 * @version 2019-05-21
 */
@Service
@Transactional(readOnly = true)
public class StorageDiaryService extends CrudService<StorageDiaryDao, StorageDiary> {

	public StorageDiary get(String id) {
		return super.get(id);
	}
	
	public List<StorageDiary> findList(StorageDiary storageDiary) {
		return super.findList(storageDiary);
	}
	
	public Page<StorageDiary> findPage(Page<StorageDiary> page, StorageDiary storageDiary) {
		return super.findPage(page, storageDiary);
	}
	
	@Transactional(readOnly = false)
	public void save(StorageDiary storageDiary) {
		super.save(storageDiary);
	}
	
	@Transactional(readOnly = false)
	public void delete(StorageDiary storageDiary) {
		super.delete(storageDiary);
	}
	
}