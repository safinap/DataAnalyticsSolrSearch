package com.mastek.dstl.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.mastek.dstl.dvo.AlchemyResponse;

@Service
public interface AlchemyRepository extends CrudRepository<AlchemyResponse, Long> {

}
