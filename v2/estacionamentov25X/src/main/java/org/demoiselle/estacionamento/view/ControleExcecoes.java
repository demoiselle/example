package org.demoiselle.estacionamento.view;

import java.io.Serializable;

import javax.inject.Inject;

import org.demoiselle.estacionamento.exception.TesteException;

import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;

@ViewController
public class ControleExcecoes implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private MessageContext messageContext;
	
	
	public void chamaExcecao() {
		throw new TesteException();		
	}
	
	@ExceptionHandler
	public void handleUsedException(final Exception cause) {		
		messageContext.add("teste", SeverityType.ERROR);
	}

}
