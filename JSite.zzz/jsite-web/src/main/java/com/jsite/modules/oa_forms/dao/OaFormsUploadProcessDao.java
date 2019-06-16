/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/lruijun/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.oa_forms.dao;

import com.jsite.common.persistence.CrudDao;
import com.jsite.common.persistence.annotation.MyBatisDao;
import com.jsite.modules.oa_forms.entity.OaFormsUploadProcess;

/**
 * 报表流程DAO接口
 * @author liuruijun
 * @version 2019-04-19
 */
@MyBatisDao
public interface OaFormsUploadProcessDao extends CrudDao<OaFormsUploadProcess> {
	// TODO 根据具体业务增加方法
    void setUploadedFile(OaFormsUploadProcess oaFormsUploadProcess);
}