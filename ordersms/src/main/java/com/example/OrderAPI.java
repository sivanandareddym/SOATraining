package com.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Order;
import com.example.repository.OrderRepository;

@RestController
@ConfigurationProperties(prefix="newConfig")
@RefreshScope
public class OrderAPI {
	
	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Autowired
	private OrderRepository orderRepository;
	
	@RequestMapping(value="/api/order", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order> add(@RequestBody Order manufacturer)
	{
		orderRepository.save(manufacturer);
		return new ResponseEntity<Order>(manufacturer, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/api/order/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order> update( @RequestBody Order manufacturer, @PathVariable Integer id )
	{
		Order manf = orderRepository.findOne(id);
		if(manf!=null)
		{
			manf.setName(manufacturer.getName());
			manf.setFoundDate(manufacturer.getFoundDate());
			manf.setActive(manufacturer.getActive());
		}
			orderRepository.save(manf);
		return new ResponseEntity<Order>(manufacturer, HttpStatus.CREATED);
	}
	
/*	@RequestMapping(value="/api/order", method=RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Order>> findAll()
	{
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(123,"localorder", new Date(), true));
		return  new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}*/
	
	
	@RequestMapping(value="/api/order", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Order> findAll()
	{
		System.err.println("accessing orders getter");
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(123,getData(), new Date(), true));
		return orders;
	}
}
