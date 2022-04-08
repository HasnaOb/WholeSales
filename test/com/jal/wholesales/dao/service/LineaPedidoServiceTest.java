package com.jal.wholesales.dao.service;

import java.util.Calendar;

import com.company.wholesales.service.LineaPedidoService;
import com.company.wholesales.service.impl.LineaPedidoServiceImpl;
import com.jal.wholesales.model.LineaPedido;
import com.wholesales.exception.InstanceException;
import com.wholesales.exception.ServiceException;

public class LineaPedidoServiceTest {

	private LineaPedidoService lineaPedidoService = null;
	public LineaPedidoServiceTest() {
		lineaPedidoService= new LineaPedidoServiceImpl();	
	}
	
	public void testFindById() throws ServiceException {
		
		System.out.println("Testing findById...");	        
		LineaPedido lineaPedido= lineaPedidoService.findById(1);
		System.out.println(lineaPedido.getId());
		System.out.println(lineaPedido.getFechaCreacion());
		System.out.println(lineaPedido.getPrecio()+"€");

	}
	
	 
	
	
	public void testCreate() {
		System.out.println("Testing Create...");
		LineaPedido lineaPedido = null;
		lineaPedido = new LineaPedido();
		Calendar c = Calendar.getInstance();		
		c.set(Calendar.YEAR, c.get(Calendar.YEAR)-20);

	 
 
		lineaPedido.setIdProducto(7);
		lineaPedido.setIdPedido(1);
		lineaPedido.setUnidades(2);
		lineaPedido.setPrecio(75);
		lineaPedido.setTotal(150);
		lineaPedido.setFechaCreacion(c.getTime());
	 
		
	 
		 
		System.out.println("Creando producto "+lineaPedido.getId());
		try {
			
			lineaPedidoService.create( lineaPedido);
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
		LineaPedidoServiceTest test = new LineaPedidoServiceTest();
		/*test.testCreate();*/
			test.testFindById();
			 
	}
	

}
