package com.example;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.model.Order;

@RestController
public class OrdersLBAPI {

	/**
	 * Internally FeignClient itself will implemen the load balancer. In order
	 * to show the explicitly we are using Load balancer
	 */
	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Order> findAll() {
		ServiceInstance instance = loadBalancerClient.choose("order-micorservice");

		try {
			return (new RestTemplate()).getForObject(new URI(instance.getUri().toString().concat("/api/order")),
					List.class);
		} catch (RestClientException | URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

}
