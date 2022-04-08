package com.jal.wholesales.dao.service;
import com.company.wholesales.service.DireccionService;
import com.company.wholesales.service.impl.DireccionServiceImpl;
import com.jal.wholesales.model.Direccion;
import com.wholesales.exception.ServiceException;

public class DireccionServiceTest {
	private DireccionService direccionService = null;
	public DireccionServiceTest() {
		direccionService= new DireccionServiceImpl();	
	}
	 
	
	public void testFindById() throws ServiceException {
		
		System.out.println("Testing findById...");	        
		Direccion direccion= direccionService.findById(5);
		System.out.println(direccion.getId());
		System.out.println(direccion.getCodigoPostal());
		System.out.println(direccion.getIdLocalidad());

	} 
	public static void main(String args[] ) throws ServiceException {
		DireccionServiceTest test = new DireccionServiceTest();
			/*test.testCreate();*/
			test.testFindById();
			/*test.testSignUp();*/
	}
	

}
