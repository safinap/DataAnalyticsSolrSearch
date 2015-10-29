package com.mastek.dstl.dvo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SOURCE_URL_MST")
public class SourceUrl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "URL")
	private String url;
	@Column(name = "REMARK")
	private String remark;
	@Column(name = "IS_CRAWLED")
	private boolean crawled;

	public SourceUrl() {
	}

	public SourceUrl(String url, String remark) {
		this.url = url;
		this.remark = remark;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "SourceUrl [url=" + url + ", remark=" + remark + "]";
	}

	public boolean isCrawled() {
		return crawled;
	}

	public void setCrawled(boolean crawled) {
		this.crawled = crawled;
	}

}
