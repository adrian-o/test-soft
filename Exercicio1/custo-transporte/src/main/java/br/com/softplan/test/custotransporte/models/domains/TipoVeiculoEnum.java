package br.com.softplan.test.custotransporte.models.domains;

import java.math.BigDecimal;

public enum TipoVeiculoEnum {
	
	CAMINHAO_BAU("Caminhão Baú", new BigDecimal(1.0)),
	CAMINHAO_CACAMBA("Caminhão Caçamba", new BigDecimal(1.05)),
	CARRETA("Carreta", new BigDecimal(1.12));
	
	private TipoVeiculoEnum(String label, BigDecimal fatorMultiplicador) {
		this.label = label;
		this.fatorMultiplicador = fatorMultiplicador;
	}
	
	private String label;
	private BigDecimal fatorMultiplicador;
		
	public String getLabel() {
		return label;
	}
	
	public BigDecimal getFatorMultiplicador() {
		return fatorMultiplicador;
	}
}
