package com.mastek.dstl.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mastek.dstl.dvo.Response;
import com.mastek.dstl.dvo.Status;
import com.mastek.dstl.dvo.UserSubscritionDetails;
import com.mastek.dstl.service.SubscriptionsService;

@RestController
@RequestMapping(value = "/subscribe")
public class SubscriptionsController {

	private static final Logger logger = Logger.getLogger(SubscriptionsController.class);

	@Autowired
	private SubscriptionsService subscriptionsService;

	@RequestMapping(method = RequestMethod.POST)
	public Response subsribeEmail(@RequestBody UserSubscritionDetails subscritionDetails) {
		if (logger.isDebugEnabled()) {
			logger.debug("Adding new subscription: " + subscritionDetails);
		}
		Response response = new Response();
		try {
			subscriptionsService.addSubscription(subscritionDetails);
			response.setMessage("URLs Registration done sucessfully");
			response.setStatus(Status.SUCESS);
			if (logger.isDebugEnabled()) {
				logger.debug("Subscription successfull.");
			}
		} catch (Exception e) {
			logger.error("Failed.", e);
			response.setMessage("Subscription failed.");
			response.setStatus(Status.FAILURE);
		}
		return response;
	}

}
