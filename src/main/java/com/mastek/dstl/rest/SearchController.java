package com.mastek.dstl.rest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mastek.dstl.dvo.Response;
import com.mastek.dstl.dvo.SearchRequest;
import com.mastek.dstl.dvo.SearchResponse;
import com.mastek.dstl.dvo.SolrResponse;
import com.mastek.dstl.dvo.Status;
import com.mastek.dstl.service.SearchService;

@RestController
@RequestMapping(value = "/search")
public class SearchController {

	private static final Logger logger = Logger.getLogger(SearchController.class);

	@Autowired
	private SearchService searchService;

	@RequestMapping(method = RequestMethod.POST)
	public SearchResponse search(@RequestBody SearchRequest searchRequest) {
		if (logger.isDebugEnabled()) {
			logger.debug("Search : " + searchRequest);
		}
		SearchResponse searchResponse = new SearchResponse();
		Map<String, Map<String, Long>> facetFieldMap = new LinkedHashMap<String, Map<String, Long>>();
		SolrResponse solrResponse = new SolrResponse();
		Response response = new Response();
		try {
			QueryResponse queryResponse = searchService.search(searchRequest);
			if (logger.isDebugEnabled()) {
				logger.debug("Search returned " + queryResponse.getResults().size() + " results.");
			}
			if (logger.isTraceEnabled()) {
				for (int i = 0; i < queryResponse.getResults().size(); i++) {
					logger.trace("Result: " + queryResponse.getResults().get(i));
				}
			}
			response.setMessage("Searching has been done sucessfully");
			response.setStatus(Status.SUCESS);

			List<FacetField> facetFieldList = queryResponse.getFacetFields();

			for (FacetField FacetField : facetFieldList) {
				Map<String, Long> facetCount = null;
				facetCount = new LinkedHashMap<String, Long>();
				for (Count count : FacetField.getValues()) {
					if (null != count.getName()) {
						facetCount.put(count.getName(), count.getCount());
						facetFieldMap.put(FacetField.getName(), facetCount);
					}
				}
			}
			solrResponse.set_results(queryResponse.getResults());
			solrResponse.setFacetFields(facetFieldMap);
			searchResponse.setResponse(response);
			solrResponse.set_highlighting(queryResponse.getHighlighting());
			searchResponse.setSolrResponse(solrResponse);
			if (logger.isDebugEnabled()) {
				logger.debug("Search complete.");
			}
		} catch (Exception e) {
			logger.error("Failed.", e);
			//response.setMessage("Error while searching");
			//response.setStatus(Status.FAILURE);
		}
		return searchResponse;
	}

}
