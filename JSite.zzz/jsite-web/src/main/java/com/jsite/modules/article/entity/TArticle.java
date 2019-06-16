/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/baseweb/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.article.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.jsite.common.persistence.DataEntity;

/**
 * 新闻管理Entity
 * @author 豆奶
 * @version 2019-04-04
 */
public class TArticle extends DataEntity<TArticle> {
	
	private static final long serialVersionUID = 1L;
	private String categoryId;		// 分类
	private String title;		// 标题
	private String content;		// 内容
	private String image;		// 图片
	private String keywords;		// 关键字
	private Date createtime;		// 创建时间
	private String hit;		// 点击量
	private String description;		// 描述
	private String orders;		// 排序
	private String status;		// 状态
	private String author;		// 作者
	
	public TArticle() {
		super();
	}

	public TArticle(String id){
		super(id);
	}

	@Length(min=1, max=11, message="分类长度必须介于 1 和 11 之间")
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	@Length(min=0, max=150, message="标题长度必须介于 0 和 150 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=1, max=255, message="图片长度必须介于 1 和 255 之间")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@Length(min=1, max=255, message="关键字长度必须介于 1 和 255 之间")
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="创建时间不能为空")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@Length(min=1, max=11, message="点击量长度必须介于 1 和 11 之间")
	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}
	
	@Length(min=1, max=300, message="描述长度必须介于 1 和 300 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=1, max=11, message="排序长度必须介于 1 和 11 之间")
	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}
	
	@Length(min=1, max=4, message="状态长度必须介于 1 和 4 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=1, max=255, message="作者长度必须介于 1 和 255 之间")
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
}