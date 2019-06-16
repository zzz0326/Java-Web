/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/lruijun/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.baobiao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jsite.common.persistence.ActEntity;
import com.jsite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 报表管理Entity
 * @author liuruijun
 * @version 2019-04-17
 */
public class OaBaobiao extends ActEntity<OaBaobiao> {
	
	private static final long serialVersionUID = 1L;
	private String procInsId;		// 流程实例编号
	private Date startTime;		// 开始时间
	private Date endTime;		// 结束时间
	private String leaveType;		// 请假类型
	private String reason;		// 请假理由
	private String deptLeadText;		// dept_lead_text
	private String hrText;		// hr_text
//	private Date applyTime;		// 申请时间
	private String formmakerText;		// formmaker_text

	public OaBaobiao() {
		super();
	}

	public OaBaobiao(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Length(min=0, max=20, message="请假类型长度必须介于 0 和 20 之间")
	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	
	@Length(min=0, max=255, message="请假理由长度必须介于 0 和 255 之间")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Length(min=0, max=255, message="dept_lead_text长度必须介于 0 和 255 之间")
	public String getDeptLeadText() {
		return deptLeadText;
	}

	public void setDeptLeadText(String deptLeadText) {
		this.deptLeadText = deptLeadText;
	}
	
	@Length(min=1, max=255, message="hr_text长度必须介于 1 和 255 之间")
	public String getHrText() {
		return hrText;
	}

	public void setHrText(String hrText) {
		this.hrText = hrText;
	}

	@Length(min=0, max=255, message="formmaker_text长度必须介于 0 和 255 之间")
	public String getFormmakerText() {
		return formmakerText;
	}

	public void setFormmakerText(String formmakerText) {
		this.formmakerText = formmakerText;
	}
	
}