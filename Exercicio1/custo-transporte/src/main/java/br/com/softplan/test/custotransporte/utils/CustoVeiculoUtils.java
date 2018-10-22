package br.com.softplan.test.custotransporte.utils;

import static br.com.softplan.test.custotransporte.utils.NumberUtils.multiplicar;
import static java.math.RoundingMode.HALF_DOWN;

import java.math.BigDecimal;

import br.com.softplan.test.custotransporte.models.CalculoCusto;

public class CustoVeiculoUtils {
	
	public static BigDecimal recalcularCustoPorFatorVeiculo(BigDecimal custoCalculado, CalculoCusto custo) {
		return multiplicar(
					custoCalculado.setScale(2, HALF_DOWN),
					custo.getTipoVeiculo().getFatorMultiplicador().setScale(2, HALF_DOWN)
				).setScale(2, HALF_DOWN);
	}
}
