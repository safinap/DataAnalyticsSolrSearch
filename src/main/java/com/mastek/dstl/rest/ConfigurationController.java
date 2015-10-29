package com.mastek.dstl.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mastek.dstl.dvo.Response;
import com.mastek.dstl.dvo.SourceUrl;
import com.mastek.dstl.dvo.Status;
import com.mastek.dstl.service.CrawlerService;
import com.mastek.dstl.service.SourceUrlService;

@RestController
@RequestMapping(value = "/configure")
public class ConfigurationController {

	private static final Logger logger = Logger.getLogger(ConfigurationController.class);

	@Autowired
	private SourceUrlService service;

	@Autowired
	private CrawlerService crawlService;

	@RequestMapping(value = "/crawlExtract")
	public Response crawl() {
		if (logger.isDebugEnabled()) {
			logger.debug("Crawling and extracting.");
		}
		Response response = new Response();
		try {
			crawlService.scrapeAllUrls();
			response.setMessage("Crawling has been done sucessfully");
			response.setStatus(Status.SUCESS);
		} catch (Exception e) {
			logger.error("Failed.", e);
			response.setMessage("Error while crawling");
			response.setStatus(Status.FAILURE);
		}
		return response;
	}

	@RequestMapping(value = "/source-url", method = RequestMethod.POST)
	public Response registerUrl(@RequestBody List<String> urls) {
		if (logger.isDebugEnabled()) {
			logger.debug("Registering URLs: " + urls);
		}
		Response response = new Response();
		try {
			SourceUrl sourceUrl = new SourceUrl();
			sourceUrl.setRemark("Remarks");
			for (String url : urls) {
				sourceUrl.setUrl(url);
				service.addUrl(sourceUrl);
			}
			response.setMessage("URLs Registration done sucessfully");
			response.setStatus(Status.SUCESS);
			if (logger.isDebugEnabled()) {
				logger.debug("URLs registered.");
			}
		} catch (Exception e) {
			logger.error("Failed.", e);
			response.setMessage("Error while URLs Registration");
			response.setStatus(Status.FAILURE);
		}
		return response;

	}

}
