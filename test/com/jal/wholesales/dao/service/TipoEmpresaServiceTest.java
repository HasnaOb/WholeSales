package com.jal.wholesales.dao.service;

import com.company.wholesales.service.TipoEmpresaService;
import com.company.wholesales.service.impl.TipoEmpresaServiceImpl;
import com.jal.wholesales.model.TipoEmpresa;
import com.wholesales.exception.ServiceException;

public class TipoEmpresaServiceTest {

	private TipoEmpresaService tipoEmpresaService = null;
	public TipoEmpresaServiceTest() {
		tipoEmpresaService= new TipoEmpresaServiceImpl();	
	}
	
	public void testFindById() throws ServiceException {
		
		System.out.println("Testing findById...");	        
		TipoEmpresa tipoEmpresa= tipoEmpresaService.findById(1);
		System.out.println(tipoEmpresa.getId());
		System.out.println(tipoEmpresa.getNombre());
		 

	}
	
	 
	
	

	
	public static void main(String args[] ) throws ServiceException {
		TipoEmpresaServiceTest test = new TipoEmpresaServiceTest();
	
			test.testFindById();
			 
	}
	

}
