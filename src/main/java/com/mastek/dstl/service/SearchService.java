package com.mastek.dstl.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.CursorMarkParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastek.dstl.dvo.EntityType;
import com.mastek.dstl.dvo.QuerySort;
import com.mastek.dstl.dvo.SearchRequest;

@Service
public class SearchService {

	@Autowired
	private SolrServer server;

	public QueryResponse search(SearchRequest searchRequest) throws SolrServerException {
		List<QuerySort> querySorts = new ArrayList<QuerySort>();
		QuerySort querySort = new QuerySort(SolrQuery.ORDER.desc, "score");
		QuerySort querySortId = new QuerySort(SolrQuery.ORDER.asc, "id");
		querySorts.add(querySort);
		querySorts.add(querySortId);

		String[] highlightFields = new String[] { "text" };

		String[] facetFields = new String[] { EntityType.PERSON.name().toLowerCase(),
				EntityType.ORGANIZATION.name().toLowerCase(), EntityType.COUNTRY.name().toLowerCase(),
				EntityType.CRIME.name().toLowerCase() };

		return search(searchRequest.getQueryString(), null, querySorts, facetFields, searchRequest.getFacetFields(),
				highlightFields, 0, searchRequest.getPageRows(), searchRequest.getCursor());
	}

	public QueryResponse search(String text, String[] resultFields, List<QuerySort> querySort, String[] facetFields,
			Map<String, String[]> filter, String[] highlightFields, int start, int rows, String cursorMark)
					throws SolrServerException {
		SolrQuery query = new SolrQuery();
		if (start != 0) {
			query.setStart(start);
		}
		if (rows != 0) {
			query.setRows(rows);
		}

		if (cursorMark != null) {
			query.set(CursorMarkParams.CURSOR_MARK_PARAM, cursorMark);
		}
		//Query
		query.setQuery(text);
		//Fields
		if (resultFields != null) {
			Arrays.asList(resultFields).forEach(field -> query.addField(field));
		}
		//Sorting
		if (querySort != null) {
			for (QuerySort queryS : querySort) {
				query.addSort(queryS.getSortField(), (ORDER) queryS.getSortOrder());
			}
		}
		//Faceting
		if (facetFields != null) {
			query.setFacet(true);
			query.setFacetLimit(-1);
			query.addFacetField(facetFields);
			query.setFacetMinCount(1);
			query.setFacetMissing(true);
		}
		//Filter
		if (filter != null) {
			for (String field : filter.keySet()) {
				String[] filterValues = filter.get(field);
				for (String filterValue : filterValues) {
					String f = field + ":" + filterValue;
					query.addFilterQuery(f);
				}

			}
		}

		//Highlighting
		if (highlightFields != null) {
			query.setHighlight(true);
			Arrays.asList(highlightFields).forEach(field -> query.addHighlightField(field));
			query.setHighlightSimplePre("<mark>");
			query.setHighlightSimplePost("</mark>");
			query.setHighlightSnippets(5);
		}
		query.setParam("defType", "edismax");
		query.setParam("qf", "person^10 organization^7 city^3 text");
		query.setParam("ps", "2");

		return server.query(query);
	}

}
