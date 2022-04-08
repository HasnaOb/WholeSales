package com.company.wholesales.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.company.wholesales.service.SeccionService;
import com.jal.wholesales.dao.SeccionDAO;
import com.jal.wholesales.dao.SeccionDAOImpl;
import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.Seccion;
import com.wholesales.exception.DataException;
import com.wholesales.exception.ServiceException;

public class SeccionServiceImpl implements SeccionService{

private SeccionDAO seccionDAO = null;
	
	
	public SeccionServiceImpl() {
		 
		seccionDAO = new SeccionDAOImpl();
	}
	@Override
	public Seccion findById (long id) throws DataException, ServiceException {
		 Connection c=null;
		Seccion seccion = null;
		boolean commitOrRollback = false;
		try  {
			c = ConnectionManager.getConnection();					
			
			c.setAutoCommit(false);
			
			seccion =seccionDAO.findById(c, id);
								
			commitOrRollback = true;
			

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new ServiceException(id+"", sqle);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
			
		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}
		return seccion;	
	}

	 
	@Override
    public  Seccion create(Seccion s) 
   
            throws DataException, ServiceException {
		 Connection c =null;
		
		System.out.println("Creating "+s+"...");
	 
		boolean commitOrRollback = false;
		Seccion producto = null;
        try  {
            c = ConnectionManager.getConnection();

            // inicio de la transaccion, es como un beggin
            c.setAutoCommit(false);

            seccionDAO.create(c, s);
            
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

        return producto;
    }
	
	@Override
	public void update(Seccion p) throws ServiceException {
		// TODO Auto-generated method stub
		Connection c=null;
		boolean commit = false;

		try {

			c = ConnectionManager.getConnection();

			c.setTransactionIsolation(
					Connection.TRANSACTION_READ_COMMITTED);

			c.setAutoCommit(false);

			seccionDAO.update(c, p);
			commit = true;

		} catch (SQLException ex) {
		 
			throw new DataException(ex);

		} finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}
}