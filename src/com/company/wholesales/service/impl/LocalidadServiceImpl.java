package com.company.wholesales.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.company.wholesales.service.LocalidadService;
import com.jal.wholesales.dao.LocalidadDAO;
import com.jal.wholesales.dao.LocalidadDAOImpl;
import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.Localidad;
import com.wholesales.exception.DataException;
import com.wholesales.exception.ServiceException;

public class LocalidadServiceImpl implements LocalidadService {
	
		private LocalidadDAO localidadDAO = null;
		
		
		public LocalidadServiceImpl() {
			 
			localidadDAO = new LocalidadDAOImpl();
		}
		@Override
		public Localidad findById (long id) throws DataException, ServiceException {
			 Connection c=null;
			Localidad localidad = null;
			boolean commitOrRollback = false;
			try  {
				c = ConnectionManager.getConnection();					
				
				c.setAutoCommit(false);
				
				localidad =localidadDAO.findById(c, id);
									
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
			return localidad;	
		}

		 
		@Override
	    public  Localidad create(Localidad l) 
	            throws DataException, ServiceException {
			
			Connection c =null;
			System.out.println("Creating "+l+"...");
		 
			boolean commitOrRollback = false;
			Localidad localidad = null;
	        try  {
	            c = ConnectionManager.getConnection();

	            // inicio de la transaccion, es como un beggin
	            c.setAutoCommit(false);

	            localidadDAO.create(c, l);
	            
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

	        return localidad;
	    }
		
		@Override
		public void update(Localidad l) throws ServiceException {
			
			Connection c = null;
			 
			boolean commit = false;

			try {

				c = ConnectionManager.getConnection();

				c.setTransactionIsolation(
						Connection.TRANSACTION_READ_COMMITTED);

				c.setAutoCommit(false);

				localidadDAO.update(c, l);
				commit = true;

			} catch (SQLException ex) {
			 
				throw new DataException(ex);

			} finally {
				JDBCUtils.closeConnection(c, commit);
			}
		}
	}
