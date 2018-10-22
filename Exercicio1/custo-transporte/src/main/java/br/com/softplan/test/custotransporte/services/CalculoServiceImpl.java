package br.com.softplan.test.custotransporte.services;

import static br.com.softplan.test.custotransporte.utils.CustoCargaUtils.calcularCustoPorExcessoCarga;
import static br.com.softplan.test.custotransporte.utils.CustoRodoviasUtils.custoPorRodovia;
import static br.com.softplan.test.custotransporte.utils.CustoVeiculoUtils.recalcularCustoPorFatorVeiculo;
import static br.com.softplan.test.custotransporte.utils.NumberUtils.adicionar;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.softplan.test.custotransporte.exceptions.DistanciaRodoviasNaoPreenchidasException;
import br.com.softplan.test.custotransporte.exceptions.ServiceException;
import br.com.softplan.test.custotransporte.models.CalculoCusto;
import br.com.softplan.test.custotransporte.models.domains.TipoRodoviaEnum;

@Service
public class CalculoServiceImpl implements CalculoService {
	
	@Override
	public void calculaValorTotal(CalculoCusto calculoCusto) throws ServiceException {
		try {
			this.validaPreenchimento(calculoCusto);
			
			BigDecimal custoTotal = new BigDecimal("0").setScale(2);
			
			for(TipoRodoviaEnum rodovia : TipoRodoviaEnum.values()) {
				BigDecimal custoRodovia = custoPorRodovia(rodovia, calculoCusto);
				custoRodovia = recalcularCustoPorFatorVeiculo(custoRodovia, calculoCusto);
				custoRodovia = calcularCustoPorExcessoCarga(custoRodovia, rodovia,  calculoCusto);
				
				custoTotal = adicionar(custoTotal, custoRodovia);
			}
			
			calculoCusto.setValorTotal(custoTotal);
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