package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;

public class SaleMinDTO {

	private Long id;
	private Double amount;
	private LocalDate date;
	private String sellerName; 
	private Long sellerId;
	
	
	public SaleMinDTO() {
	}

	public SaleMinDTO(Long id, Double amount, LocalDate date, String sellerName, Long SellerId) {
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.sellerName = sellerName; 
		this.sellerId = SellerId; 
	}
	
	public SaleMinDTO(Sale entity) {
		id = entity.getId();
		amount = entity.getAmount();
		date = entity.getDate();
		sellerName = entity.getSeller().getName(); 
		sellerId = entity.getSeller().getId(); 
	}

	public Long getId() {
		return id;
	}

	public Double getAmount() {
		return amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getSellerName() {
		return sellerName;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	
	
	
}
