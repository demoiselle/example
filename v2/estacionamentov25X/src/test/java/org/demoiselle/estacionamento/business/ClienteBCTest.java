package org.demoiselle.estacionamento.business;

import static org.junit.Assert.*;

import java.util.*;

import javax.inject.Inject;

import org.demoiselle.estacionamento.business.ClienteBC;
import org.demoiselle.estacionamento.domain.Cliente;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;

//
//Para executar os testes sem erro de segurança altere no arquivo demoiselle.properties o valor da propriedade frameworkdemoiselle.security.enabled para falso:
//frameworkdemoiselle.security.enabled=false
//

@RunWith(DemoiselleRunner.class)
public class ClienteBCTest {

    @Inject
	private ClienteBC clienteBC;
	
	@Before
	public void before() {
		for (Cliente cliente : clienteBC.findAll()) {
			clienteBC.delete(cliente.getId());
		}
	}	
	
	
	@Test
	public void testInsert() {
				
		Cliente cliente = new Cliente("nome","11111111111","email@email.mail","034400310515","1","111111111111",null,null);
		clienteBC.insert(cliente);
		List<Cliente> listOfCliente = clienteBC.findAll();
		assertNotNull(listOfCliente);
		assertEquals(1, listOfCliente.size());
	}	
	
	@Test
	public void testDelete() {
		
		Cliente cliente = new Cliente("nome","11111111111","email@email.mail","034400310515","1","111111111111",null,null);
		clienteBC.insert(cliente);
		
		List<Cliente> listOfCliente = clienteBC.findAll();
		assertNotNull(listOfCliente);
		assertEquals(1, listOfCliente.size());
		
		clienteBC.delete(cliente.getId());
		listOfCliente = clienteBC.findAll();
		assertEquals(0, listOfCliente.size());
	}
	
	@Test
	public void testUpdate() {
		
		Cliente cliente = new Cliente("nome","11111111111","email@email.mail","034400310515","1","111111111111",null,null);
		clienteBC.insert(cliente);
		
		List<Cliente> listOfCliente = clienteBC.findAll();
		Cliente cliente2 = (Cliente)listOfCliente.get(0);
		assertNotNull(listOfCliente);

		cliente2.setNome("novo valor");
		clienteBC.update(cliente2);
		
		listOfCliente = clienteBC.findAll();
		Cliente cliente3 = (Cliente)listOfCliente.get(0);
		
		assertEquals("novo valor", cliente3.getNome());
	}

}