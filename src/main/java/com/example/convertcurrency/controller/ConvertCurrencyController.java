package com.example.convertcurrency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.convertcurrency.dto.CurrencyDTO;
import com.example.convertcurrency.service.ConvertCurrencyService;

@RestController
@RequestMapping("/currency")
public class ConvertCurrencyController {
	
	@Autowired
	ConvertCurrencyService convertCurrencyService;
	
	@PostMapping("/convert")
	public double convertCurrency(@RequestBody CurrencyDTO currencyDTO) {
		double convertedAmount = convertCurrencyService.convertCurrency(currencyDTO);
		return convertedAmount;
		
	}
	
	

}
