package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;

@Configuration
public class CustomLoadbalancerConfig {

	@Autowired
	private IClientConfig ribbonClientConfig;
	
	@Bean
	public IPing ribbonPint(IClientConfig config)
	{
		return new PingUrl();
	}
	
	@Bean
	public IRule ribbonRule(IClientConfig config)
	{
		return new AvailabilityFilteringRule();
	}
}
