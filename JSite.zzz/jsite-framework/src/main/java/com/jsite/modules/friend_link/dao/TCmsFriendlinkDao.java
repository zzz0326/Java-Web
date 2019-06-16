/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/baseweb/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.friend_link.dao;

import com.jsite.common.persistence.CrudDao;
import com.jsite.common.persistence.annotation.MyBatisDao;
import com.jsite.modules.friend_link.entity.TCmsFriendlink;

/**
 * 友情链接DAO接口
 * @author 豆奶
 * @version 2019-04-11
 */
@MyBatisDao
public interface TCmsFriendlinkDao extends CrudDao<TCmsFriendlink> {
	
}