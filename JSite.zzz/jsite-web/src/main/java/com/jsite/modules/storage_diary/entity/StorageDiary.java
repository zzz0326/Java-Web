/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/baseweb/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.storage_diary.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.jsite.common.persistence.DataEntity;

/**
 * 仓储流水Entity
 * @author zzz
 * @version 2019-05-21
 */
public class StorageDiary extends DataEntity<StorageDiary> {
	
	private static final long serialVersionUID = 1L;
	private Date data;		// data
	private String docType;		// doc_type
	private String douNum;		// dou_num
	private String warehouse;		// warehouse
	private String transerType;		// transer_type
	private String department;		// department
	private String employ;		// employ
	private String suppliers;		// suppliers
	private String custom;		// custom
	private String notes;		// notes
	private String docMaker;		// doc_maker
	private String audit;		// audit
	private Date auditData;		// audit_data
	private String accounter;		// accounter
	private String stockCode;		// stock_code
	private String stockId;		// stock_id
	private String stockName;		// stock_name
	private String model;		// model
	private String mainMeas;		// main_meas
	private String inNum;		// in_num
	private String inPrice;		// in_price
	private String inSinglePrice;		// in_single_price
	private String outNum;		// out_num
	private String outPrice;		// out_price
	private String stockClassId;		// stock_class_id
	private String outSinglePrice;		// out_single_price
	private Date outDate;		// out_date
	private String stockClassName;		// stock_class_name
	private String orderClass;		// order_class
	
	public StorageDiary() {
		super();
	}

	public StorageDiary(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	@Length(min=0, max=255, message="doc_type长度必须介于 0 和 255 之间")
	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}
	
	@Length(min=1, max=100, message="dou_num长度必须介于 1 和 100 之间")
	public String getDouNum() {
		return douNum;
	}

	public void setDouNum(String douNum) {
		this.douNum = douNum;
	}
	
	@Length(min=0, max=255, message="warehouse长度必须介于 0 和 255 之间")
	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	
	@Length(min=0, max=255, message="transer_type长度必须介于 0 和 255 之间")
	public String getTranserType() {
		return transerType;
	}

	public void setTranserType(String transerType) {
		this.transerType = transerType;
	}
	
	@Length(min=0, max=255, message="department长度必须介于 0 和 255 之间")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Length(min=0, max=255, message="employ长度必须介于 0 和 255 之间")
	public String getEmploy() {
		return employ;
	}

	public void setEmploy(String employ) {
		this.employ = employ;
	}
	
	@Length(min=0, max=255, message="suppliers长度必须介于 0 和 255 之间")
	public String getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(String suppliers) {
		this.suppliers = suppliers;
	}
	
	@Length(min=0, max=255, message="custom长度必须介于 0 和 255 之间")
	public String getCustom() {
		return custom;
	}

	public void setCustom(String custom) {
		this.custom = custom;
	}
	
	@Length(min=0, max=255, message="notes长度必须介于 0 和 255 之间")
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	@Length(min=0, max=255, message="doc_maker长度必须介于 0 和 255 之间")
	public String getDocMaker() {
		return docMaker;
	}

	public void setDocMaker(String docMaker) {
		this.docMaker = docMaker;
	}
	
	@Length(min=0, max=255, message="audit长度必须介于 0 和 255 之间")
	public String getAudit() {
		return audit;
	}

	public void setAudit(String audit) {
		this.audit = audit;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAuditData() {
		return auditData;
	}

	public void setAuditData(Date auditData) {
		this.auditData = auditData;
	}
	
	@Length(min=0, max=255, message="accounter长度必须介于 0 和 255 之间")
	public String getAccounter() {
		return accounter;
	}

	public void setAccounter(String accounter) {
		this.accounter = accounter;
	}
	
	@Length(min=0, max=30, message="stock_code长度必须介于 0 和 30 之间")
	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	
	@Length(min=0, max=30, message="stock_id长度必须介于 0 和 30 之间")
	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	
	@Length(min=0, max=255, message="stock_name长度必须介于 0 和 255 之间")
	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	
	@Length(min=0, max=255, message="model长度必须介于 0 和 255 之间")
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	@Length(min=0, max=255, message="main_meas长度必须介于 0 和 255 之间")
	public String getMainMeas() {
		return mainMeas;
	}

	public void setMainMeas(String mainMeas) {
		this.mainMeas = mainMeas;
	}
	
	@Length(min=0, max=11, message="in_num长度必须介于 0 和 11 之间")
	public String getInNum() {
		return inNum;
	}

	public void setInNum(String inNum) {
		this.inNum = inNum;
	}
	
	public String getInPrice() {
		return inPrice;
	}

	public void setInPrice(String inPrice) {
		this.inPrice = inPrice;
	}
	
	public String getInSinglePrice() {
		return inSinglePrice;
	}

	public void setInSinglePrice(String inSinglePrice) {
		this.inSinglePrice = inSinglePrice;
	}
	
	@Length(min=0, max=11, message="out_num长度必须介于 0 和 11 之间")
	public String getOutNum() {
		return outNum;
	}

	public void setOutNum(String outNum) {
		this.outNum = outNum;
	}
	
	public String getOutPrice() {
		return outPrice;
	}

	public void setOutPrice(String outPrice) {
		this.outPrice = outPrice;
	}
	
	@Length(min=0, max=11, message="stock_class_id长度必须介于 0 和 11 之间")
	public String getStockClassId() {
		return stockClassId;
	}

	public void setStockClassId(String stockClassId) {
		this.stockClassId = stockClassId;
	}
	
	public String getOutSinglePrice() {
		return outSinglePrice;
	}

	public void setOutSinglePrice(String outSinglePrice) {
		this.outSinglePrice = outSinglePrice;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
	
	@Length(min=0, max=255, message="stock_class_name长度必须介于 0 和 255 之间")
	public String getStockClassName() {
		return stockClassName;
	}

	public void setStockClassName(String stockClassName) {
		this.stockClassName = stockClassName;
	}
	
	@Length(min=0, max=255, message="order_class长度必须介于 0 和 255 之间")
	public String getOrderClass() {
		return orderClass;
	}

	public void setOrderClass(String orderClass) {
		this.orderClass = orderClass;
	}
	
}