package br.com.softplan.test.custotransporte.services;

import static br.com.softplan.test.custotransporte.models.domains.TipoRodoviaEnum.RODOVIA_NAO_PAVIMENTADA;
import static br.com.softplan.test.custotransporte.models.domains.TipoRodoviaEnum.RODOVIA_PAVIMENTADA;
import static br.com.softplan.test.custotransporte.utils.CustoCargaUtils.calcularCustoPorExcessoCarga;
import static br.com.softplan.test.custotransporte.utils.CustoRodoviasUtils.custoPorRodovia;
import static br.com.softplan.test.custotransporte.utils.CustoVeiculoUtils.recalcularCustoPorFatorVeiculo;
import static br.com.softplan.test.custotransporte.utils.NumberUtils.adicionar;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.softplan.test.custotransporte.exceptions.DistanciaRodoviasNaoPreenchidasException;
import br.com.softplan.test.custotransporte.exceptions.ServiceException;
import br.com.softplan.test.custotransporte.models.CalculoCusto;

@Service
public class CalculoServiceImpl implements CalculoService {
	
	@Override
	public void calculaValorTotal(CalculoCusto calculoCusto) throws ServiceException {
		try {
			this.validaPreenchimento(calculoCusto);
			
			BigDecimal custoPav = custoPorRodovia(RODOVIA_PAVIMENTADA, calculoCusto.getDistanciaRodoviaPav());
			BigDecimal custoNaoPav = custoPorRodovia(RODOVIA_NAO_PAVIMENTADA, calculoCusto.getDistanciaRodoviaNaoPav());
			
			custoPav = recalcularCustoPorFatorVeiculo(custoPav, calculoCusto.getTipoVeiculo());
			custoNaoPav = recalcularCustoPorFatorVeiculo(custoNaoPav, calculoCusto.getTipoVeiculo());
			
			custoPav = calcularCustoPorExcessoCarga(custoPav, calculoCusto.getCargaTon(), calculoCusto.getDistanciaRodoviaPav());
			custoNaoPav = calcularCustoPorExcessoCarga(custoNaoPav, calculoCusto.getCargaTon(), calculoCusto.getDistanciaRodoviaNaoPav());
			
			calculoCusto.setValorTotal(adicionar(custoPav, custoNaoPav));
		} catch (DistanciaRodoviasNaoPreenchidasException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	private void validaPreenchimento(CalculoCusto calculoCusto) throws DistanciaRodoviasNaoPreenchidasException {
		if (calculoCusto.getDistanciaRodoviaPav() == 0 && calculoCusto.getDistanciaRodoviaNaoPav() == 0) {
			throw new DistanciaRodoviasNaoPreenchidasException("Você deve preencher ao menos um dos valores de rodovias para cálculos");
		}
	}
}
