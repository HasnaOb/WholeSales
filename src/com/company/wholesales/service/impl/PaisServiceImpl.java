package com.company.wholesales.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.company.wholesales.service.PaisService;
import com.jal.wholesales.dao.PaisDAO;
import com.jal.wholesales.dao.PaisDAOImpl;
import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.Pais;
import com.wholesales.exception.DataException;
import com.wholesales.exception.ServiceException;

public class PaisServiceImpl implements PaisService {
	private PaisDAO paisDAO = null;
	
	
	public PaisServiceImpl() {
		 
		paisDAO = new PaisDAOImpl();
	}
	@Override
	public Pais findById (long id) throws DataException, ServiceException {
		 Connection c=null;
		Pais pais = null;
		boolean commitOrRollback = false;
		try  {
			c = ConnectionManager.getConnection();					
			
			c.setAutoCommit(false);
			
			pais =paisDAO.findById(c, id);
								
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
		return pais;	
	}

	 
	@Override
    public  Pais create(Pais p) 
            throws DataException, ServiceException {
		
		Connection c =null;
		System.out.println("Creating "+p+"...");
	 
		boolean commitOrRollback = false;
		Pais pais = null;
        try  {
            c = ConnectionManager.getConnection();

            // inicio de la transaccion, es como un beggin
            c.setAutoCommit(false);

            paisDAO.create(c, p);
            
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

        return pais;
    }
	
	@Override
	public void update(Pais p) throws ServiceException {
		
		Connection c = null;
		 
		boolean commit = false;

		try {

			c = ConnectionManager.getConnection();

			c.setTransactionIsolation(
					Connection.TRANSACTION_READ_COMMITTED);

			c.setAutoCommit(false);

			paisDAO.update(c, p);
			commit = true;

		} catch (SQLException ex) {
		 
			throw new DataException(ex);

		} finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}
}
