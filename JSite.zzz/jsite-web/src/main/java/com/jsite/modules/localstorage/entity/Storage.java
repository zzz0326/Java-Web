/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/lruijun/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.localstorage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jsite.common.persistence.ActEntity;
import com.jsite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 111Entity
 * @author liuruijun
 * @version 2019-04-24
 */
public class Storage extends ActEntity<Storage> {
	
	private static final long serialVersionUID = 1L;
	private String warehouseId;		// warehouse_id
	private String cargoId;		// cargo_id
	private String remaining;		// remaining
	private String need;		// need

	public Storage() {
		super();
	}

	public Storage(String i,String a,String b,int c,int d){
		this.id =i;
		this.warehouseId = a;
		this.cargoId = b;
		this.remaining = String.valueOf(c-d);
		this.need=String.valueOf(0);
	}

	public Storage(String id){
		super(id);
	}

	@Length(min=1, max=255, message="warehouse_id长度必须介于 1 和 255 之间")
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
	
	@Length(min=0, max=255, message="remaining长度必须介于 0 和 255 之间")
	public String getRemaining() {
		return remaining;
	}

	public void setRemaining(String remaining) {
		this.remaining = remaining;
	}
	
	@Length(min=0, max=255, message="need长度必须介于 0 和 255 之间")
	public String getNeed() {
		return need;
	}

	public void setNeed(String need) {
		this.need = need;
	}
	
}