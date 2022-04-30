package com.company.wholesales.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.company.wholesales.service.ProductoService;
import com.jal.wholesales.dao.ProductoDAO;
import com.jal.wholesales.dao.ProductoDAOImpl;
import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.Empresa;
import com.jal.wholesales.model.Producto;
import com.jal.wholesales.service.ProductoCriteria;
import com.wholesales.exception.DataException;
import com.wholesales.exception.ServiceException;

public class ProductoServiceImpl implements ProductoService {

	private static Logger logger = LogManager.getLogger(ProductoServiceImpl.class);

	private ProductoDAO productoDAO = null;

	public ProductoServiceImpl() {

		productoDAO = new ProductoDAOImpl();
	}

	@Override
	public Producto findById(long id) throws DataException, ServiceException {
		Connection c = null;
		Producto producto = null;
		boolean commitOrRollback = false;
		try {
			c = ConnectionManager.getConnection();

			c.setAutoCommit(false);

			producto = productoDAO.findById(c, id);

			commitOrRollback = true;

		} catch (SQLException sqle) {
			logger.error("findById: " + id + ": " + sqle.getMessage(), sqle);
			throw new DataException(id + "", sqle);

		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}
		return producto;
	}

	public List<Producto> findByNombre(String nombre) throws DataException, ServiceException {
		Connection c = null;
		List<Producto> producto = null;
		boolean commitOrRollback = false;
		try {
			c = ConnectionManager.getConnection();

			c.setAutoCommit(false);

			producto = productoDAO.findByNombre(c, nombre);

			commitOrRollback = true;

		} catch (SQLException sqle) {
			if (logger.isErrorEnabled()) {
				logger.error(nombre, sqle);
			}
			throw new DataException(sqle);
		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}
		return producto;
	}

	public Results<Producto> findByCriteria(ProductoCriteria criteria, int startIndex, int pageSize)
			throws DataException {

		boolean commitOrRollback = false;
		Connection c = null;
		Results<Producto> results = new Results<Producto>();

		try {

			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);

			results = productoDAO.findByCriteria(c, criteria, startIndex, pageSize);
			commitOrRollback = true;

		} catch (SQLException e) {
			if (logger.isErrorEnabled()) {
				logger.error(results, e);
			}
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}
		return results;
	}

	@Override
	public Producto create(Producto p)

			throws DataException, ServiceException {
		Connection c = null;

		boolean commitOrRollback = false;
		Producto producto = null;
		try {
			c = ConnectionManager.getConnection();

			// inicio de la transaccion, es como un beggin
			c.setAutoCommit(false);

			productoDAO.create(c, p);

			// fin de la transacción
			commitOrRollback = true;

		} catch (SQLException sqle) {
			if (logger.isErrorEnabled()) {
				logger.error(p, sqle);
			}
			throw new DataException(sqle);

		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}

		return producto;
	}

	@Override
	public void update(Producto p) throws ServiceException {
		// TODO Auto-generated method stub
		Connection c = null;
		boolean commit = false;

		try {

			c = ConnectionManager.getConnection();

			c.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			c.setAutoCommit(false);

			productoDAO.update(c, p);
			commit = true;

		} catch (SQLException sqle) {

			if (logger.isErrorEnabled()) {
				logger.error(p, sqle);
			}
			throw new DataException(sqle);

		} finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

}