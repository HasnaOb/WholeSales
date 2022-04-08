package com.jal.wholesales.dao.service;

import com.company.wholesales.service.SeccionService;
import com.company.wholesales.service.impl.SeccionServiceImpl;
import com.jal.wholesales.model.Seccion;
import com.wholesales.exception.ServiceException;

public class SeccionServiceTest {

	private SeccionService seccionService = null;
	public SeccionServiceTest() {
		seccionService= new SeccionServiceImpl();	
	}
	
	public void testFindById() throws ServiceException {
		
		System.out.println("Testing findById...");	        
		Seccion seccion= seccionService.findById(2);
		System.out.println(seccion.getId());
		System.out.println(seccion.getNombre());
	

	}
	
	 
	
	
	
	public static void main(String args[] ) throws ServiceException {
		SeccionServiceTest test = new SeccionServiceTest();
		/*	test.testCreate();*/
			test.testFindById();
			 
	}
	

}
