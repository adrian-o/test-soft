package br.com.softplan.test.custotransporte;

import static br.com.softplan.test.custotransporte.builders.CalculoCustoBuilder.umCalculoCusto;
import static br.com.softplan.test.custotransporte.models.domains.TipoRodoviaEnum.RODOVIA_PAVIMENTADA;
import static br.com.softplan.test.custotransporte.utils.CustoCargaUtils.calcularCustoPorExcessoCarga;
import static br.com.softplan.test.custotransporte.utils.CustoRodoviasUtils.custoPorRodovia;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import br.com.softplan.test.custotransporte.models.CalculoCusto;

public class UtilsTests {

	private CalculoCusto custo;
	
	@Before
	public void setup() {
		custo = umCalculoCusto().agora();
	}
	
	@Test
	public void deveCalcularCustoPorExcessoCargaQuandoToneladasUltrapassaremLimiteDe5() {
		// ação
		BigDecimal custoExcesso = calcularCustoPorExcessoCarga(new BigDecimal("20.00"), 10, 25);
		
		// validação
		assertThat(custoExcesso, is(new BigDecimal("22.50")));
	}
	
	@Test
	public void naoDeveCalcularCustoPorExcessoCargaQuandoToneladas_Nao_UltrapassaremLimiteDe5() {
		// ação
		BigDecimal custoExcesso = calcularCustoPorExcessoCarga(new BigDecimal("20.00"), 5, 25);
		
		// validação
		assertThat(custoExcesso, is(new BigDecimal("20.00")));
	}
	
	@Test
	public void deveCalcularCustoZeroQuandoNaoHaDistanciaParaRodovia() {
		// cenário
		custo = umCalculoCusto().semDistanciaEmRodoviaPavimentada().agora();
		
		// ação
		BigDecimal custoZero = custoPorRodovia(RODOVIA_PAVIMENTADA, custo.getDistanciaRodoviaPav());
		
		// validação
		assertThat(custoZero, is(BigDecimal.ZERO.setScale(2)));
	}
	
	@Test
	public void deveCalcularCustoQuandoHaDistanciaParaRodovia() {
		// ação
		BigDecimal custoZero = custoPorRodovia(RODOVIA_PAVIMENTADA, custo.getDistanciaRodoviaPav());
		
		// validação
		assertThat(custoZero, is(new BigDecimal("24.30")));
	}

}
