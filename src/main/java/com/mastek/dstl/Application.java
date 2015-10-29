package com.mastek.dstl;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@EnableAutoConfiguration
@ComponentScan(basePackages = "com.mastek.dstl")
@EnableJpaRepositories("com.mastek.dstl.repository")
@EnableSolrRepositories("com.mastek.dstl.repository")
public class Application extends SpringBootServletInitializer {
	private final static transient Logger logger = Logger.getLogger(Application.class);

	private static final Class<Application> applicationClass = Application.class;

	public static void main(String[] args) {
		SpringApplication.run(applicationClass, args);
		if (logger.isInfoEnabled()) {
			logger.info("Application started.");
		}
		while (true) {

		}
	}

	@Bean
	public SolrServer solrServer() {
		return new HttpSolrServer("http://localhost:8983/solr/entities");
	}

	@Bean
	public SolrTemplate solrTemplate(SolrServer server) {
		return new SolrTemplate(server);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

}
