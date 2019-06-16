/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/lruijun/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.oa_forms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jsite.common.persistence.ActEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 报表流程Entity
 * @author liuruijun
 * @version 2019-04-19
 */
public class OaFormsUploadProcess extends ActEntity<OaFormsUploadProcess> {
	
	private static final long serialVersionUID = 1L;
	private String procInsId;		// 流程实例编号
	private Date startTime;		// 开始时间

	public OaFormsUploadProcess() {
		super();
	}

	public OaFormsUploadProcess(String id){
		super(id);
	}

	@Length(min=0, max=64, message="流程实例编号长度必须介于 0 和 64 之间")
	public String getProcInsId() {
		return procInsId;
	}

	public void setProcInsId(String procInsId) {
		this.procInsId = procInsId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void uploadFormExcel(){}
	
}