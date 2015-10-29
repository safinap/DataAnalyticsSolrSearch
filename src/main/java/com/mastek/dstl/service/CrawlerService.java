package com.mastek.dstl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mastek.dstl.dvo.AlchemyResponse;
import com.mastek.dstl.dvo.EntityType;
import com.mastek.dstl.dvo.SolrInput;
import com.mastek.dstl.dvo.SourceUrl;
import com.mastek.dstl.dvo.UserSubscritionDetails;
import com.mastek.dstl.repository.AlchemyRepository;
import com.mastek.dstl.repository.EntitySolrRepository;
import com.mastek.dstl.service.alchemy.AlchemyInvoker;
import com.mastek.dstl.service.extraction.StructureTransformer;
import com.mastek.dstl.service.notification.EmailService;

@Service
public class CrawlerService {
	private static final Logger logger = Logger.getLogger(CrawlerService.class);

	@Autowired
	private AlchemyRepository entityRepository;
	@Autowired
	private EntitySolrRepository solrRepository;
	@Autowired
	private SourceUrlService urlService;
	@Autowired
	private SubscriptionsService subscriptionsService;
	@Autowired
	private EmailService mailService;
	@Autowired
	private SearchService searchService;

	@Autowired
	private AlchemyInvoker crawlInvoker;

	@Value(value = "${alchemyapi.rest.url}")
	private String alchemyUrl;

	private StructureTransformer structureTransformer;

	public CrawlerService() {
		EntityType[] properties = EntityType.values();
		structureTransformer = new StructureTransformer(properties);
	}

	public void scrapeAllUrls() throws Exception {
		Iterable<SourceUrl> urls = urlService.getUrlList();//TODO get delta
		List<AlchemyResponse> crawledList = crawl(urls);
		if (logger.isDebugEnabled()) {
			logger.debug("Crawling completed for " + crawledList.size() + " URLs.");
		}
		if (!crawledList.isEmpty()) {
			if (logger.isDebugEnabled()) {
				logger.debug("Persisting extracted entities to DB.");
			}
			entityRepository.save(crawledList);
			List<SolrInput> indexes = index(crawledList);
			if (!indexes.isEmpty()) {
				solrRepository.save(indexes);
				entityRepository.save(crawledList);//TODO these 2 should be atomic (responses updated index=true)
				Iterable<UserSubscritionDetails> subscriptions = subscriptionsService.getSubscriptions();
				notify(subscriptions);
			}
		}
	}

	public List<AlchemyResponse> crawl(Iterable<SourceUrl> urls) {
		List<AlchemyResponse> responses = new ArrayList<>();
		for (SourceUrl url : urls) {
			try {
				if (logger.isDebugEnabled()) {
					logger.debug("Crawling " + url);
				}
				AlchemyResponse response = crawlInvoker.crawlAndExtract(url.getUrl());
				responses.add(response);
			} catch (Exception e) {
				logger.error("Crawling failed for " + url, e);
				continue;
			}
		}
		return responses;
	}

	public List<SolrInput> index(List<AlchemyResponse> responses) {
		List<SolrInput> solrInputs = new ArrayList<>();
		for (AlchemyResponse response : responses) {
			try {
				if (logger.isDebugEnabled()) {
					logger.debug("Indexing content from: " + response.getUrl());
				}
				Map<EntityType, List<String>> flattened = structureTransformer.flatten(response.getEntities());
				SolrInput solrInput = new SolrInput();
				solrInput.setText(response.getText());
				solrInput.setUrl(response.getUrl());
				solrInput.setCity(flattened.get(EntityType.CITY));
				solrInput.setCompany(flattened.get(EntityType.COMPANY));
				solrInput.setContinent(flattened.get(EntityType.CONTINENT));
				solrInput.setCountry(flattened.get(EntityType.COUNTRY));
				solrInput.setCrime(flattened.get(EntityType.CRIME));
				solrInput.setJobTitle(flattened.get(EntityType.JOBTITLE));
				solrInput.setOrganization(flattened.get(EntityType.ORGANIZATION));
				solrInput.setPerson(flattened.get(EntityType.PERSON));
				solrInput.setRegion(flattened.get(EntityType.REGION));
				solrInput.setUrl(response.getUrl());
				solrInput.setText(response.getText());
				solrInput.setId(response.getId());
				solrInput.setTitle(response.getTitle());
				solrInputs.add(solrInput);
				response.setIndexed(true);
			} catch (Exception e) {
				logger.error("Failed.", e);
				continue;
			}
		}
		return solrInputs;
	}

	public void notify(Iterable<UserSubscritionDetails> subscritionDetails) {
		for (UserSubscritionDetails subscription : subscritionDetails) {
			new Runnable() {
				@Override
				public void run() {
					try {
						QueryResponse response = searchService.search(subscription.getKeywords(),
								new String[] { "url" }, null, null, null, null, 0, 0, null);
						if (logger.isDebugEnabled()) {
							logger.debug("Found " + response.getResults().size()
									+ " relevant results for keywords for e-mailing." + subscription.getKeywords());
						}
						if (!response.getResults().isEmpty()) {
							for (int i = 0; i < response.getResults().size(); i++) {
								mailService.sendEmail(subscription.getEmail(), subscription.getKeywords(),
										response.getResults().get(i));
								if (logger.isDebugEnabled()) {
									logger.debug("Emailing sent to: " + subscription.getEmail());
								}
							}
						}
					} catch (SolrServerException e) {
						logger.error("Emailing failed for subscription: " + subscription, e);
						return;
					}
				}
			}.run();
		}
	}

}
