package com.jal.wholesales.dao.service;
import java.util.List;

import com.company.wholesales.service.CategoriaService;
import com.company.wholesales.service.impl.CategoriaServiceImpl;
import com.jal.wholesales.model.Categoria;
import com.jal.wholesales.service.CategoriaCriteria;
import com.wholesales.exception.DataException;
import com.wholesales.exception.InstanceException;

import com.wholesales.exception.ServiceException;

public class CategoriaServiceTest {
	
	private CategoriaService categoriaService = null;
	
	public CategoriaServiceTest() {
		categoriaService= new CategoriaServiceImpl();	
	}
	
	
	public void testFindByCriteria() throws DataException {
		System.out.println("Testing findByCriteria...");
		System.out.println();

		CategoriaCriteria cc = new CategoriaCriteria();
		
	 
		 
		 cc.setNombre("Zapatos");
		 cc.setIdCategoriaPadre(null);
	 
		 
		
		List<Categoria> categoria = categoriaService.findByCriteria(cc);
		System.out.println(categoria);
		System.out.println(cc.getNombre());
		System.out.println(cc.getIdCategoriaPadre());
	}
	
	
	
	public void testCreate() {
		System.out.println("Testing Create...");
		Categoria categoria = null;
		categoria = new Categoria();
	 
 
		categoria.setNombre("Mesas" );
		categoria.setIdCategoriaPadre( 1);
		
	 
		 
		System.out.println("Creando categoria "+categoria.getId());
		try {
			
			categoriaService.create( categoria);
			System.out.println("Categoria Creado");
		} 
		
		
		catch (InstanceException uae) {		
			System.out.println("La categoria ya existe.");
			uae.printStackTrace();
		} catch (ServiceException se) {
			System.out.println(se.getMessage());
			se.printStackTrace();
		}
	}
	public void testFindById() throws ServiceException {
		
		System.out.println("Testing findById...");	        
		Categoria categoria= categoriaService.findById(1);
		System.out.println(categoria.getId());
		System.out.println(categoria.getNombre());
		System.out.println(categoria.getIdCategoriaPadre());

	}
	
	
	
	/*public void testDelete() {
		System.out.println("Testing Delete...");
		Categoria categoria = null;
		categoria = new Categoria();
	 
 
		 
		
	 
		 
		System.out.println("Creando categoria "+categoria.getId());
		try {
			
			categoriaService.create(null, categoria);
			System.out.println("Categoria Creado");
		} 
		
		
		catch (InstanceException uae) {		
			System.out.println("La categoria ya existe.");
			uae.printStackTrace();
		} catch (ServiceException se) {
			System.out.println(se.getMessage());
			se.printStackTrace();
		}
	}*/
	public static void main(String args[] ) throws ServiceException {
		CategoriaServiceTest test = new CategoriaServiceTest();
			/*test.testCreate();*/
			test.testFindById();
			test.testFindByCriteria();
	}
	
}


 
