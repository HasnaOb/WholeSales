package com.company.wholesales.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.company.wholesales.service.MarcaService;
import com.jal.wholesales.dao.MarcaDAO;
import com.jal.wholesales.dao.MarcaDAOImpl;
import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.Marca;
import com.wholesales.exception.DataException;
import com.wholesales.exception.ServiceException;

public class MarcaServiceImpl implements MarcaService {

	private Logger logger = LogManager.getLogger(MarcaServiceImpl.class);
	private MarcaDAO marcaDAO = null;

	public MarcaServiceImpl() {

		marcaDAO = new MarcaDAOImpl();
	}

	@Override
	public Marca findById(long id) throws DataException, ServiceException {
		if (logger.isDebugEnabled())
			logger.debug("id: {}", id);
		Connection c = null;
		Marca marca = null;
		boolean commitOrRollback = false;
		try {
			c = ConnectionManager.getConnection();

			c.setAutoCommit(false);

			marca = marcaDAO.findById(c, id);

			commitOrRollback = true;

		} catch (SQLException sqle) {
			logger.error("findById: " + id + ": " + sqle.getMessage(), sqle);
			throw new DataException(id + "", sqle);

		} finally {

			JDBCUtils.closeConnection(c, commitOrRollback);
		}
		return marca;
	}
	@Override
	public List<Marca> findByAll() throws DataException, ServiceException {
		 
		Connection c = null;
		List<Marca> marca = null;
		boolean commitOrRollback = false;
		try {
			c = ConnectionManager.getConnection();

			c.setAutoCommit(false);

			marca = marcaDAO.findByAll(c);

			commitOrRollback = true;

		} catch (SQLException sqle) {
			logger.error( sqle.getMessage(), sqle);
			throw new DataException(sqle);

		} finally {

			JDBCUtils.closeConnection(c, commitOrRollback);
		}
		return marca;
	}

	@Override
	public Marca create(Marca m) throws DataException, ServiceException {

		Connection c = null;

		boolean commitOrRollback = false;
		Marca marca = null;
		try {
			c = ConnectionManager.getConnection();

			// inicio de la transaccion, es como un beggin
			c.setAutoCommit(false);

			marcaDAO.create(c, m);

			// fin de la transacción
			commitOrRollback = true;

		} catch (SQLException sqle) {
			if (logger.isErrorEnabled()) {
				logger.error(m, sqle);
			}
			throw new DataException(sqle);

		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}

		return marca;
	}

	@Override
	public void update(Marca m) throws ServiceException {
		// TODO Auto-generated method stub
		Connection c = null;
		boolean commit = false;

		try {

			c = ConnectionManager.getConnection();

			c.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			c.setAutoCommit(false);

			marcaDAO.update(c, m);
			commit = true;

		} catch (SQLException sqle) {

			logger.error("update: " + m.getNombre() + ": " + sqle.getMessage(), sqle);
			throw new DataException("update: " + m.getNombre() + ": " + sqle.getMessage(), sqle);
		} finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}
}

