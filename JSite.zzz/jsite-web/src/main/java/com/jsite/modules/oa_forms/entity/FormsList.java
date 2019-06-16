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
 * @version 2019-04-16
 */
public class FormsList extends DataEntity<FormsList> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 文件名
	private Date uploadTime;		// 上传时间
	private String form_type; // 报表类型
	private String uploader;		// 上传者
	private String path; // 文件相对路径
	private String fileSize; // 文件大小
	private String remark;		// 备注
	
	public FormsList() {
		super();
	}

	public FormsList(String id){
		super(id);
	}

	@Length(min=1, max=64, message="报表名长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="创建时间不能为空")
	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	
	@Length(min=5, max=5, message="类型必须为 \" \" 或 \"  \"")
	public String getForm_type() {
		return form_type;
	}

	public void setForm_type(String form_type) {
		this.form_type = form_type;
	}

	@Length(min = 1, max = 32, message = "用户ID长度必须介于 1 和 32 之间")
	public String getUploader() { return uploader; }

	public void setUploader(String uploader) { this.uploader = uploader; }

	public String getPath() { return path; }

	public void setPath(String path) { this.path = path; }

	public String getFileSize() { return fileSize; }

	public void setFileSize(String fileSize){ this.fileSize = fileSize; }

	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}