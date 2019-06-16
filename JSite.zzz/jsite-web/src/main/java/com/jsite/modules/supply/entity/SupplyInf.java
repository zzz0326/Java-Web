/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/baseweb/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.supply.entity;

import org.hibernate.validator.constraints.Length;

import com.jsite.common.persistence.DataEntity;

/**
 * 关联查询Entity
 * @author zzz
 * @version 2019-05-22
 */
public class SupplyInf extends DataEntity<SupplyInf> {
	
	private static final long serialVersionUID = 1L;
	private String supplier;		// supplier
	private String product;		// product
	private String address;		// address
	private String principal;		// principal
	private String phonenum;		// phonenum
	
	public SupplyInf() {
		super();
	}

	public SupplyInf(String id){
		super(id);
	}

	@Length(min=1, max=20, message="supplier长度必须介于 1 和 20 之间")
	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
	@Length(min=0, max=255, message="product长度必须介于 0 和 255 之间")
	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}
	
	@Length(min=0, max=255, message="address长度必须介于 0 和 255 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=5, message="principal长度必须介于 0 和 5 之间")
	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	
	@Length(min=0, max=20, message="phonenum长度必须介于 0 和 20 之间")
	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	
}