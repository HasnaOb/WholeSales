package com.jal.wholesales.dao.service;

 
import java.sql.Connection;
import java.util.List;

import com.company.wholesales.service.ProductoService;
import com.company.wholesales.service.impl.ProductoServiceImpl;
import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.Producto;
import com.jal.wholesales.service.ProductoCriteria;
import com.wholesales.exception.DataException;
import com.wholesales.exception.InstanceException;
import com.wholesales.exception.ServiceException;

public class ProductoServiceTest {
	
	private ProductoService productoService = null;
	public ProductoServiceTest() {
		productoService= new ProductoServiceImpl();	
	}
	
	public void testFindById() throws ServiceException {
		
		System.out.println("Testing findById...");	        
		Producto producto= productoService.findById(7);
		System.out.println(producto.getId());
		System.out.println(producto.getNombre());
		System.out.println(producto.getPrecio()+"€");

	}
	
	
	public  void TestFindByNombre (String nombre) throws DataException {
		System.out.println("Testing findByNombre...");
		System.out.println();
		
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			List <Producto> producto = productoService.findByNombre(nombre);
			
			for(Producto p: producto) {
				System.out.println(p.getNombre());
				System.out.println(p.getPrecio());
				System.out.println(p.getDescripcion());
			 
	 
			}
			
		 
			
		} catch (Exception e) {
			 
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConnection(c);
		}
	}
	
	public void testFindByCriteria() throws DataException {
		System.out.println("Testing findByCriteria...");
		System.out.println();

		ProductoCriteria pc = new ProductoCriteria();
		
		pc.setIdEmpresa(2L);
		pc.setNombre("zapatos");
		pc.setIdCategoria(12L);
		pc.setIdMarca(4L);
		pc.setIdSeccion(3L);
		pc.setIdPais(7L);
		pc.setPrecio(200.0d);
		pc.setDescripcion("zapatos numer 34");
		
		
		 
	 

			List<Producto> producto = productoService.findByCriteria(pc);
			System.out.println(producto);
			System.out.println(pc.getNombre());
			System.out.println();

	 
	}


	 
	
	
	public void testCreate() {
		System.out.println("Testing Create...");
		Producto producto = null;
		producto = new Producto();
	 
 
		producto.setNombre("Silla" );
		producto.setDescripcion("Silla Gaming");
		producto.setIdCategoria(2);
		producto.setIdEmpresa(2);
		producto.setIdMarca(3);
		producto.setPrecio(75);
		producto.setIdPais(1);
		producto.setIdSeccion(3);
	 
		
	 
		 
		System.out.println("Creando producto "+producto.getId());
		try {
			
			productoService.create( producto);
			System.out.println("Producto Creado");
		} 
		
		
		catch (InstanceException uae) {		
			System.out.println("El producto ya existe.");
			uae.printStackTrace();
		} catch (ServiceException se) {
			System.out.println(se.getMessage());
			se.printStackTrace();
		}
	}
	
	public static void main(String args[] ) throws ServiceException {
		ProductoServiceTest test = new ProductoServiceTest();
//		test.testCreate();
//			test.testFindById();
			test.testFindByCriteria();
//			test.TestFindByNombre("Bolso");
			 
	}

 
}
