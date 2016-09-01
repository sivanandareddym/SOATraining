package com.example.dao;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.Order;

@FeignClient("order-micorservice")
public interface OrderRepository{
	
	@RequestMapping(value="/api/order")
	List<Order> findAll();

}
