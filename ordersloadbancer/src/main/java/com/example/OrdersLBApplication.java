package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@RibbonClient(configuration=CustomLoadbalancerConfig.class, name="lb1")
public class OrdersLBApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdersLBApplication.class, args);
	}
}
