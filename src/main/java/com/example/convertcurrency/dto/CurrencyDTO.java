package com.example.convertcurrency.dto;

public class CurrencyDTO {
	
	private String country_code;
	
	private double amount;

	
	/**
	 * @param country_code
	 * @param amount
	 */
	public CurrencyDTO(String country_code, double amount) {
		super();
		this.country_code = country_code;
		this.amount = amount;
	}

	/**
	 * 
	 */
	public CurrencyDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the country_code
	 */
	public String getCountry_code() {
		return country_code;
	}

	/**
	 * @param country_code the country_code to set
	 */
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	

}
