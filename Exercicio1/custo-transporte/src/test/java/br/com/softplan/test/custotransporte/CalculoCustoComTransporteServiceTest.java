package br.com.softplan.test.custotransporte;

import static br.com.softplan.test.custotransporte.builders.CalculoCustoBuilder.umCalculoCusto;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.com.softplan.test.custotransporte.exceptions.ServiceException;
import br.com.softplan.test.custotransporte.models.CalculoCusto;
import br.com.softplan.test.custotransporte.models.domains.TipoVeiculoEnum;
import br.com.softplan.test.custotransporte.services.CalculoServiceImpl;
import br.com.softplan.test.custotransporte.utils.CustoVeiculoUtils;

@RunWith(Parameterized.class)
public class CalculoCustoComTransporteServiceTest {

	private CalculoServiceImpl service;
	
	@Parameter
	public CalculoCusto calculoCusto;
	
	@Parameter(value=1)
	public String custo;
	
	@Parameter(value=2)
	public String custoPorVeiculo;
	
	@Parameter(value=3)
	public String cenario;
	
	@Before
	public void setup() {
		service = new CalculoServiceImpl();
	}
	
	private static CalculoCusto custoBau = 
			umCalculoCusto().comTipoVeiculo(TipoVeiculoEnum.CAMINHAO_BAU).comCargaMaiorQue5Toneladas().agora();
	private static CalculoCusto custoCacamba = 
			umCalculoCusto().comTipoVeiculo(TipoVeiculoEnum.CAMINHAO_CACAMBA).comCargaMaiorQue5Toneladas().agora();
	private static CalculoCusto custoCarreta = 
			umCalculoCusto().comTipoVeiculo(TipoVeiculoEnum.CARRETA).comCargaMaiorQue5Toneladas().agora();
	
	@Parameters(name="{3}")
	public static Collection<Object[]> getParametros(){
		return Arrays.asList(new Object[][] {
			{custoBau, "41.20", "10.00", "Caminhão Baú"},
			{custoCacamba, "43.18", "10.50", "Caminhão Caçamba"},
			{custoCarreta, "45.98", "11.20", "Carreta"}
		});
	}
	
	@Test
	public void deveAcrescentarCustoQuando_CargaMaiorQueLimiteToneladas() throws ServiceException {
		// ação
		this.service.calculaValorTotal(calculoCusto);
		
		// validação
		assertThat(calculoCusto.getValorTotal(), is(new BigDecimal(custo)));
	}
	
	@Test
	public void deveRecalcularCustoDeAcordoComFatorDeRecalculoPorTipoVeiculo() throws ServiceException {
		// ação
		BigDecimal custoAposFator = CustoVeiculoUtils.recalcularCustoPorFatorVeiculo(new BigDecimal("10.00"), calculoCusto.getTipoVeiculo());
		
		// validação
		assertThat(custoAposFator, is(new BigDecimal(custoPorVeiculo)));
	}
}
