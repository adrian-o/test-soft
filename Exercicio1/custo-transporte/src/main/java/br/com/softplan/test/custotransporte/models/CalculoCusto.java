package br.com.softplan.test.custotransporte.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.softplan.test.custotransporte.models.domains.TipoVeiculoEnum;

public class CalculoCusto implements Serializable {

	private static final long serialVersionUID = -3415518650912855902L;

	private Integer distanciaRodoviaPav;
	
	private Integer distanciaRodoviaNaoPav;
	
	private TipoVeiculoEnum tipoVeiculo;
	
	@NotNull
	@Min(value=1, message="Carga deve ter pelo menos 1 tonelada")
	private Integer cargaTon;
	
	private BigDecimal valorTotal;

	public Integer getDistanciaRodoviaPav() {
		return distanciaRodoviaPav;
	}

	public void setDistanciaRodoviaPav(Integer distanciaRodoviaPav) {
		this.distanciaRodoviaPav = distanciaRodoviaPav;
	}

	public Integer getDistanciaRodoviaNaoPav() {
		return distanciaRodoviaNaoPav;
	}

	public void setDistanciaRodoviaNaoPav(Integer distanciaRodoviaNaoPav) {
		this.distanciaRodoviaNaoPav = distanciaRodoviaNaoPav;
	}

	public TipoVeiculoEnum getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(TipoVeiculoEnum tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	public Integer getCargaTon() {
		return cargaTon;
	}

	public void setCargaTon(Integer cargaTon) {
		this.cargaTon = cargaTon;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
}