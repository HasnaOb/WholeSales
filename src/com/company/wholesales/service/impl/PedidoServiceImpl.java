package com.company.wholesales.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.company.wholesales.service.PedidoService;
import com.jal.wholesales.dao.PedidoDAO;
import com.jal.wholesales.dao.PedidoDAOImpl;
import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.PedidoDTO;
import com.wholesales.exception.DataException;
import com.wholesales.exception.ServiceException;

public class PedidoServiceImpl implements PedidoService {
	private Logger logger = LogManager.getLogger(PedidoServiceImpl.class);
	private PedidoDAO pedidoDAO = null;

	public PedidoServiceImpl() {
		pedidoDAO = new PedidoDAOImpl();
	}

	@Override
	public PedidoDTO findById(long id) throws DataException, ServiceException {
		if (logger.isDebugEnabled())
			logger.debug("id: {}", id);
		Connection c = null;
		PedidoDTO pedido = null;
		boolean commitOrRollback = false;
		try {
			c = ConnectionManager.getConnection();

			c.setAutoCommit(false);

			pedido = pedidoDAO.findById(c, id);

			commitOrRollback = true;

		} catch (SQLException sqle) {
			logger.error("findById: " + id + ": " + sqle.getMessage(), sqle);
			throw new DataException(id + "", sqle);

		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}
		return pedido;
	}

	@Override
	public PedidoDTO create(PedidoDTO p) throws DataException, ServiceException {

		Connection c = null;

		boolean commitOrRollback = false;
		PedidoDTO pedido = null;
		try {
			c = ConnectionManager.getConnection();

			// inicio de la transaccion, es como un beggin
			c.setAutoCommit(false);

			pedidoDAO.create(c, p);

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

		return pedido;
	}

	@Override
	public void update(PedidoDTO p) throws ServiceException {

		Connection c = null;

		boolean commit = false;

		try {

			c = ConnectionManager.getConnection();

			c.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			c.setAutoCommit(false);

			pedidoDAO.update(c, p);
			commit = true;

		} catch (SQLException sqle) {

			logger.error("update: " + p.getId() + ": " + sqle.getMessage(), sqle);
			throw new DataException("update: " + p.getId() + ": " + sqle.getMessage(), sqle);

		} finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}
}



 