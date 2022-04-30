package com.company.wholesales.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.company.wholesales.service.ProvinciaService;
import com.jal.wholesales.dao.ProvinciaDAO;
import com.jal.wholesales.dao.ProvinciaDAOImpl;
import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.Provincia;
import com.wholesales.exception.DataException;
import com.wholesales.exception.ServiceException;

public class ProvinciaServiceImpl implements ProvinciaService {

	private Logger logger = LogManager.getLogger(ProvinciaServiceImpl.class);

	private ProvinciaDAO provinciaDAO = null;

	public ProvinciaServiceImpl() {

		provinciaDAO = new ProvinciaDAOImpl();
	}

	@Override
	public Provincia findById(long id) throws DataException, ServiceException {
		if (logger.isDebugEnabled())
			logger.debug("id: {}", id);
		Connection c = null;
		Provincia provincia = null;
		boolean commitOrRollback = false;
		try {
			c = ConnectionManager.getConnection();

			c.setAutoCommit(false);

			provincia = provinciaDAO.findById(c, id);

			commitOrRollback = true;

		} catch (SQLException sqle) {
			logger.error("findById: " + id + ": " + sqle.getMessage(), sqle);
			throw new DataException(id + "", sqle);

		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}
		return provincia;
	}

	public List<Provincia> findByPais(long id) throws DataException, ServiceException {
		Connection c = null;
		List<Provincia> provincia = null;
		boolean commitOrRollback = false;
		try {
			c = ConnectionManager.getConnection();

			c.setAutoCommit(false);

			provincia = provinciaDAO.findByPais(c, id);

			commitOrRollback = true;

		} catch (SQLException sqle) {
			if (logger.isErrorEnabled()) {
				logger.error(id, sqle);
			}
			throw new DataException(sqle);
		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}
		return provincia;
	}

	@Override
	public Provincia create(Provincia p) throws DataException, ServiceException {

		Connection c = null;
		System.out.println("Creating " + p + "...");

		boolean commitOrRollback = false;
		Provincia provincia = null;
		try {
			c = ConnectionManager.getConnection();

			// inicio de la transaccion, es como un beggin
			c.setAutoCommit(false);

			provinciaDAO.create(c, p);

			// fin de la transacción
			commitOrRollback = true;

		} catch (SQLException ex) {
			logger.error("Create: " + p.getNombre() + ": " + ex.getMessage(), ex);
			throw new DataException("Create: " + p.getNombre() + ": " + ex.getMessage(), ex);
		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}

		return provincia;
	}

	@Override
	public void update(Provincia p) throws ServiceException {

		Connection c = null;

		boolean commitOrRollback = false;

		try {

			c = ConnectionManager.getConnection();

			c.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			c.setAutoCommit(false);

			provinciaDAO.update(c, p);
			commitOrRollback = true;

		} catch (SQLException sqle) {
			logger.error("update: " + p.getNombre() + ": " + sqle.getMessage(), sqle);
			throw new DataException("update: " + p.getNombre() + ": " + sqle.getMessage(), sqle);
		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}
	}
}


