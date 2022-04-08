package com.jal.wholesales.dao.service;

import com.company.wholesales.service.TipoEstadoService;
import com.company.wholesales.service.impl.TipoEstadoServiceImpl;
import com.jal.wholesales.model.TipoEstado;
import com.wholesales.exception.ServiceException;

public class TipoEstadoServiceTest {
	private TipoEstadoService tipoEstadoService = null;
	public TipoEstadoServiceTest() {
		tipoEstadoService= new TipoEstadoServiceImpl();	
	}
	
	public void testFindById() throws ServiceException {
		
		System.out.println("Testing findById...");	        
		TipoEstado tipoEstado= tipoEstadoService.findById(7);
		System.out.println(tipoEstado.getId());
		System.out.println(tipoEstado.getNombre());
		 

	}
	
	 
	
	

	
	public static void main(String args[] ) throws ServiceException {
		TipoEstadoServiceTest test = new TipoEstadoServiceTest();
	
			test.testFindById();
			 
	}
	


}
