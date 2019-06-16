/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/baseweb/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.oa_forms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jsite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 报表查看Entity
 * @author Dora
 * @version 2019-04-20
 */
public class OaFormsList extends DataEntity<OaFormsList> {
	
	private static final long serialVersionUID = 1L;
	private String filename;		// 文件名
	private Date uploadtime;		// 上传时间
	private String formType;		// 报表类型
	private String uploader;		// 上传者
	private String path;		// 相对路径
	private String filesize;		// 文件大小
	private String remark;		// 备注
	
	public OaFormsList() {
		super();
	}

	public OaFormsList(String id){
		super(id);
	}

	@Length(min=1, max=32, message="文件名长度必须介于 1 和 32 之间")
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="上传时间不能为空")
	public Date getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}
	
	@Length(min=1, max=5, message="报表类型长度必须介于 1 和 5 之间")
	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}
	
	@Length(min=1, max=64, message="上传者长度必须介于 1 和 64 之间")
	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	
	@Length(min=1, max=255, message="相对路径长度必须介于 1 和 255 之间")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@Length(min=1, max=32, message="文件大小长度必须介于 1 和 32 之间")
	public String getFilesize() {
		return filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}