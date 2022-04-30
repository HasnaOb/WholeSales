package com.jal.wholesales.dao.service;


import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.company.wholesales.service.EmpresaService;
import com.company.wholesales.service.impl.EmpresaServiceImpl;
import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.Empresa;
import com.jal.wholesales.service.EmpresaCriteria;
import com.wholesales.exception.DataException;
import com.wholesales.exception.InstanceException;
import com.wholesales.exception.MailException;
import com.wholesales.exception.ServiceException;

public class EmpresaServiceTest {
	 

	private static Logger logger = LogManager.getLogger(EmpresaServiceTest.class);	
	
private EmpresaService empresaService = null;
	Empresa empresa=null;
	public EmpresaServiceTest() {
		empresaService= new EmpresaServiceImpl();	
	}
	 
	public void testFindById() throws ServiceException {
		
	logger.trace("Begin.......");
	
	Long idEmpresa= 1L;
	
//	try {
//		logger.info("Found"+empresaService.findById(idEmpresa));
//		logger.
//	}
		Empresa empresa= empresaService.findById(3);
		System.out.println(empresa.getId());
		System.out.println(empresa.getNombre());
		System.out.println(empresa.getNombreUsuario());
		System.out.println(empresa.getCif());

	}
 
	 
	 
 	
	public void testFindByEmail() throws ServiceException {
		
		System.out.println("Testing findByEmail...");	        
		Empresa empresa= empresaService.findByEmail("tomas12@gmail.com");
		System.out.println(empresa.getId());
		System.out.println(empresa.getNombre());
		System.out.println(empresa.getNombreUsuario());
		System.out.println(empresa.getCif());

	}
	
	public void findByNombre (String nombre) throws DataException {
		System.out.println("Testing findByNombre...");
		System.out.println();
		
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			List <Empresa> empresas = empresaService.findByNombre(nombre);
			
			for(Empresa e: empresas) {
				System.out.println(e.getNombre());
				System.out.println(e.getNombreUsuario());
				System.out.println(e.getEmail());
				System.out.println(e.getCif());
	 
			}
			
		 
			
		} catch (Exception e) {
			 
			e.printStackTrace();
		}finally {
			JDBCUtils.closeConnection(c, false);
		}
	}
	
	public void testFindByCriteria() throws ServiceException {
		System.out.println("Testing findByCriteria...");
		System.out.println();

		EmpresaCriteria ec = new EmpresaCriteria();
		
	 
		  
		 
//		 ec.setNombre("prada");
	 
		 
		
		List<Empresa> empresa = empresaService.findByCriteria(ec);
		System.out.println(empresa);
		this.leerLista(empresa);
		
		
	}
	

	 public void testLogin() {
		Empresa empresa = null;
		
		System.out.println("testLogin ...");
		try {
			
			empresa = empresaService.login("distribuciones_alex@gmail.com", "abc123.");
			if (empresa == null) {
				System.out.println("Usuario o password incorrecta");
			} else {
				System.out.println("Hola "+empresa.getNombre()+"!");
			}
		} catch (Exception e) {
			System.out.println("Por favor intentelo de nuevo más tarde.");
			e.printStackTrace();
		}
	 
	
		try {
			empresa = empresaService.login("jorge@gmail.com", "abc123.");
			if (empresa == null) {
				System.out.println("Usuario o password incorrecta");
			} else {
				System.out.println("Hola "+empresa.getNombre()+"!");
			}
		} catch (Exception e) {
			System.out.println("Por favor intentelo de nuevo más tarde.");
			e.printStackTrace();
		}}
		/*

		try {
			empresa = empresaService.login("ricardo@gmail.com", "chantada");
			if (empresa == null) {
				System.out.println("Usuario o password incorrecta");
			} else {
				System.out.println("Hola "+empresa.getNombre()+"!");
			}
		} catch (Exception e) {
			System.out.println("Por favor intentelo de nuevo más tarde.");
			e.printStackTrace();
		}
	 }
*/
	public void testSignUp() {
		System.out.println("Testing signUp...");
		Empresa empresa = null;
		empresa = new Empresa();
	 
 
		empresa.setNombre("hasna.13ub" );
		
		empresa.setCif("B0011332");
		empresa.setNombreUsuario("hasb" );
		 
		empresa.setEmail(empresa.getNombre()+"@gmail.com");
		empresa.setContrasena("abc12347");
		empresa.setIdTipoEmpresa(1L);
 
		 
		System.out.println("Creando usuario/empresa "+empresa.getEmail());
		try {
			
			empresaService.signUp(empresa);
			System.out.println("Empresa Creada");
		} 
		
		
		catch (InstanceException uae) {		
			System.out.println("El usuario ya existe.");
			uae.printStackTrace();
		} catch (MailException me) {
			System.out.println("Ha habido un problema al enviar el e-mail.");
			me.printStackTrace();
		} catch (ServiceException se) {
			System.out.println(se.getMessage());
			se.printStackTrace();
		}
	}
	
	public void testUpdate() throws ServiceException {
		System.out.println("Testing Update...");
		System.out.println();

		Empresa empresa = new Empresa();
		try {
			empresa.setNombre("Distrinucciones Alejandro");
			empresa.setNombreUsuario("Alejandro");
			empresa.setCif("B1234567");
			empresa.setEmail("disctrinucciones@gmail.com");
			empresa.setContrasena("abc12345");
			empresa.setIdTipoEmpresa(1L);
			System.out.println("Empresa actualizada");
		}catch (Exception e) {
			e.printStackTrace();
		}
	 
		 
	 
		  
		 
//		 ec.setNombre("prada");
	 
		 
		
		 
		System.out.println(empresa);
		 
		
		
	}
 
	public static void main(String args[] ) throws ServiceException {
		EmpresaServiceTest test = new EmpresaServiceTest();
//			test.testLogin();
//			test.testFindById();
	//	test.testFindByEmail();
		//test.findByNombre("gUCCI");
		test.testUpdate();
//	test.testFindByCriteria();
			
//	test.testSignUp();
			
			
	}
	public void leerLista(List<Empresa> e) throws DataException, ServiceException {
        for (Empresa empresa : e) {
        	System.out.println("Id de la empresa: "+empresa.getId());
        	System.out.println("Email de la empresa: "+empresa.getEmail());
        	System.out.println("Nombre de la empresa: "+empresa.getNombre());
        	System.out.println("Nombre de usuario de la empresa: "+empresa.getNombreUsuario());
        	System.out.println("Id tipo de la empresa: "+empresa.getIdTipoEmpresa());
         
        	
        }
        System.out.println("!");
     
	}
        }
	
	
