package com.mastek.dstl.dvo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SolrDocument(solrCoreName = "entities")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SolrInput implements Serializable {

	@Id
	@Field
	private Long id;
	@Field
	private String url;
	@Field
	private String text;
	@Field
	private String title;

	@Field
	private List<String> person;
	@Field
	private List<String> country;
	@Field
	private List<String> company;
	@Field
	private List<String> city;
	@Field
	private List<String> crime;
	@Field
	private List<String> organization;
	@Field
	private List<String> region;
	@Field
	private List<String> continent;
	@Field
	private List<String> jobTitle;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<String> getPerson() {
		return person;
	}

	public void setPerson(List<String> person) {
		this.person = person;
	}

	public List<String> getCountry() {
		return country;
	}

	public void setCountry(List<String> country) {
		this.country = country;
	}

	public List<String> getCompany() {
		return company;
	}

	public void setCompany(List<String> company) {
		this.company = company;
	}

	public List<String> getCity() {
		return city;
	}

	public void setCity(List<String> city) {
		this.city = city;
	}

	public List<String> getCrime() {
		return crime;
	}

	public void setCrime(List<String> crime) {
		this.crime = crime;
	}

	public List<String> getOrganization() {
		return organization;
	}

	public void setOrganization(List<String> organization) {
		this.organization = organization;
	}

	public List<String> getRegion() {
		return region;
	}

	public void setRegion(List<String> region) {
		this.region = region;
	}

	public List<String> getContinent() {
		return continent;
	}

	public void setContinent(List<String> continent) {
		this.continent = continent;
	}

	public List<String> getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(List<String> jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "SolrInput [id=" + id + ", url=" + url + ", title=" + title + "]";
	}
}
