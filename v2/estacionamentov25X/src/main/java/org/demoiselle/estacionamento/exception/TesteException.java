package org.demoiselle.estacionamento.exception;

import br.gov.frameworkdemoiselle.exception.ApplicationException;
import br.gov.frameworkdemoiselle.message.SeverityType;

@ApplicationException(rollback = true, severity = SeverityType.ERROR)
public class TesteException extends RuntimeException {

     private static final long serialVersionUID = 1L;

     public TesteException() {
         super();
     }

     TesteException(String message) {
         super(message);
     }

}