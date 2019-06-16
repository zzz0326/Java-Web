/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/baseweb/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.supply.service;

import java.util.List;

import com.jsite.modules.sys.entity.Dict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsite.common.persistence.Page;
import com.jsite.common.service.CrudService;
import com.jsite.modules.supply.entity.SupplyInf;
import com.jsite.modules.supply.dao.SupplyInfDao;

/**
 * 关联查询Service
 * @author zzz
 * @version 2019-05-22
 */
@Service
@Transactional(readOnly = true)
public class SupplyInfService extends CrudService<SupplyInfDao, SupplyInf> {

	public List<SupplyInf> findSupplierListList(){
		return dao.findSupplierList(new SupplyInf());
	}

	public SupplyInf get(String id) {
		return super.get(id);
	}
	
	public List<SupplyInf> findList(SupplyInf supplyInf) {
		return super.findList(supplyInf);
	}

	public Page<SupplyInf> findPage(Page<SupplyInf> page, SupplyInf supplyInf) {
		return super.findPage(page, supplyInf);
	}


	
	@Transactional(readOnly = false)
	public void save(SupplyInf supplyInf) {
		super.save(supplyInf);
	}
	
	@Transactional(readOnly = false)
	public void delete(SupplyInf supplyInf) {
		super.delete(supplyInf);
	}
	
}