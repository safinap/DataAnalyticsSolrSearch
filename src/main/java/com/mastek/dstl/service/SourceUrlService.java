package com.mastek.dstl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastek.dstl.dvo.SourceUrl;
import com.mastek.dstl.repository.SourceUrlRepository;

@Service
public class SourceUrlService {

	@Autowired
	private SourceUrlRepository repository;

	public void addUrl(SourceUrl url) {
		repository.save(url);
	}

	public void updateUrl(SourceUrl url) {
		repository.save(url);
	}

	public void removeUrl(String url) {
		repository.delete(url);
	}

	public Iterable<SourceUrl> getUrlList() {
		return repository.findAll();
	}

}
