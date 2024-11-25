package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SaleSumProjection;

public class SaleSumDTO {

	private String name;
	private Double soma;

	public SaleSumDTO() {
	}
	
	public SaleSumDTO(String name, Double soma) {
		this.name = name;
		this.soma = soma;
	}
	
	public SaleSumDTO(SaleSumProjection projection) {
		this.name = projection.getName();
		this.soma = projection.getSoma(); 
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSoma() {
		return soma;
	}

	public void setSoma(Double soma) {
		this.soma = soma;
	}

	@Override
	public String toString() {
		return "SaleSumDTO [name=" + name + ", soma=" + soma + "]";
	}
	
	
	
}
