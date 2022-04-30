package com.company.wholesales.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.company.wholesales.service.LocalidadService;
import com.jal.wholesales.dao.LocalidadDAO;
import com.jal.wholesales.dao.LocalidadDAOImpl;
import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.Localidad;
import com.wholesales.exception.DataException;
import com.wholesales.exception.ServiceException;

public class LocalidadServiceImpl implements LocalidadService {

	private Logger logger = LogManager.getLogger(LocalidadServiceImpl.class);
	private LocalidadDAO localidadDAO = null;

	public LocalidadServiceImpl() {

		localidadDAO = new LocalidadDAOImpl();
	}

	@Override
	public Localidad findById(long id) throws DataException, ServiceException {
		if (logger.isDebugEnabled())
			logger.debug("id: {}", id);
		Connection c = null;
		Localidad localidad = null;
		boolean commitOrRollback = false;
		try {
			c = ConnectionManager.getConnection();

			c.setAutoCommit(false);

			localidad = localidadDAO.findById(c, id);

			commitOrRollback = true;

		} catch (SQLException sqle) {
			logger.error("findById: " + id + ": " + sqle.getMessage(), sqle);
			throw new DataException(id + "", sqle);

		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}
		return localidad;
	}

	@Override
	public Localidad create(Localidad l) throws DataException, ServiceException {

		Connection c = null;

		boolean commitOrRollback = false;
		Localidad localidad = null;
		try {
			c = ConnectionManager.getConnection();

			// inicio de la transaccion, es como un beggin
			c.setAutoCommit(false);

			localidadDAO.create(c, l);

			// fin de la transacción
			commitOrRollback = true;

		} catch (SQLException sqle) {
			if (logger.isErrorEnabled()) {
				logger.error(l, sqle);
			}
			throw new DataException(sqle);

		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}

		return localidad;
	}

	@Override
	public void update(Localidad l) throws ServiceException {

		Connection c = null;

		boolean commitOrRollback = false;

		try {

			c = ConnectionManager.getConnection();

			c.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			c.setAutoCommit(false);

			localidadDAO.update(c, l);
			commitOrRollback = true;

		} catch (SQLException sqle) {

			logger.error("update: " + l.getNombre() + ": " + sqle.getMessage(), sqle);
			throw new DataException("update: " + l.getNombre() + ": " + sqle.getMessage(), sqle);

		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}
	}
}
