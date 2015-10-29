package com.mastek.dstl.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.mastek.dstl.dvo.UserSubscritionDetails;

@Service
public interface SubscriptionsRepository extends CrudRepository<UserSubscritionDetails, String> {

}
