/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/lruijun/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.baobiao.dao;

import com.jsite.common.persistence.CrudDao;
import com.jsite.common.persistence.annotation.MyBatisDao;
import com.jsite.modules.baobiao.entity.OaBaobiao;

/**
 * 报表管理DAO接口
 * @author liuruijun
 * @version 2019-04-17
 */
@MyBatisDao
public interface OaBaobiaoDao extends CrudDao<OaBaobiao> {
	// TODO 根据具体业务增加方法

    int updateLeadText(OaBaobiao oaBaobiao);

    int updateHRText(OaBaobiao oaBaobiao);
}