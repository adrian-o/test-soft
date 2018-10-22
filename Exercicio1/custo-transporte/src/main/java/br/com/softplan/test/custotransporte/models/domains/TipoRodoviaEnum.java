package br.com.softplan.test.custotransporte.models.domains;

import java.math.BigDecimal;

public enum TipoRodoviaEnum {
	
	RODOVIA_PAVIMENTADA(new BigDecimal(0.54)), 
	RODOVIA_NAO_PAVIMENTADA(new BigDecimal(0.62));
	
	private BigDecimal custoKm;

	private TipoRodoviaEnum(BigDecimal custo) {
		this.custoKm = custo;
	}
	
	public BigDecimal getCustoKm() {
		return custoKm;
	}
}
