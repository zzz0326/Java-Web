/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/baseweb/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.storage.record.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.jsite.common.persistence.DataEntity;

/**
 * 仓储记录Entity
 * @author zzz
 * @version 2019-05-19
 */
public class StorageRecord extends DataEntity<StorageRecord> {
	
	private static final long serialVersionUID = 1L;
	private Date starttiem;		// starttiem
	private String warehouseId;		// warehouse_id
	private String cargoId;		// cargo_id
	private String need;		// need
	private String processId;		// process_id
	
	public StorageRecord() {
		super();
	}

	public StorageRecord(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStarttiem() {
		return starttiem;
	}

	public void setStarttiem(Date starttiem) {
		this.starttiem = starttiem;
	}
	
	@Length(min=0, max=255, message="warehouse_id长度必须介于 0 和 255 之间")
	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	
	@Length(min=0, max=11, message="cargo_id长度必须介于 0 和 11 之间")
	public String getCargoId() {
		return cargoId;
	}

	public void setCargoId(String cargoId) {
		this.cargoId = cargoId;
	}
	
	@Length(min=0, max=255, message="need长度必须介于 0 和 255 之间")
	public String getNeed() {
		return need;
	}

	public void setNeed(String need) {
		this.need = need;
	}
	
	@Length(min=0, max=255, message="process_id长度必须介于 0 和 255 之间")
	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}
	
}