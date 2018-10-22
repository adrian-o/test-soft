package br.com.softplan.test.custotransporte.exceptions;

public class DistanciaRodoviasNaoPreenchidasException extends Exception {

	private static final long serialVersionUID = -8275155242199282988L;

	public DistanciaRodoviasNaoPreenchidasException() {
		super();
	}

	public DistanciaRodoviasNaoPreenchidasException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public DistanciaRodoviasNaoPreenchidasException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DistanciaRodoviasNaoPreenchidasException(String arg0) {
		super(arg0);
	}

	public DistanciaRodoviasNaoPreenchidasException(Throwable arg0) {
		super(arg0);
	}
}
