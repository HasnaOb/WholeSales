package com.jal.wholesales.dao;

import java.sql.Connection;


import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.Empresa;
import com.jal.wholesales.service.EmpresaCriteria;
import com.wholesales.exception.DataException;
 

public class EmpresaTest {
	

		private EmpresaDAO empresaDAO = null;
	
		public EmpresaTest() {
			this.empresaDAO = new EmpresaDAOImpl();
		}
		
		
		
		public void findByIdTest (Integer id) throws DataException {
			Connection c = null;
				try {
					c = ConnectionManager.getConnection();
					Empresa e = empresaDAO.findById(c, id);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					JDBCUtils.closeConnection(c);
				}
		}
		
//			public void findByNombre (String nombre) throws DataException {
//			Connection c = null;
//			try {
//				c = ConnectionManager.getConnection();
//				List <Empresa> empresas = empresaDAO.findByNombre(c, nombre);
//				
//				for(Empresa e: empresas) {
//				}
//				 
//				
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}finally {
//				JDBCUtils.closeConnection(c);
//			}
//		}
//		
//		public void findByCriteria (EmpresaCriteria criteria) throws DataException {
//			Connection c = null;
//			try {
//				c = ConnectionManager.getConnection();
//				List <Empresa> empresa = empresaDAO.findByCriteria(c, criteria);
//		
//				
//			} catch (Exception e) {
//				 
//				e.printStackTrace();
//			}finally {
//				JDBCUtils.closeConnection(c);
//			}
//		}
//		
		public static void main(String[] args) throws DataException {
			
			EmpresaTest test = new EmpresaTest();
			EmpresaCriteria criteria = new EmpresaCriteria();
//		try {
////				test.findByIdTest(3);
////				test.findByNombre("g");
////				criteria.setNombre("dytufvgyhbjksdgfhkjlijlbsnjñfdsñnjvfszknjñvsakjñvsknjñvfszkjñsakñvsañsañjvsañj");
//			 
////				test.findByCriteria(criteria);
//				
//			} catch (DataException e) {
//
//				e.printStackTrace();
//			}
//				
//		}
}}
	
		