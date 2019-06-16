/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/lruijun/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.storage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jsite.common.persistence.ActEntity;
import com.jsite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 仓储流程Entity
 * @author liuruijun
 * @version 2019-04-22
 */
public class StorageGet extends ActEntity<StorageGet> {
	
	private static final long serialVersionUID = 1L;
	private String procInsId;		// 流程实例编号
	private Date startTime;		// 开始时间
	private Date endTime;		// 结束时间
	private String leaveType;		// 请假类型
	private Date applyTime;		// 申请时间
	private String warehouseId;		// warehouse_id
	private String carhoId;		// carho_id
	private String leftNum;		// left_num
	private String requestNum;		// request_num

	public StorageGet() {
		super();
	}

	public StorageGet(String id){
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	
	@Length(min=0, max=11, message="warehouse_id长度必须介于 0 和 11 之间")
	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	
	@Length(min=0, max=11, message="carho_id长度必须介于 0 和 11 之间")
	public String getCarhoId() {
		return carhoId;
	}

	public void setCarhoId(String carhoId) {
		this.carhoId = carhoId;
	}
	
	@Length(min=0, max=11, message="left_num长度必须介于 0 和 11 之间")
	public String getLeftNum() {
		return leftNum;
	}

	public void setLeftNum(String leftNum) {
		this.leftNum = leftNum;
	}
	
	@Length(min=0, max=11, message="request_num长度必须介于 0 和 11 之间")
	public String getRequestNum() {
		return requestNum;
	}

	public void setRequestNum(String requestNum) {
		this.requestNum = requestNum;
	}

	public void uploadFormExcel(){}
}