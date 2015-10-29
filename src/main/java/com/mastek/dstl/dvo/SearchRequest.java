package com.mastek.dstl.dvo;

import java.util.Map;

public class SearchRequest {

	private String queryString;
	private String cursor;
	private int pageRows;
	private Map<String, String[]> facetFields;

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public String getCursor() {
		return cursor;
	}

	public void setCursor(String cursor) {
		this.cursor = cursor;
	}

	public int getPageRows() {
		return pageRows;
	}

	public void setPageRows(int pageRows) {
		this.pageRows = pageRows;
	}

	public Map<String, String[]> getFacetFields() {
		return facetFields;
	}

	public void setFacetFields(Map<String, String[]> facetFields) {
		this.facetFields = facetFields;
	}

	@Override
	public String toString() {
		return queryString;
	}

}
