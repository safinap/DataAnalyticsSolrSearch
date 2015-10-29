package com.mastek.dstl.dvo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ALCHEMY_RESPONSE")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlchemyResponse {

	@Id
	@Column(name = "CONTENT_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "IS_INDEXED")
	private boolean indexed;

	@Column(name = "URL")
	private String url;

	@Lob
	@Column(name = "CONTENT")
	private String text;

	@Column(name = "TITLE")
	private String title;
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "ALCHEMY_ENTITY_MAPPING", joinColumns = @JoinColumn(name = "CONTENT_ID") , inverseJoinColumns = @JoinColumn(name = "ENTITY_ID") )
	private List<AlchemyEntity> entities;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<AlchemyEntity> getEntities() {
		return entities;
	}

	public void setEntities(List<AlchemyEntity> entities) {
		this.entities = entities;
	}

	public boolean isIndexed() {
		return indexed;
	}

	public void setIndexed(boolean indexed) {
		this.indexed = indexed;
	}

	@Override
	public String toString() {
		return "AlchymyResponse [indexed=" + indexed + ", url=" + url + ", text=" + text + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the urlHeading
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param urlHeading the urlHeading to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}
