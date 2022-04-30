package com.jal.wholesales.dao.service;


import java.util.List;

import com.company.wholesales.service.MarcaService;
import com.company.wholesales.service.impl.MarcaServiceImpl;
import com.jal.wholesales.model.Marca;
import com.wholesales.exception.InstanceException;
import com.wholesales.exception.ServiceException;

public class MarcaServiceTest {
private MarcaService marcaService = null;
	public MarcaServiceTest() {
		marcaService= new MarcaServiceImpl();	
	}
 
	
	public void testFindById() throws ServiceException {
		
		System.out.println("Testing findById...");	        
		Marca marca= marcaService.findById(1);
		System.out.println(marca.getId());
		System.out.println(marca.getNombre());

	}
	public void testFindByAll() throws ServiceException {
		
		System.out.println("Testing findByAll...");	        
		List<Marca>marca= marcaService.findByAll();
		
		System.out.println(marca);

	}
	public void testCreate() {
		System.out.println("Testing Create...");
		Marca marca = null;
		marca = new Marca();
	 
 
		marca.setNombre("Adidas" );
	 
		
	 
		 
		System.out.println("Creando marca "+marca.getId());
		try {
			
			marcaService.create(marca);
			System.out.println("Marca Creada");
		} 
		
		
		catch (InstanceException uae) {		
			System.out.println("La marca ya existe.");
			uae.printStackTrace();
		} catch (ServiceException se) {
			System.out.println(se.getMessage());
			se.printStackTrace();
		}
	}
	
	public static void main(String args[] ) throws ServiceException {
		MarcaServiceTest test = new MarcaServiceTest();
//			test.testCreate();
//		test.testFindById();
		test.testFindByAll();
			/*test.testSignUp();*/
	}
	
}
