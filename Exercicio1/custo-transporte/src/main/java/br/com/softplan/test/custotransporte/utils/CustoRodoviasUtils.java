package br.com.softplan.test.custotransporte.utils;

import static br.com.softplan.test.custotransporte.utils.NumberUtils.multiplicar;
import static br.com.softplan.test.custotransporte.utils.NumberUtils.novoBigDecimal;
import static java.math.RoundingMode.HALF_DOWN;

import java.math.BigDecimal;

import br.com.softplan.test.custotransporte.models.domains.TipoRodoviaEnum;

public class CustoRodoviasUtils {
	
	public static BigDecimal custoPorRodovia(TipoRodoviaEnum rodovia, Integer distancia) {
		if (distancia == 0) 
			return novoBigDecimal(0);
		return calcularCustoPorRodovia(rodovia.getCustoKm(), distancia);
	}
	
	public static BigDecimal calcularCustoPorRodovia(BigDecimal custo, Integer distancia) {
		custo = multiplicar(
					custo.setScale(2, HALF_DOWN), 
					novoBigDecimal(distancia)
				).setScale(2, HALF_DOWN);
		
		return custo;
	}
	
}
