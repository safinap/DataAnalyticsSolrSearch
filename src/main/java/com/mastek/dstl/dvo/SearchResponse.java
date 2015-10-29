package com.mastek.dstl.dvo;

import org.apache.solr.client.solrj.response.QueryResponse;

public class SearchResponse {

	private QueryResponse queryResponse;
	private Response response;
	private SolrResponse solrResponse;

	public SolrResponse getSolrResponse() {
		return solrResponse;
	}

	public void setSolrResponse(SolrResponse solrResponse) {
		this.solrResponse = solrResponse;
	}

	public QueryResponse getQueryResponse() {
		return queryResponse;
	}

	public void setQueryResponse(QueryResponse queryResponse) {
		this.queryResponse = queryResponse;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

}
