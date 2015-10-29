package com.mastek.dstl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastek.dstl.dvo.UserSubscritionDetails;
import com.mastek.dstl.repository.SubscriptionsRepository;

@Service
public class SubscriptionsService {

	@Autowired
	private SubscriptionsRepository repository;

	public Iterable<UserSubscritionDetails> getSubscriptions() {
		return repository.findAll();
	}

	public UserSubscritionDetails getSubscription(String email) {
		return repository.findOne(email);
	}

	public void addSubscription(UserSubscritionDetails subscription) {
		repository.save(subscription);
	}

	public void updateSubscription(UserSubscritionDetails subscription) {
		repository.save(subscription);
	}

	public void removeSubscription(String url) {
		repository.delete(url);
	}

}
