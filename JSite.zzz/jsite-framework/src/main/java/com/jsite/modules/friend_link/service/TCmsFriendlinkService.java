/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/baseweb/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.friend_link.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsite.common.persistence.Page;
import com.jsite.common.service.CrudService;
import com.jsite.modules.friend_link.entity.TCmsFriendlink;
import com.jsite.modules.friend_link.dao.TCmsFriendlinkDao;

/**
 * 友情链接Service
 * @author 豆奶
 * @version 2019-04-11
 */
@Service
@Transactional(readOnly = true)
public class TCmsFriendlinkService extends CrudService<TCmsFriendlinkDao, TCmsFriendlink> {

	public TCmsFriendlink get(String id) {
		return super.get(id);
	}
	
	public List<TCmsFriendlink> findList(TCmsFriendlink tCmsFriendlink) {
		return super.findList(tCmsFriendlink);
	}
	
	public Page<TCmsFriendlink> findPage(Page<TCmsFriendlink> page, TCmsFriendlink tCmsFriendlink) {
		return super.findPage(page, tCmsFriendlink);
	}
	
	@Transactional(readOnly = false)
	public void save(TCmsFriendlink tCmsFriendlink) {
		super.save(tCmsFriendlink);
	}
	
	@Transactional(readOnly = false)
	public void delete(TCmsFriendlink tCmsFriendlink) {
		super.delete(tCmsFriendlink);
	}
	
}