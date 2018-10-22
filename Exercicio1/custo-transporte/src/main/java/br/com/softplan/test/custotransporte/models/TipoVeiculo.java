package br.com.softplan.test.custotransporte.models;

import java.io.Serializable;
import java.math.BigDecimal;

public class TipoVeiculo implements Serializable {

	private static final long serialVersionUID = 5519973731830219069L;

	private Integer id;
	
	private String descricao;
	
	private BigDecimal fatorMultiplicador;

	public TipoVeiculo() {}
	
	public TipoVeiculo(Integer id, String descricao, BigDecimal fatorMultiplicador) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.fatorMultiplicador = fatorMultiplicador;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getFatorMultiplicador() {
		return fatorMultiplicador;
	}

	public void setFatorMultiplicador(BigDecimal fatorMultiplicador) {
		this.fatorMultiplicador = fatorMultiplicador;
	}
	
	
}
