package com.example.convertcurrency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.convertcurrency.dto.CurrencyDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ConvertCurrencyService {

	@Autowired
	ConvertCurrencyServiceProxy converyCurrencyServiceProxy;
	
	@Autowired
	LoadBalancerClient loadBalancerClient;

	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "convertCurrencyFallback")
	public double convertCurrency(CurrencyDTO currencyDTO) {
		double amount = currencyDTO.getAmount();
		System.out.println("Inside convertCurrency");
		ResponseEntity<String> responseEntity = converyCurrencyServiceProxy.getConversionFactorfromCCode(currencyDTO.getCountry_code());
		System.out.println("Response body"+responseEntity.getBody()+" "+responseEntity.getStatusCodeValue());
		double conversionFactor = Double.parseDouble(responseEntity.getBody());
		double convertedAmount = conversionFactor*amount;
		return convertedAmount;
	}
	
	
	//Hystrix fallback method
	public double convertCurrencyFallback(CurrencyDTO currencyDTO) {	
		System.out.println("INSIDE FALLBACK");
		double convertedAmount = currencyDTO.getAmount()*0.0;
		return convertedAmount;
	}
	
	
	
	
	public double convertCurrencyLoadBalanced(CurrencyDTO currencyDTO){

		ServiceInstance instance = loadBalancerClient.choose("manage-currency");
		if (instance != null) {
			double amount = currencyDTO.getAmount();
			String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/currency/get/"+currencyDTO.getCountry_code();
			System.out.println("Calling :" + url);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
			System.out.println("Response body"+responseEntity.getBody()+" "+responseEntity.getStatusCodeValue());
			double conversionFactor = Double.parseDouble(responseEntity.getBody());
			double convertedAmount = conversionFactor*amount;
			return convertedAmount;

		} else {
			return 0.0;
		}
		

	}
	
}
