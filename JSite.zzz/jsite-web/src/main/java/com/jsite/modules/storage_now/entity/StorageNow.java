/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/baseweb/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.storage_now.entity;

import org.hibernate.validator.constraints.Length;

import com.jsite.common.persistence.DataEntity;

/**
 * 查询现有库存Entity
 * @author zzz
 * @version 2019-05-21
 */
public class StorageNow extends DataEntity<StorageNow> {
	
	private static final long serialVersionUID = 1L;
	private String warehouseId;		// warehouse_id
	private String stockId;		// stock_id
	private String stockExit;		// stock_exit
	private String exitNumber;		// exit_number
	private String useable;		// useable
	private String useableNumber;		// useable_number
	
	public StorageNow() {
		super();
	}

	public StorageNow(String id){
		super(id);
	}

	@Length(min=1, max=5, message="warehouse_id长度必须介于 1 和 5 之间")
	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	
	@Length(min=1, max=30, message="stock_id长度必须介于 1 和 30 之间")
	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	
	@Length(min=0, max=255, message="stock_exit长度必须介于 0 和 255 之间")
	public String getStockExit() {
		return stockExit;
	}

	public void setStockExit(String stockExit) {
		this.stockExit = stockExit;
	}
	
	@Length(min=0, max=255, message="exit_number长度必须介于 0 和 255 之间")
	public String getExitNumber() {
		return exitNumber;
	}

	public void setExitNumber(String exitNumber) {
		this.exitNumber = exitNumber;
	}
	
	@Length(min=0, max=255, message="useable长度必须介于 0 和 255 之间")
	public String getUseable() {
		return useable;
	}

	public void setUseable(String useable) {
		this.useable = useable;
	}
	
	@Length(min=0, max=255, message="useable_number长度必须介于 0 和 255 之间")
	public String getUseableNumber() {
		return useableNumber;
	}

	public void setUseableNumber(String useableNumber) {
		this.useableNumber = useableNumber;
	}
	
}