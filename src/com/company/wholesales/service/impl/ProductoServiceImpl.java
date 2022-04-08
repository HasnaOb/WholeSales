package com.company.wholesales.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.company.wholesales.service.ProductoService;
import com.jal.wholesales.dao.ProductoDAO;
import com.jal.wholesales.dao.ProductoDAOImpl;
import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.Producto;
import com.jal.wholesales.service.ProductoCriteria;
import com.wholesales.exception.DataException;
import com.wholesales.exception.ServiceException;


public class ProductoServiceImpl implements ProductoService {
	private ProductoDAO productoDAO = null;
	
	
	public ProductoServiceImpl() {
		 
		productoDAO = new ProductoDAOImpl();
	}
	@Override
	public Producto findById (long id) throws DataException, ServiceException {
		 Connection c=null;
		Producto producto = null;
		boolean commitOrRollback = false;
		try  {
			c = ConnectionManager.getConnection();					
			
			c.setAutoCommit(false);
			
			producto =productoDAO.findById(c, id);
								
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
		return producto;	
	}
	public List<Producto> findByNombre (String nombre) throws DataException, ServiceException {
		 Connection c=null;
		List<Producto> producto = null;
		boolean commitOrRollback = false;
		try  {
			c = ConnectionManager.getConnection();					
			
			c.setAutoCommit(false);
			
			producto =productoDAO.findByNombre(c, nombre);
								
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
		return producto;	
	}

	public List<Producto> findByCriteria(ProductoCriteria criteria)
			throws DataException {
		boolean commitOrRollback = false;
		Connection c = null;

		try {

			c = ConnectionManager.getConnection();
			c.setAutoCommit(true);

			return productoDAO.findByCriteria(c, criteria);

		} catch (SQLException e){
			e.printStackTrace();
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(c);
		}
	}



	 
	@Override
    public  Producto create(Producto p) 
   
            throws DataException, ServiceException {
		 Connection c =null;
		
		System.out.println("Creating "+p+"...");
	 
		boolean commitOrRollback = false;
		Producto producto = null;
        try  {
            c = ConnectionManager.getConnection();

            // inicio de la transaccion, es como un beggin
            c.setAutoCommit(false);

            productoDAO.create(c, p);
            
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
	public void update(Producto p) throws ServiceException {
		// TODO Auto-generated method stub
		Connection c=null;
		boolean commit = false;

		try {

			c = ConnectionManager.getConnection();

			c.setTransactionIsolation(
					Connection.TRANSACTION_READ_COMMITTED);

			c.setAutoCommit(false);

			productoDAO.update(c, p);
			commit = true;

		} catch (SQLException ex) {
		 
			throw new DataException(ex);

		} finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}
	 
}