package com.mastek.dstl.repository;

import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Service;

import com.mastek.dstl.dvo.SolrInput;

@Service
public interface EntitySolrRepository extends SolrCrudRepository<SolrInput, Long> {

}
