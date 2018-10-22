package br.com.softplan.test.custotransporte.services;

import br.com.softplan.test.custotransporte.exceptions.ServiceException;
import br.com.softplan.test.custotransporte.models.CalculoCusto;

public interface CalculoService {
	
	public void calculaValorTotal(CalculoCusto calculoCusto) throws ServiceException;
}
