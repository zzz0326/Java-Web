/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/baseweb/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.oa_forms.service;

import com.jsite.common.persistence.Page;
import com.jsite.common.service.CrudService;
import com.jsite.modules.oa_forms.dao.OaFormsListDao;
import com.jsite.modules.oa_forms.entity.OaFormsList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 报表查看Service
 * @author Dora
 * @version 2019-04-20
 */
@Service
@Transactional(readOnly = true)
public class OaFormsListService extends CrudService<OaFormsListDao, OaFormsList> {

	public OaFormsList get(String id) {
		return super.get(id);
	}
	
	public List<OaFormsList> findList(OaFormsList oaFormsList) {
		return super.findList(oaFormsList);
	}
	
	public Page<OaFormsList> findPage(Page<OaFormsList> page, OaFormsList oaFormsList) {
		return super.findPage(page, oaFormsList);
	}
	
	@Transactional(readOnly = false)
	public void save(OaFormsList oaFormsList) {
		super.save(oaFormsList);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaFormsList oaFormsList) {
		super.delete(oaFormsList);
	}
	
}