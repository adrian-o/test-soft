package br.com.softplan.test.custotransporte.utils;

import static br.com.softplan.test.custotransporte.models.domains.TipoRodoviaEnum.RODOVIA_PAVIMENTADA;
import static br.com.softplan.test.custotransporte.utils.NumberUtils.multiplicar;
import static br.com.softplan.test.custotransporte.utils.NumberUtils.novoBigDecimal;
import static java.math.RoundingMode.HALF_DOWN;

import java.math.BigDecimal;

import br.com.softplan.test.custotransporte.models.CalculoCusto;
import br.com.softplan.test.custotransporte.models.domains.TipoRodoviaEnum;

public class CustoRodoviasUtils {
	
	public static BigDecimal custoPorRodovia(TipoRodoviaEnum rodovia, CalculoCusto custo) {
		Integer distancia = RODOVIA_PAVIMENTADA.equals(rodovia) ? 
				custo.getDistanciaRodoviaPav() : 
				custo.getDistanciaRodoviaNaoPav();
		if (distancia == 0) 
			return novoBigDecimal(0);
		return calcularCustoPorRodovia(rodovia.getCustoKm(), distancia);
	}
	
	public static BigDecimal calcularCustoPorRodovia(BigDecimal custo, Integer distancia) {
		return multiplicar(
				custo.setScale(2, HALF_DOWN), 
				novoBigDecimal(distancia)
			).setScale(2, HALF_DOWN);
	}
	
}
