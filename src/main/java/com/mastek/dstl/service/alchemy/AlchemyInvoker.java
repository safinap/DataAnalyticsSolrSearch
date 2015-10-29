package com.mastek.dstl.service.alchemy;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastek.dstl.dvo.AlchemyResponse;

@Component
public class AlchemyInvoker {
	private final static transient Logger logger = Logger.getLogger(AlchemyInvoker.class);

	private RestTemplate restTemplate = new RestTemplate();
	private ResourceBundle bundle = ResourceBundle.getBundle("com.mastek.dstl.service.alchemy.alchemy");
	private static final String urlPropertyKey = "url";

	@Value(value = "${alchemyapi.rest.url}")
	private String restServiceUrl;

	public AlchemyResponse crawlAndExtract(String url) throws Exception {
		AlchemyResponse alchemyResponse = new AlchemyResponse();
		
		AlchemyResponse alchemyResponseURL = new AlchemyResponse();
		if (logger.isTraceEnabled()) {
			logger.trace("Invoking Alchemy for url:" + url);
		}

		//Proxy settings
		//System.getProperties().put("proxySet", "true");
		//System.getProperties().put("http.proxyHost", "192.168.100.40");
		//System.getProperties().put("http.proxyPort", "8080");
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add(urlPropertyKey, url);

		Enumeration<String> keys = bundle.getKeys();
		String key;
		while (keys.hasMoreElements()) {
			key = keys.nextElement();
			map.add(key, bundle.getString(key));
		}

		// header and message converter preparation
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new MappingJackson2HttpMessageConverter(new ObjectMapper()));
		messageConverters.add(new FormHttpMessageConverter());
		restTemplate.setMessageConverters(messageConverters);

		// call to alchymy
		try {
			alchemyResponse = restTemplate.postForObject(getRestServiceUrl() + "URLGetRankedNamedEntities", request,
					AlchemyResponse.class);
			
			alchemyResponseURL =  restTemplate.postForObject(getRestServiceUrl() + "URLGetTitle", request,
									AlchemyResponse.class);
			
			alchemyResponse.setTitle(alchemyResponseURL.getTitle());
		} catch (ResourceAccessException e) {
			throw new Exception("Alchemy online service not accessible.", e);
		}
		return alchemyResponse;
	}

	public String getRestServiceUrl() {
		return restServiceUrl;
	}

	public void setRestServiceUrl(String restServiceUrl) {
		this.restServiceUrl = restServiceUrl;
	}

}
