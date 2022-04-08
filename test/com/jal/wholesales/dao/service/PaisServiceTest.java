package com.jal.wholesales.dao.service;

import com.company.wholesales.service.PaisService;
import com.company.wholesales.service.impl.PaisServiceImpl;
import com.jal.wholesales.model.Pais;
import com.wholesales.exception.InstanceException;
import com.wholesales.exception.ServiceException;

public class PaisServiceTest {


	private PaisService paisService = null;
	public PaisServiceTest() {
		paisService= new PaisServiceImpl();	
	}
	
	public void testFindById() throws ServiceException {
		
		System.out.println("Testing findById...");	        
		Pais pais= paisService.findById(7);
		System.out.println(pais.getId());
		System.out.println(pais.getNombre());
		 

	}
	
	 
	
	
	public void testCreate() {
		System.out.println("Testing Create...");
		Pais pais = null;
		pais = new Pais();
	 
 
		pais.setNombre("Ecuador" );
		 
		 
		
	 
		 
		System.out.println("Creando pais "+pais.getId());
		try {
			
			paisService.create( pais);
			System.out.println("Pais Creado");
		} 
		
		
		catch (InstanceException uae) {		
			System.out.println("El pais ya existe.");
			uae.printStackTrace();
		} catch (ServiceException se) {
			System.out.println(se.getMessage());
			se.printStackTrace();
		}
	}
	
	public static void main(String args[] ) throws ServiceException {
		PaisServiceTest test = new PaisServiceTest();
//			test.testCreate();
			test.testFindById();
			 
	}
	

}
