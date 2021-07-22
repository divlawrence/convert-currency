package com.example.convertcurrency.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("manage-currency")
//@RibbonClient("manage-currency")
public interface ConvertCurrencyServiceProxy {
	
	
	  @RequestMapping("/currency/get/{country_code}") 
	  public ResponseEntity<String> getConversionFactorfromCCode(@PathVariable("country_code") String countryCode);
	 

}
