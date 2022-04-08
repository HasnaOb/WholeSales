package com.company.wholesales.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.company.wholesales.service.TipoEstadoService;
import com.jal.wholesales.dao.TipoEstadoDAO;
import com.jal.wholesales.dao.TipoEstadoDAOImpl;
import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.TipoEstado;
import com.wholesales.exception.DataException;
import com.wholesales.exception.ServiceException;

public class TipoEstadoServiceImpl implements TipoEstadoService {
private TipoEstadoDAO tipoEstadoDAO = null;
	
	
	public TipoEstadoServiceImpl() {
		 
		tipoEstadoDAO = new TipoEstadoDAOImpl();
	}
	@Override
	public TipoEstado findById (long id) throws DataException, ServiceException {
		 Connection c=null;
		TipoEstado tipoEstado = null;
		boolean commitOrRollback = false;
		try  {
			c = ConnectionManager.getConnection();					
			
			c.setAutoCommit(false);
			
			tipoEstado =tipoEstadoDAO.findById(c, id);
								
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
		return tipoEstado;	
	}

	 
	@Override
    public  TipoEstado create(TipoEstado te) 
   
            throws DataException, ServiceException {
		 Connection c =null;
		
		System.out.println("Creating "+te+"...");
	 
		boolean commitOrRollback = false;
		TipoEstado tipoEstado = null;
        try  {
            c = ConnectionManager.getConnection();

            // inicio de la transaccion, es como un beggin
            c.setAutoCommit(false);

            tipoEstadoDAO.create(c, te);
            
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

        return tipoEstado;
    }
	
	@Override
	public void update(TipoEstado te) throws ServiceException {
		// TODO Auto-generated method stub
		Connection c=null;
		boolean commit = false;

		try {

			c = ConnectionManager.getConnection();

			c.setTransactionIsolation(
					Connection.TRANSACTION_READ_COMMITTED);

			c.setAutoCommit(false);

			tipoEstadoDAO.update(c, te);
			commit = true;

		} catch (SQLException ex) {
		 
			throw new DataException(ex);

		} finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

}
