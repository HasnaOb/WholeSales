package com.company.wholesales.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.company.wholesales.service.DireccionService;
import com.jal.wholesales.dao.DireccionDAO;
import com.jal.wholesales.dao.DireccionDAOImpl;
import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.Direccion;
import com.wholesales.exception.DataException;
import com.wholesales.exception.ServiceException;

public class DireccionServiceImpl implements DireccionService {
	private static Logger logger = LogManager.getLogger(DireccionServiceImpl.class);

	private DireccionDAO direccionDAO = null;

	public DireccionServiceImpl() {

		direccionDAO = new DireccionDAOImpl();
	}

	@Override
	public Direccion findById(long id) throws DataException, ServiceException {
		Connection c = null;
		Direccion direccion = null;
		boolean commitOrRollback = false;
		try {
			c = ConnectionManager.getConnection();

			c.setAutoCommit(false);

			direccion = direccionDAO.findById(c, id);

			commitOrRollback = true;

		} catch (SQLException sqle) {
			logger.error("findById: " + id + ": " + sqle.getMessage(), sqle);
			throw new DataException(id + "", sqle);

		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}
		return direccion;
	}

	@Override
	public List<Direccion> findByEmpresa(long idEmpresa) throws DataException, ServiceException {
		Connection c = null;
		List<Direccion> direccion = null;
		boolean commitOrRollback = false;
		try {
			c = ConnectionManager.getConnection();

			c.setAutoCommit(false);

			direccion = direccionDAO.findByEmpresa(c, idEmpresa);

			commitOrRollback = true;

		} catch (SQLException sqle) {
			if (logger.isErrorEnabled()) {
				logger.error(idEmpresa, sqle);
			}
			throw new DataException(sqle);
		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}
		return direccion;
	}

	@Override
	public Direccion create(Direccion d) throws DataException, ServiceException {

		Connection c = null;

		boolean commitOrRollback = false;
		Direccion direccion = null;
		try {
			c = ConnectionManager.getConnection();

			// inicio de la transaccion, es como un beggin
			c.setAutoCommit(false);

			direccionDAO.create(c, d);

			// fin de la transacción
			commitOrRollback = true;

		} catch (SQLException sqle) {
			if (logger.isErrorEnabled()) {
				logger.error(d, sqle);
			}
			throw new DataException(sqle);

		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}

		return direccion;
	}

	@Override
	public void update(Direccion d) throws ServiceException {

		Connection c = null;
		boolean commitOrRollback = false;

		try {

			c = ConnectionManager.getConnection();

			c.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			c.setAutoCommit(false);

			direccionDAO.update(c, d);
			commitOrRollback = true;

		} catch (SQLException sqle) {

			if (logger.isErrorEnabled()) {
				logger.error(d, sqle);
			}
			throw new DataException(sqle);

		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}
	}
} 
	