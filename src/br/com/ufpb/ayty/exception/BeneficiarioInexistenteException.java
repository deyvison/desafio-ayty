package br.com.ufpb.ayty.exception;

public class BeneficiarioInexistenteException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BeneficiarioInexistenteException(String msg){
		super(msg);
	}
}
