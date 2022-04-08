package com.jal.wholesales.dao.service;

import com.company.wholesales.service.PedidoService;
import com.company.wholesales.service.impl.PedidoServiceImpl;
import com.jal.wholesales.model.PedidoDTO;
import com.wholesales.exception.InstanceException;
import com.wholesales.exception.ServiceException;

public class PedidoServiceTest {

	private PedidoService pedidoService = null;
	public PedidoServiceTest() {
		pedidoService= new PedidoServiceImpl();	
	}
	
	public void testFindById() throws ServiceException {
		
		System.out.println("Testing findById...");	        
		PedidoDTO pedido= pedidoService.findById(1);
		System.out.println(pedido.getId());
		

	}
	
	 
	
	
	public void testCreate() {
		System.out.println("Testing Create...");
		PedidoDTO pedido = null;
		pedido = new PedidoDTO();
		 
	 
 
		pedido.setIdDireccion((long) 1);
		pedido.setIdEmpresa((long) 1);
		
		 
		
	 
		 
		System.out.println("Creando producto "+pedido.getId());
		try {
			
			pedidoService.create( pedido);
			System.out.println("Linea de pedido creada");
		} 
		
		
		catch (InstanceException uae) {		
			System.out.println("La linea de pedido ya existe.");
			uae.printStackTrace();
		} catch (ServiceException se) {
			System.out.println(se.getMessage());
			se.printStackTrace();
		}
	}
	
	public static void main(String args[] ) throws ServiceException {
		PedidoServiceTest test = new PedidoServiceTest();
		/*test.testCreate();*/
			test.testFindById();
			 
	}
	

}
