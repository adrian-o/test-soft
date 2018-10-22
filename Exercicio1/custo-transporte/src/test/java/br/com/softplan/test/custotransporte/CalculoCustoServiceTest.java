package br.com.softplan.test.custotransporte;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import br.com.softplan.test.custotransporte.builders.CalculoCustoBuilder;
import br.com.softplan.test.custotransporte.exceptions.DistanciaRodoviasNaoPreenchidasException;
import br.com.softplan.test.custotransporte.exceptions.ServiceException;
import br.com.softplan.test.custotransporte.models.CalculoCusto;
import br.com.softplan.test.custotransporte.services.CalculoServiceImpl;

public class CalculoCustoServiceTest {

	private CalculoServiceImpl service;
	
	private CalculoCusto calculoCusto;
	
	private static Validator validator;
	
	@Before
	public void setup() {
		service = new CalculoServiceImpl();
		calculoCusto = CalculoCustoBuilder.umCalculoCusto().agora();
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
	}
	
	@Test
	public void deveCalcularCustoTransporte() throws ServiceException {
		// ação
		this.service.calculaValorTotal(calculoCusto);
		
		// validação
		assertThat(calculoCusto.getValorTotal(), is(new BigDecimal("39.80")));
	}
	
	@Test
	public void naoDeveCalcularCustoSemDistanciaEmPeloMenosUmaRodovia() throws DistanciaRodoviasNaoPreenchidasException {
		// cenário
		calculoCusto = CalculoCustoBuilder.umCalculoCusto()
				.semDistanciaEmRodoviaNaoPavimentada()
				.semDistanciaEmRodoviaPavimentada()
				.agora();
		
		// ação
		try {
			this.service.calculaValorTotal(calculoCusto);
			fail();
		} catch (ServiceException se) {
			assertThat(se.getMessage(), is("Você deve preencher ao menos um dos valores de rodovias para cálculos"));
		}
	}
	
	@Test
	public void naoDeveCalcularCustoSemCargaEmToneladas() {
		// cenário
		calculoCusto = CalculoCustoBuilder.umCalculoCusto()
				.semCargaEmToneladas()
				.agora();
		
		// ação
		Set<ConstraintViolation<CalculoCusto>> violations = validator.validate(calculoCusto);
        assertFalse(violations.isEmpty());
	}
	
	@Test
	public void deveAcrescentarCustoSe_CargaMaiorQueLimiteToneladas() throws ServiceException {
		// cenário
		calculoCusto = CalculoCustoBuilder.umCalculoCusto()
				.comCargaMaiorQue5Toneladas()
				.agora();
		
		// ação
		this.service.calculaValorTotal(calculoCusto);
		
		// validação
		assertThat(calculoCusto.getValorTotal(), is(new BigDecimal("41.20")));
	}
}
