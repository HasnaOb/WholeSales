package com.company.wholesales.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.company.wholesales.service.DireccionService;
import com.jal.wholesales.dao.DireccionDAO;
import com.jal.wholesales.dao.DireccionDAOImpl;
import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.Direccion;
import com.wholesales.exception.DataException;
import com.wholesales.exception.ServiceException;

 

public class DireccionServiceImpl implements DireccionService {

	 
	private DireccionDAO direccionDAO = null;
	
	public DireccionServiceImpl() {
		 
		direccionDAO = new DireccionDAOImpl();
	}
	
	
	@Override
	public Direccion findById (long id) throws DataException, ServiceException {
		Connection c=null;
		Direccion direccion = null;
		boolean commitOrRollback = false;
		try  {
			c = ConnectionManager.getConnection();					
			
			c.setAutoCommit(false);
			
			direccion = direccionDAO.findById(c, id);
								
			commitOrRollback = true;
			

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new ServiceException(id+"", sqle);
			
		} catch (DataException de) { // si viene del DAO ya seria innecesario
			de.printStackTrace();	
			throw de;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
			
		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}
		return direccion;	
	}
	
	
	
	@Override
	public List<Direccion> findByEmpresa (long  idEmpresa) throws DataException, ServiceException {
		Connection c=null;
		List<Direccion > direccion = null;
		boolean commitOrRollback = false;
		try  {
			c = ConnectionManager.getConnection();					
			
			c.setAutoCommit(false);
			
			direccion = direccionDAO.findByEmpresa(c, idEmpresa);
								
			commitOrRollback = true;
			

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new ServiceException(idEmpresa+"", sqle);
			
		} catch (DataException de) { // si viene del DAO ya seria innecesario
			de.printStackTrace();	
			throw de;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
			
		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}
		return direccion;
	}
		@Override
	    public  Direccion create(Connection c ,Direccion d) 
	            throws DataException, ServiceException {
			
			
			System.out.println("Creating "+d+"...");
		 
			boolean commitOrRollback = false;
			Direccion direccion = null;
	        try  {
	            c = ConnectionManager.getConnection();

	            // inicio de la transaccion, es como un beggin
	            c.setAutoCommit(false);

	            direccionDAO.create(c, d);
	            
	            // fin de la transacción
	            commitOrRollback = true;

	        } catch (DataException de) { 
	            de.printStackTrace();
	            throw de;
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new ServiceException(e);
	        } finally {
	            JDBCUtils.closeConnection(c, commitOrRollback);
	        }

	        return direccion;
	    }
		
		@Override
		public void update(Connection c,Direccion d) throws ServiceException {
			
			 
			boolean commit = false;

			try {

				c = ConnectionManager.getConnection();

				c.setTransactionIsolation(
						Connection.TRANSACTION_READ_COMMITTED);

				c.setAutoCommit(false);

				direccionDAO.update(c, d);
				commit = true;

			} catch (SQLException ex) {
			 
				throw new DataException(ex);

			} finally {
				JDBCUtils.closeConnection(c, commit);
			}
		}
	}

	

	 
	