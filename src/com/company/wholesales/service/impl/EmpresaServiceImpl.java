package com.company.wholesales.service.impl;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.company.wholesales.service.EmpresaService;
import com.company.wholesales.service.MailService;
import com.jal.wholesales.dao.EmpresaDAO;
import com.jal.wholesales.dao.EmpresaDAOImpl;
import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.Empresa;
import com.jal.wholesales.service.EmpresaCriteria;
import com.wholesales.exception.DataException;
import com.wholesales.exception.InstanceException;
import com.wholesales.exception.MailException;
import com.wholesales.exception.ServiceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import util.PasswordEncryptionUtil;

public class EmpresaServiceImpl implements EmpresaService{
	
	private Logger logger = LogManager.getLogger(EmpresaServiceImpl.class);
	

	private MailService mailService = null;
	private EmpresaDAO empresaDAO = null;
	
	public EmpresaServiceImpl() {
		mailService = new MailServiceImpl();
		empresaDAO = new EmpresaDAOImpl();
	}
	
	
	@Override
	public Empresa findById (long id) throws DataException, ServiceException {
		if(logger.isDebugEnabled()) logger.debug("id: {}", id);
		Connection c=null;
		Empresa empresa = null;
		boolean commitOrRollback = false;
		try  {
			c = ConnectionManager.getConnection();					
			
			c.setAutoCommit(false);
			
			empresa = empresaDAO.findById(c, id);
								
			commitOrRollback = true;
			

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new ServiceException(id+"", sqle);
			
		} catch (DataException de) { // si viene del DAO ya seria innecesario
			logger.warn(de.getMessage(), de);
			throw de;
			
		} catch (Exception ex) {
			logger.warn(ex.getMessage(), ex);
			throw new ServiceException(ex);
			
		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}
		return empresa;	
	}
	
	
	
	@Override
	public Empresa findByEmail (String  email) throws DataException, ServiceException 
	{ if(logger.isDebugEnabled()) logger.debug("Email: {}", email);
		Connection c=null;
		Empresa empresa = null;
		boolean commitOrRollback = false;
		try  {
			c = ConnectionManager.getConnection();					
			
			c.setAutoCommit(false);
			
			empresa = empresaDAO.findByEmail(c, email);
								
			commitOrRollback = true;
			

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new ServiceException(email+"", sqle);
			
		} catch (DataException de) { // si viene del DAO ya seria innecesario
			de.printStackTrace();	
			throw de;
			
		} catch (Exception ex) {
			logger.warn(ex.getMessage(), ex);
			throw new ServiceException(ex);
			
		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}
		return empresa;	
	}

		@Override
		public List<Empresa> findByNombre (String  nombre) throws DataException, ServiceException {
			
			Connection c=null;
			List<Empresa> empresa=null;
			 
			boolean commitOrRollback = false;
			try  {
				c = ConnectionManager.getConnection();					
				
				c.setAutoCommit(false);
				
				empresa = empresaDAO.findByNombre(c, nombre);
									
				commitOrRollback = true;
				
	
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				throw new ServiceException(nombre+"", sqle);
				
			} catch (DataException de) { // si viene del DAO ya seria innecesario
				de.printStackTrace();	
				throw de;
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(e);
				
			} finally {
				JDBCUtils.closeConnection(c, commitOrRollback);
			}
			return empresa;	
		}
	public List<Empresa> findByCriteria(EmpresaCriteria criteria)
			throws DataException {
		
		if(logger.isDebugEnabled()) logger.debug("Criteria: {}" , criteria);
	
		Connection c = null;

		try {

			c = ConnectionManager.getConnection();
			c.setAutoCommit(true);

			return empresaDAO.findByCriteria(c, criteria);

		} catch (SQLException e){
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(c);
		}
	}


	 
	

	@Override
	public Empresa login(String email, String password) throws ServiceException {
		
		if(logger.isDebugEnabled()) logger.debug("email: {}; password: {}", email, password==null);
		
		Connection c = null;
		Empresa empresa = null;
		boolean commitOrRollback = false;
		try  {
			c = ConnectionManager.getConnection();					
			
			c.setAutoCommit(false);
			
			empresa = empresaDAO.findByEmail(c, email);
			
			if (empresa!=null) {
				boolean passwordOK = PasswordEncryptionUtil.checkPassword(password, empresa.getContrasenaEncriptada());
				if (!passwordOK) {
					empresa = null;
				}
			}
		
			 		
			commitOrRollback = true;
			 

		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage(), sqle);
			throw new DataException(email, sqle);
			
		} catch (DataException de) { // si viene del DAO ya seria innecesario
			logger.warn(de.getMessage(), de);
			throw de;
			
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
			throw new ServiceException(e);
			
		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}
		return empresa;		
	}

	@Override
	public Empresa signUp(Empresa e) throws InstanceException, MailException, ServiceException {
//		if(logger.isDebugEnabled()) logger.debug("Estudiante = email: {}; id_pais: {}; pssw: {}; nombre: {}; apellido1: {}; apellido2: {}; ano_nacimiento: {}; id_genero: {}; acertadas= {}",
//				e.getEmail(), e.getIdPais(), e.getPsswd()==null, e.getNombre(), e.getApellido1(), e.getApellido2(), e.getAnoNacimiento(), e.getIdGenero(), acertadas);
		Connection c = null;
		boolean commitOrRollback = false;
		Empresa empresa = null;
		try  {
			c = ConnectionManager.getConnection();								
			
			c.setAutoCommit(false);

			if (empresaDAO.findByEmail(c, e.getEmail())!=null) {
				throw new InstanceException(e.getEmail());				
			}
			
	 
			
			e.setContrasenaEncriptada(PasswordEncryptionUtil.encryptPassword(e.getContrasena()));
			empresa = empresaDAO.create(c, e);		
			StringBuilder welcomeMsgSb = new StringBuilder("Hola ")
                    .append(e.getNombreUsuario())
                    .append(", Bienvenido a Wholsales")
                    .append(" ...");

            String welcomeMsg = welcomeMsgSb.toString();

			mailService.sendEmail("hasna.1310.ub@gmail.com", 					 					 
					e.getEmail(), welcomeMsg);
			
			commitOrRollback = true;
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DataException(ex);			
		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}
		
		return empresa;
	}

	@Override
	public void update(Empresa e) throws ServiceException {
		// TODO Auto-generated method stub
			Connection c=null;
			boolean commitOrRollback = false;
			try  {
				c = ConnectionManager.getConnection();					

				c.setAutoCommit(false);

				if (e.getContrasena()!=null) {
					e.setContrasenaEncriptada(PasswordEncryptionUtil.encryptPassword(e.getContrasena()));
				}

				empresaDAO.update(c, e);

				commitOrRollback = true;


			} catch (SQLException sqle) {
				sqle.printStackTrace();
				throw new ServiceException(e.getEmail()+"", sqle);
				
			} catch (DataException de) { // si viene del DAO ya seria innecesario
				de.printStackTrace();	
				throw de;
				
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ServiceException(ex);
				
			} finally {
				JDBCUtils.closeConnection(c, commitOrRollback);
			}
			
		}



	 


 

	 
}