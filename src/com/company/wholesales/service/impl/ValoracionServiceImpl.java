package com.company.wholesales.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.company.wholesales.service.ValoracionService;
import com.jal.wholesales.dao.ValoracionDAO;
import com.jal.wholesales.dao.ValoracionDAOImpl;
import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.Valoracion;
import com.wholesales.exception.DataException;
import com.wholesales.exception.InstanceNotFoundException;
import com.wholesales.exception.ServiceException;

public class ValoracionServiceImpl implements ValoracionService{
	
	private ValoracionDAO valoracionDAO = null;
	
	
	public ValoracionServiceImpl() {
		 
		valoracionDAO = new ValoracionDAOImpl();
	}
	@Override
	public Valoracion findById (long id) throws DataException, ServiceException {
		 Connection c=null;
		Valoracion valoracion = null;
		boolean commitOrRollback = false;
		try  {
			c = ConnectionManager.getConnection();					
			
			c.setAutoCommit(false);
			
			valoracion =valoracionDAO.findById(c, id);
								
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
		return valoracion;	
	}

	 
	@Override
    public  Valoracion create(Valoracion v) 
            throws DataException, ServiceException {
		
		Connection c =null;
		System.out.println("Creating "+v+"...");
	 
		boolean commitOrRollback = false;
		Valoracion valoracion = null;
        try  {
            c = ConnectionManager.getConnection();

            // inicio de la transaccion, es como un beggin
            c.setAutoCommit(false);

            valoracionDAO.create(c, v);
            
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

        return valoracion;
    }
	
	@Override
	public void update(Valoracion v) throws ServiceException {
		
		Connection c = null;
		 
		boolean commit = false;

		try {

			c = ConnectionManager.getConnection();

			c.setTransactionIsolation(
					Connection.TRANSACTION_READ_COMMITTED);

			c.setAutoCommit(false);

			valoracionDAO.update(c, v);
			commit = true;

		} catch (SQLException ex) {
		 
			throw new DataException(ex);

		} finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}
	
	@Override
	public void deleteById( Long id) 
		throws DataException, InstanceNotFoundException {
		Connection c=null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {			
			String sql = " delete "
					+ " from valoracion"
					+ " where id = ?";
			
			preparedStatement =  c.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			JDBCUtils.setParameter(preparedStatement, 1, id); 
			int deletedRows = preparedStatement.executeUpdate();
			if (deletedRows!=1) {
				throw new InstanceNotFoundException(""+id);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataException(""+id, sqle);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);
		}

		
	}
}

	
