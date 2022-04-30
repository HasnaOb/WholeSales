package com.company.wholesales.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.company.wholesales.service.CategoriaService;
import com.jal.wholesales.dao.CategoriaDAO;
import com.jal.wholesales.dao.CategoriaDAOImpl;
import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.Categoria;
import com.jal.wholesales.service.CategoriaCriteria;
import com.wholesales.exception.DataException;
import com.wholesales.exception.InstanceNotFoundException;

import com.wholesales.exception.ServiceException;

public class CategoriaServiceImpl implements CategoriaService {
	private static Logger logger = LogManager.getLogger(CategoriaServiceImpl.class);

	private CategoriaDAO categoriaDAO = null;

	public CategoriaServiceImpl() {

		categoriaDAO = new CategoriaDAOImpl();
	}

	@Override
	public Categoria findById(long id) throws InstanceNotFoundException, DataException, ServiceException {
		Connection c = null;
		Categoria categoria = null;
		boolean commitOrRollback = false;
		try {
			c = ConnectionManager.getConnection();

			c.setAutoCommit(false);

			categoria = categoriaDAO.findById(c, id);

			commitOrRollback = true;

		} catch (SQLException sqle) {
			logger.error("findById: " + id + ": " + sqle.getMessage(), sqle);
			throw new DataException(id + "", sqle);

		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}
		return categoria;
	}

	@Override
	public List<Categoria> findByNombre(String nombre) throws DataException, ServiceException {

		Connection c = null;
		List<Categoria> categoria = null;

		boolean commitOrRollback = false;
		try {
			c = ConnectionManager.getConnection();

			c.setAutoCommit(false);

			categoria = categoriaDAO.findByNombre(c, nombre);

			commitOrRollback = true;

		} catch (SQLException sqle) {
			if (logger.isErrorEnabled()) {
				logger.error(nombre, sqle);
			}
			throw new DataException(sqle);

		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}
		return categoria;
	}

	public List<Categoria> findByCriteria(CategoriaCriteria categoria) throws DataException {

		Connection c = null;
		boolean commitOrRollback = false;

		try {

			c = ConnectionManager.getConnection();
			c.setAutoCommit(true);

			return categoriaDAO.findByCriteria(c, categoria);

		} catch (SQLException e) {
			if (logger.isErrorEnabled()) {
				logger.error(e);
			}
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}
	}

	@Override
	public Categoria create(Categoria ca) throws DataException, ServiceException {

		Connection c = null;
		boolean commitOrRollback = false;
		Categoria categoria = null;
		try {
			c = ConnectionManager.getConnection();

			// inicio de la transaccion, es como un beggin
			c.setAutoCommit(false);

			categoriaDAO.create(c, ca);

			// fin de la transacción
			commitOrRollback = true;

		} catch (SQLException sqle) {
			if (logger.isErrorEnabled()) {
				logger.error(c, sqle);
			}
			throw new DataException(sqle);

		} finally {
			JDBCUtils.closeConnection(c, commitOrRollback);
		}

		return categoria;
	}

	@Override
	public void update(Categoria ca) throws ServiceException {
		Connection c = null;
		boolean commit = false;

		try {

			c = ConnectionManager.getConnection();

			c.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			c.setAutoCommit(false);

			categoriaDAO.update(c, ca);
			commit = true;

		} catch (SQLException sqle) {

			if (logger.isErrorEnabled()) {
				logger.error(c, sqle);
			}
			throw new DataException(sqle);

		} finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public void deleteById(Long id) throws DataException, InstanceNotFoundException {
		Connection c = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			String sql = " delete " + " from categoria" + " where id = ?";

			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			JDBCUtils.setParameter(preparedStatement, 1, id);
			int deletedRows = preparedStatement.executeUpdate();
			if (deletedRows != 1) {
				throw new InstanceNotFoundException("" + id);
			}
		} catch (SQLException sqle) {
			logger.error("findById: " + id + ": " + sqle.getMessage(), sqle);
			throw new DataException("" + id, sqle);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);
		}

	}
}
