/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/lruijun/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.storage.dao;

import com.jsite.common.persistence.CrudDao;
import com.jsite.common.persistence.annotation.MyBatisDao;
import com.jsite.modules.localstorage.entity.Storage;
import com.jsite.modules.storage.entity.StorageGet;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.jboss.logging.Param;

import java.util.List;
import java.util.Map;

/**
 * 仓储流程DAO接口
 * @author liuruijun
 * @version 2019-04-22
 */
@MyBatisDao
public interface StorageGetDao extends CrudDao<StorageGet> {
	// TODO 根据具体业务增加方法
    void setUploadedFile(StorageGet storageGet);

    @Select("select * from storage")
    List<Storage> listTable();

    @Update({"update storage set warehouse_id=#{warehouse_id},remaining=#{remaining},need=#{need} where cargo_id=#{cargo_id}"})
    void set(String str[]);
}