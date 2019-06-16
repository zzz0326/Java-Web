/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/baseweb/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.oa_forms.service;

import com.jsite.common.persistence.Page;
import com.jsite.common.service.CrudService;
import com.jsite.modules.oa_forms.dao.FormsListDao;
import com.jsite.modules.oa_forms.entity.FormsList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 报表查看Service
 * @author Dora
 * @version 2019-04-16
 */
@Service
@Transactional(readOnly = true)
public class FormsListService extends CrudService<FormsListDao, FormsList> {

	public FormsList get(String id) {
		return super.get(id);
	}
	
	public List<FormsList> findList(FormsList formsList) {
		return super.findList(formsList);
	}
	
	public Page<FormsList> findPage(Page<FormsList> page, FormsList formsList) {
		return super.findPage(page, formsList);
	}
	
	@Transactional(readOnly = false)
	public void save(FormsList formsList) {
		super.save(formsList);
	}
	
	@Transactional(readOnly = false)
	public void delete(FormsList formsList) {
		super.delete(formsList);
	}
	
}