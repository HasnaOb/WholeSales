package com.company.wholesales.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.company.wholesales.service.MarcaService;
import com.jal.wholesales.dao.MarcaDAO;
import com.jal.wholesales.dao.MarcaDAOImpl;
import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.Marca;
import com.wholesales.exception.DataException;
import com.wholesales.exception.ServiceException;

public class MarcaServiceImpl implements MarcaService{
private MarcaDAO marcaDAO = null;
	
	
	public MarcaServiceImpl() {
		 
		marcaDAO = new MarcaDAOImpl();
	}
	@Override
	public Marca findById (long id) throws DataException, ServiceException {
		 Connection c=null;
		Marca marca = null;
		boolean commitOrRollback = false;
		try  {
			c = ConnectionManager.getConnection();					
			
			c.setAutoCommit(false);
			
			marca =marcaDAO.findById(c, id);
								
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
		return marca;	
	}

	 
	@Override
    public  Marca create( Marca m) 
            throws DataException, ServiceException {
		
		Connection c=null;
		System.out.println("Creating "+m+"...");
	 
		boolean commitOrRollback = false;
		Marca marca = null;
        try  {
            c = ConnectionManager.getConnection();

            // inicio de la transaccion, es como un beggin
            c.setAutoCommit(false);

            marcaDAO.create(c, m);
            
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

        return marca;
    }
	
	@Override
	public void update( Marca m) throws ServiceException {
		// TODO Auto-generated method stub
		Connection c=null;
		boolean commit = false;

		try {

			c = ConnectionManager.getConnection();

			c.setTransactionIsolation(
					Connection.TRANSACTION_READ_COMMITTED);

			c.setAutoCommit(false);

			marcaDAO.update(c, m);
			commit = true;

		} catch (SQLException ex) {
		 
			throw new DataException(ex);

		} finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}
}

