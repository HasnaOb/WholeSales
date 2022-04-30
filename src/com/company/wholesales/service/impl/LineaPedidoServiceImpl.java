package com.company.wholesales.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.company.wholesales.service.LineaPedidoService;
import com.jal.wholesales.dao.LineaPedidoDAO;
import com.jal.wholesales.dao.LineaPedidoDAOImpl;
import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.LineaPedido;
import com.wholesales.exception.DataException;
import com.wholesales.exception.ServiceException;

public class LineaPedidoServiceImpl implements LineaPedidoService {

	private static Logger logger = LogManager.getLogger(LineaPedidoServiceImpl.class);
	private LineaPedidoDAO lineaPedidoDAO = null;

	public LineaPedidoServiceImpl() {

		lineaPedidoDAO = new LineaPedidoDAOImpl();
	}

	@Override
	public LineaPedido findById(long id) throws DataException, ServiceException {
		Connection c = null;
		LineaPedido lineaPedido = null;
		boolean commitOrRollback = false;
		try {
			c = ConnectionManager.getConnection();

			c.setAutoCommit(false);

			lineaPedido = lineaPedidoDAO.findById(c, id);

			commitOrRollback = true;

		} catch (SQLException sqle) {
			logger.error("findById: " + id + ": " + sqle.getMessage(), sqle);
			throw new DataException(id + "", sqle);

		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}
		return lineaPedido;
	}

	@Override
	public LineaPedido create(LineaPedido lp)

			throws DataException, ServiceException {
		Connection c = null;

		System.out.println("Creating " + lp + "...");

		boolean commitOrRollback = false;
		LineaPedido lineaPedido = null;
		try {
			c = ConnectionManager.getConnection();

			// inicio de la transaccion, es como un beggin
			c.setAutoCommit(false);

			lineaPedidoDAO.create(c, lp);

			// fin de la transacción
			commitOrRollback = true;

		} catch (SQLException sqle) {
			if (logger.isErrorEnabled()) {
				logger.error(lp, sqle);
			}
			throw new DataException(sqle);

		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}

		return lineaPedido;
	}

	public List<LineaPedido> findByPedido(Long id) throws ServiceException {

		Connection c = null;
		List<LineaPedido> lineaPedido = null;
		boolean commitOrRollback = false;
		try {
			c = ConnectionManager.getConnection();

			c.setAutoCommit(false);

			lineaPedido = lineaPedidoDAO.findByPedido(c, id);

			commitOrRollback = true;

		} catch (SQLException sqle) {
			if (logger.isErrorEnabled()) {
				logger.error(id, sqle);
			}
			throw new DataException(sqle);
		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}
		return lineaPedido;

	}

//	@Override
//	public void update(LineaPedido lp) throws ServiceException {
//		// TODO Auto-generated method stub
//		Connection c=null;
//		boolean commit = false;
//
//		try {
//
//			c = ConnectionManager.getConnection();
//
//			c.setTransactionIsolation(
//					Connection.TRANSACTION_READ_COMMITTED);
//
//			c.setAutoCommit(false);
//
//			lineaPedidoDAO.update(c, lp);
//			commit = true;
//
//		} catch (SQLException ex) {
//		 
//			throw new DataException(ex);
//
//		} finally {
//			JDBCUtils.closeConnection(c, commit);
//		}
//	}
}
