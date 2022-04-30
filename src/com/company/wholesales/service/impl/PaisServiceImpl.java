package com.company.wholesales.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.company.wholesales.service.PaisService;
import com.jal.wholesales.dao.PaisDAO;
import com.jal.wholesales.dao.PaisDAOImpl;
import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.Pais;
import com.wholesales.exception.DataException;
import com.wholesales.exception.ServiceException;

public class PaisServiceImpl implements PaisService {
	private Logger logger = LogManager.getLogger(PaisServiceImpl.class);
	private PaisDAO paisDAO = null;

	public PaisServiceImpl() {

		paisDAO = new PaisDAOImpl();
	}

	@Override
	public Pais findById(long id) throws DataException, ServiceException {
		if (logger.isDebugEnabled())
			logger.debug("id: {}", id);
		Connection c = null;
		Pais pais = null;
		boolean commitOrRollback = false;
		try {
			c = ConnectionManager.getConnection();

			c.setAutoCommit(false);

			pais = paisDAO.findById(c, id);

			commitOrRollback = true;

		} catch (SQLException sqle) {
			logger.error("findById: " + id + ": " + sqle.getMessage(), sqle);
			throw new DataException(id + "", sqle);

		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}
		return pais;
	}

	@Override
	public Pais create(Pais p) throws DataException, ServiceException {

		Connection c = null;
		boolean commitOrRollback = false;
		Pais pais = null;
		try {
			c = ConnectionManager.getConnection();

			// inicio de la transaccion, es como un beggin
			c.setAutoCommit(false);

			paisDAO.create(c, p);

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

		return pais;
	}

	@Override
	public void update(Pais p) throws ServiceException {

		Connection c = null;

		boolean commit = false;

		try {

			c = ConnectionManager.getConnection();

			c.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			c.setAutoCommit(false);

			paisDAO.update(c, p);
			commit = true;

		} catch (SQLException sqle) {

			logger.error("update: " + p.getNombre() + ": " + sqle.getMessage(), sqle);
			throw new DataException("update: " + p.getNombre() + ": " + sqle.getMessage(), sqle);
		} finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}
}
