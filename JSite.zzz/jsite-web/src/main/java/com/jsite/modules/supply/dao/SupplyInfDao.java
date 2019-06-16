/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/baseweb/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.supply.dao;

import com.jsite.common.persistence.CrudDao;
import com.jsite.common.persistence.annotation.MyBatisDao;
import com.jsite.modules.supply.entity.SupplyInf;
import com.jsite.modules.sys.entity.Dict;

import java.util.List;

/**
 * 关联查询DAO接口
 * @author zzz
 * @version 2019-05-22
 */
@MyBatisDao
public interface SupplyInfDao extends CrudDao<SupplyInf> {

    public List<SupplyInf> findSupplierList(SupplyInf supplyInf);
}