package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projection.SaleMinProjection;

public class SaleMinDTO {

	private Long id;
	private LocalDate date;
	private Double amount;

	private String sellerName;

	public SaleMinDTO(Long id, LocalDate date, Double amount, String sellerName) {
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.sellerName = sellerName;
	}

	public SaleMinDTO(SaleMinProjection projection) {
		this.id = projection.getId();
		this.amount = projection.getAmount();
		this.date = projection.getDate();
		this.sellerName = projection.getName();
	}

	public SaleMinDTO(Sale entity) {
		id = entity.getId();
		amount = entity.getAmount();
		date = entity.getDate();
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

	@Override
	public String toString() {
		return "SaleMinDTO{" +
				"id=" + id +
				", amount=" + amount +
				", date=" + date +" nome=" + sellerName;
	}
}
