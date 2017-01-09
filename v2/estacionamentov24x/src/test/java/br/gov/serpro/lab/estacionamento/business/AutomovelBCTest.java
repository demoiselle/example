package br.gov.serpro.lab.estacionamento.business;

import static org.junit.Assert.*;
import java.util.*;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import br.gov.serpro.lab.estacionamento.domain.*;
import br.gov.serpro.lab.estacionamento.business.AutomovelBC;

@RunWith(DemoiselleRunner.class)
public class AutomovelBCTest {

    @Inject
	private AutomovelBC automovelBC;
	
	@Before
	public void before() {
		for (Automovel automovel : automovelBC.findAll()) {
			automovelBC.delete(automovel.getId());
		}
	}	
	
	
	@Test
	public void testInsert() {
				
		Automovel automovel = new Automovel("marca","modelo","AAAA-0000",AutomovelTamanho.LARGE,AutomovelTipo.CAR,null);
		automovelBC.insert(automovel);
		List<Automovel> listOfAutomovel = automovelBC.findAll();
		assertNotNull(listOfAutomovel);
		assertEquals(1, listOfAutomovel.size());
	}	
	
	@Test
	public void testDelete() {
		
		Automovel automovel = new Automovel("marca","modelo","AAAA-0000",AutomovelTamanho.LARGE,AutomovelTipo.CAR,null);
		automovelBC.insert(automovel);
		
		List<Automovel> listOfAutomovel = automovelBC.findAll();
		assertNotNull(listOfAutomovel);
		assertEquals(1, listOfAutomovel.size());
		
		automovelBC.delete(automovel.getId());
		listOfAutomovel = automovelBC.findAll();
		assertEquals(0, listOfAutomovel.size());
	}
	
	@Test
	public void testUpdate() {

		Automovel automovel = new Automovel("marca","modelo","AAAA-0000",AutomovelTamanho.LARGE,AutomovelTipo.CAR,null);
		automovelBC.insert(automovel);
		
		List<Automovel> listOfAutomovel = automovelBC.findAll();
		Automovel automovel2 = (Automovel)listOfAutomovel.get(0);
		assertNotNull(listOfAutomovel);

		automovel2.setMarca("NovaMarca");
		automovelBC.update(automovel2);
		
		listOfAutomovel = automovelBC.findAll();
		Automovel automovel3 = (Automovel)listOfAutomovel.get(0);
		
		assertEquals("NovaMarca", automovel3.getMarca());
	}

}