package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projection.SaleSellerMinProjection;

public class SaleSellerMinDTO {

    private String name;
    private Double total;

    public SaleSellerMinDTO(String name, Double total) {
        this.name = name;
        this.total = total;
    }

    public SaleSellerMinDTO(SaleSellerMinProjection projection) {
        this.name = projection.getName();
        this.total = projection.getTotal();
    }

    public String getName() {
        return name;
    }

    public Double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "SaleSellerMinDTO{" +
                "name='" + name + '\'' +
                ", total=" + total +
                '}';
    }
}
