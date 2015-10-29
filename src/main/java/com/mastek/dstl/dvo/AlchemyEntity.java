package com.mastek.dstl.dvo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ALCHEMY_ENTITY")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlchemyEntity {

	@Id
	@Column(name = "ENTITY_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "ENTITY_TYPE")
	private String type;

	@Column(name = "OCCURENCES")
	private String count;

	@Column(name = "ENTITY_VALUE")
	private String text;

	public AlchemyEntity(String type, String text) {
		this.type = type;
		this.text = text;
	}

	public AlchemyEntity() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "AlchemyEntity [type=" + type + ", text=" + text + "]";
	}
}
