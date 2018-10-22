package br.com.softplan.test.custotransporte.utils;

import static br.com.softplan.test.custotransporte.utils.NumberUtils.adicionar;
import static br.com.softplan.test.custotransporte.utils.NumberUtils.multiplicar;
import static br.com.softplan.test.custotransporte.utils.NumberUtils.novoBigDecimal;
import static java.math.RoundingMode.HALF_DOWN;

import java.math.BigDecimal;

public class CustoCargaUtils {
	
	private static final Integer CARGA_MAXIMA_SEM_RECALCULO = 5;
	private static final BigDecimal FATOR_RECALCULO_EXCESSO_CARGA = new BigDecimal(0.02);
	
	public static BigDecimal calcularCustoPorExcessoCarga(BigDecimal custo, Integer carga, Integer distancia) {
		if (carga > CARGA_MAXIMA_SEM_RECALCULO) {
			custo = adicionar(
						custo.setScale(2, HALF_DOWN),
						multiplicar(
								 multiplicar(
											novoBigDecimal(carga - CARGA_MAXIMA_SEM_RECALCULO), 
											FATOR_RECALCULO_EXCESSO_CARGA
										).setScale(2, HALF_DOWN),
								novoBigDecimal(distancia)
						).setScale(2, HALF_DOWN)
					).setScale(2, HALF_DOWN);
		}
		
		return custo;
	}
}
