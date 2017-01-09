

package br.gov.serpro.lab.estacionamento.business;

import static org.junit.Assert.*;
import java.util.*;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import br.gov.serpro.lab.estacionamento.domain.Patio;
import br.gov.serpro.lab.estacionamento.business.PatioBC;

@RunWith(DemoiselleRunner.class)
public class PatioBCTest {

    @Inject
	private PatioBC patioBC;
	
	@Before
	public void before() {
		for (Patio patio : patioBC.findAll()) {
			patioBC.delete(patio.getId());
		}
	}	
	
	
	@Test
	public void testInsert() {
				
		Patio patio = new Patio("local","00000-000");
		patioBC.insert(patio);
		List<Patio> listOfPatio = patioBC.findAll();
		assertNotNull(listOfPatio);
		assertEquals(1, listOfPatio.size());
	}	
	
	@Test
	public void testDelete() {
		
		Patio patio = new Patio("local","00000-000");
		patioBC.insert(patio);
		
		List<Patio> listOfPatio = patioBC.findAll();
		assertNotNull(listOfPatio);
		assertEquals(1, listOfPatio.size());
		
		patioBC.delete(patio.getId());
		listOfPatio = patioBC.findAll();
		assertEquals(0, listOfPatio.size());
	}
	
	@Test
	public void testUpdate() {

		Patio patio = new Patio("local","00000-000");
		patioBC.insert(patio);
		
		List<Patio> listOfPatio = patioBC.findAll();
		Patio patio2 = (Patio)listOfPatio.get(0);
		assertNotNull(listOfPatio);

		patio2.setLocal("novo valor");
		patioBC.update(patio2);
		
		listOfPatio = patioBC.findAll();
		Patio patio3 = (Patio)listOfPatio.get(0);
		
		assertEquals("novo valor", patio3.getLocal());
	}

}