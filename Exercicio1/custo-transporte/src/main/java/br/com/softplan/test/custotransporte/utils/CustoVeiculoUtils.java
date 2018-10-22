package br.com.softplan.test.custotransporte.utils;

import static br.com.softplan.test.custotransporte.utils.NumberUtils.multiplicar;
import static java.math.RoundingMode.HALF_DOWN;

import java.math.BigDecimal;

import br.com.softplan.test.custotransporte.models.domains.TipoVeiculoEnum;

public class CustoVeiculoUtils {
	
	public static BigDecimal recalcularCustoPorFatorVeiculo(BigDecimal custo, TipoVeiculoEnum veiculo) {
		return multiplicar(
					custo.setScale(2, HALF_DOWN),
					veiculo.getFatorMultiplicador().setScale(2, HALF_DOWN)
				).setScale(2, HALF_DOWN);
	}
}
