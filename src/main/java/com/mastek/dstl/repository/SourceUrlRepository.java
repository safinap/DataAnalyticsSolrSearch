package com.mastek.dstl.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.mastek.dstl.dvo.SourceUrl;

@Service
public interface SourceUrlRepository extends CrudRepository<SourceUrl, String> {

}
