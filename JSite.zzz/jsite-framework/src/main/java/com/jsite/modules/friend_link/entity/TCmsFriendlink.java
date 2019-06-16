/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/baseweb/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.friend_link.entity;

import org.hibernate.validator.constraints.Length;

import com.jsite.common.persistence.DataEntity;

/**
 * 友情链接Entity
 * @author 豆奶
 * @version 2019-04-11
 */
public class TCmsFriendlink extends DataEntity<TCmsFriendlink> {
	
	private static final long serialVersionUID = 1L;
	private String linkName;		// 链接名称
	private String linkType;		// 类型
	private String img;		// img
	private String linkUrl;		// 链接地址
	private String sortId;		// sort_id
	private String status;		// status
	private String groupId;		// group_id
	
	public TCmsFriendlink() {
		super();
	}

	public TCmsFriendlink(String id){
		super(id);
	}

	@Length(min=1, max=100, message="链接名称长度必须介于 1 和 100 之间")
	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	
	@Length(min=1, max=1, message="类型长度必须介于 1 和 1 之间")
	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}
	
	@Length(min=0, max=255, message="img长度必须介于 0 和 255 之间")
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	@Length(min=1, max=255, message="链接地址长度必须介于 1 和 255 之间")
	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	
	@Length(min=0, max=11, message="sort_id长度必须介于 0 和 11 之间")
	public String getSortId() {
		return sortId;
	}

	public void setSortId(String sortId) {
		this.sortId = sortId;
	}
	
	@Length(min=0, max=1, message="status长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=1, max=1, message="group_id长度必须介于 1 和 1 之间")
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
}