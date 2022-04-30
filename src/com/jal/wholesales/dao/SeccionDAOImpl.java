package com.jal.wholesales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.Marca;
import com.jal.wholesales.model.Seccion;
import com.wholesales.exception.DataException;
import com.wholesales.exception.InstanceNotFoundException;

public class SeccionDAOImpl implements SeccionDAO {
	private static Logger logger = LogManager.getLogger(SeccionDAOImpl.class);

	public SeccionDAOImpl() {
	}

	@Override

	public Seccion findById(Connection c, long id) throws DataException {

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Seccion seccion = null;
		try {

			// SQL
			String sql = "SELECT id, nombre " + " FROM seccion" + " WHERE id = ?";

			logger.debug(sql);

			// create prepared statement
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			JDBCUtils.setParameter(preparedStatement, 1, id);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				seccion = loadNext(rs);
			} else {
				if (logger.isDebugEnabled())
					logger.debug("No se encontró la seccion {}", id);
			}
			if (rs.next()) {
				if (logger.isDebugEnabled())
					logger.debug("Seccion {} duplicada", id);
			}

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);

		}
		return seccion;
	}
	@Override
	public List<Seccion> findByAll(Connection c) throws InstanceNotFoundException, DataException {
		 

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		List<Seccion> seccionList = new ArrayList<Seccion>();
		try {

			// SQL
			String sql = "SELECT id, nombre" + " FROM seccion";
			logger.debug(sql);

			// create prepared statement
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			 
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				seccionList.add(loadNext(rs));
			}

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);

		}
		return seccionList;
	}
	public Seccion create(Connection c, Seccion seccion) throws DataException {

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {

			// SQL

			String sql = " INSERT INTO SECCION(id, nombre) " + " VALUES (?,? ) ";
			if (logger.isDebugEnabled())
				logger.debug(sql);

			// create prepared statement
			preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			JDBCUtils.setParameter(preparedStatement, i++, seccion.getId());
			JDBCUtils.setParameter(preparedStatement, i++, seccion.getNombre());

			int insertedRows = preparedStatement.executeUpdate();
			if (insertedRows == 1) {
				rs = preparedStatement.getGeneratedKeys();

			} else {
				// Lanzar una excepcion
				throw new DataException(seccion.getNombre());
			}

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(seccion.getNombre(), ex);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);

		}
		return seccion;
	}

	public void update(Connection c, Seccion seccion) throws DataException {
		if (logger.isDebugEnabled())
			logger.debug("Seccion = {}" + seccion);

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int updatedRows = 0;
		try {

			// SQL

			String sql = " UPDATE SECCION " + " SET  ID = ?," + "    	 NOMBRE = ?" + "  ID = ? ";
			if (logger.isDebugEnabled())
				logger.debug(sql);
			// create prepared statement
			preparedStatement = c.prepareStatement(sql);

			int i = 1;

			JDBCUtils.setParameter(preparedStatement, i++, seccion.getId());
			JDBCUtils.setParameter(preparedStatement, i++, seccion.getNombre());

			updatedRows = preparedStatement.executeUpdate();
			if (updatedRows != 1) {
				throw new DataException("No se ha podido actualizar" + seccion.getId());
			}

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException("Seccion: " + seccion.getId(), ex);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);

		}
	}

	private Seccion loadNext(ResultSet rs) throws SQLException {
		Seccion seccion = new Seccion();

		int i = 1;
		// HAY QUE CAMBIAR COSAS
		seccion.setId(rs.getLong(i++));
		seccion.setNombre(rs.getString(i++));

		return seccion;

	}

}
