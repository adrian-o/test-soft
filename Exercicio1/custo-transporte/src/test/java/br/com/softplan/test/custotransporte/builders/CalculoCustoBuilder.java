package br.com.softplan.test.custotransporte.builders;

import br.com.softplan.test.custotransporte.models.CalculoCusto;
import br.com.softplan.test.custotransporte.models.domains.TipoVeiculoEnum;

public class CalculoCustoBuilder {
	
	private CalculoCusto calculoCusto;
	
	private CalculoCustoBuilder() {
		
	}
	
	public static CalculoCustoBuilder umCalculoCusto() {
		CalculoCustoBuilder builder = new CalculoCustoBuilder();
		builder.calculoCusto = new CalculoCusto();
		
		builder.calculoCusto.setDistanciaRodoviaPav(45);
		builder.calculoCusto.setDistanciaRodoviaNaoPav(25);
		builder.calculoCusto.setCargaTon(5);
		builder.calculoCusto.setTipoVeiculo(TipoVeiculoEnum.CAMINHAO_BAU);
		
		return builder;
	}
	
	public CalculoCusto agora() {
		return this.calculoCusto;
	}
	
	public CalculoCustoBuilder semDistanciaEmRodoviaPavimentada() {
		this.calculoCusto.setDistanciaRodoviaPav(0);
		return this;
	}
	
	public CalculoCustoBuilder semDistanciaEmRodoviaNaoPavimentada() {
		this.calculoCusto.setDistanciaRodoviaNaoPav(0);
		return this;
	}
	
	public CalculoCustoBuilder semCargaEmToneladas() {
		this.calculoCusto.setCargaTon(0);
		return this;
	}
	
	public CalculoCustoBuilder comTipoVeiculo(TipoVeiculoEnum tipoVeiculo) {
		this.calculoCusto.setTipoVeiculo(tipoVeiculo);
		return this;
	}
	
	public CalculoCustoBuilder comCargaMaiorQue5Toneladas() {
		this.calculoCusto.setCargaTon(6);
		return this;
	}
}
