package com.mastek.dstl.dvo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.solr.client.solrj.beans.Field;

@Entity
@Table(name = "USER_SUBSCRIPTIONS")
public class UserSubscritionDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "EMAIL")
	private String email;

	@Field
	@Column(name = "KEYWORDS")
	private String keywords;

	public UserSubscritionDetails() {
	}

	public UserSubscritionDetails(String email, String keywords) {
		this.email = email;
		this.keywords = keywords;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@Override
	public String toString() {
		return "[email=" + email + ", keywords=" + keywords + "]";
	}

}
