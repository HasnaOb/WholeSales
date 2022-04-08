package com.jal.wholesales.dao.service;

import com.company.wholesales.service.LocalidadService;
import com.company.wholesales.service.impl.LocalidadServiceImpl;
import com.jal.wholesales.model.Localidad;
import com.wholesales.exception.InstanceException;
import com.wholesales.exception.ServiceException;

public class LocalidadServiceTest {

	private LocalidadService localidadService = null;
	public LocalidadServiceTest() {
		localidadService= new LocalidadServiceImpl();	
	}
	
	public void testFindById() throws ServiceException {
		
		System.out.println("Testing findById...");	        
		Localidad localidad= localidadService.findById(1);
		System.out.println(localidad.getId());
		System.out.println(localidad.getNombre());
		System.out.println(localidad.getIdProvincia());

	}
	
	 
	
	
	public void testCreate() {
		System.out.println("Testing Create...");
		Localidad localidad = null;
		localidad = new Localidad();
	 
 
		localidad.setNombre("Taboada" );
		localidad.setIdProvincia(1);
		 
		
	 
		 
		System.out.println("Creando localidad "+localidad.getId());
		try {
			
			localidadService.create( localidad);
			System.out.println("Localidad Creado");
		} 
		
		
		catch (InstanceException uae) {		
			System.out.println("La localidad ya existe.");
			uae.printStackTrace();
		} catch (ServiceException se) {
			System.out.println(se.getMessage());
			se.printStackTrace();
		}
	}
	
	public static void main(String args[] ) throws ServiceException {
		LocalidadServiceTest test = new LocalidadServiceTest();
		/*	test.testCreate();*/
			test.testFindById();
			 
	}
	

}
