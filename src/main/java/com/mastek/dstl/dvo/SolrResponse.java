package com.mastek.dstl.dvo;

import java.util.List;
import java.util.Map;

import org.apache.solr.common.SolrDocumentList;

public class SolrResponse {

	private SolrDocumentList _results;
	private Map<String, Map<String, Long>> facetFields;
	private Map<String, Map<String, List<String>>> _highlighting;

	public Map<String, Map<String, List<String>>> get_highlighting() {
		return _highlighting;
	}

	public void set_highlighting(Map<String, Map<String, List<String>>> _highlighting) {
		this._highlighting = _highlighting;
	}

	public SolrDocumentList get_results() {
		return _results;
	}

	public void set_results(SolrDocumentList _results) {
		this._results = _results;
	}

	public Map<String, Map<String, Long>> getFacetFields() {
		return facetFields;
	}

	public void setFacetFields(Map<String, Map<String, Long>> facetFields) {
		this.facetFields = facetFields;
	}

}
