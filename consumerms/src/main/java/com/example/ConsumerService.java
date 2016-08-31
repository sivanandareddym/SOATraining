package com.example;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.model.Order;

@RestController
public class ConsumerService {

	@Autowired
	private DiscoveryClient client;
	
	@RequestMapping(value="/ordersPost", method=RequestMethod.POST)
	/**
	 * RestTemplate is used for easy marshalling and unmarshalling
	 * @return
	 */
	public ResponseEntity<String> getData()
	{
		List<ServiceInstance> instances = client.getInstances("order-micorservice");
		if(!CollectionUtils.isEmpty(instances))
		{
			URI uri = instances.get(0).getUri();
			if(uri!=null)
			{
				Order order1 = new Order(1, "order1", null, true);
				Order order2 = new Order(2, "order2", null, true);
				List<Order> orderList = new LinkedList<>();
				orderList.add(order1);
				orderList.add(order2);
				try {
					uri = new URI(uri.toString().concat("/api/order"));
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return (new RestTemplate()).postForEntity(uri, orderList.get(0), String.class);
			}
		}
		return null;
	}
}
