/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/lruijun/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.localstorage.dao;

import com.jsite.common.persistence.CrudDao;
import com.jsite.common.persistence.annotation.MyBatisDao;
import com.jsite.modules.localstorage.entity.Storage;
import org.apache.ibatis.annotations.Update;

/**
 * 111DAO接口
 * @author liuruijun
 * @version 2019-04-24
 */
@MyBatisDao
public interface StorageDao extends CrudDao<Storage> {
	// TODO 根据具体业务增加方法
    @Update({"update storage set warehouse_id=#{warehouse_id},remaining=#{remaining},need=#{need} where cargo_id=#{cargo_id}"})
    void set(String str[]);
}