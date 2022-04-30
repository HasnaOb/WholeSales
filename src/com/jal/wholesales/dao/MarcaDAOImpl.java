package com.jal.wholesales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.Marca;
import com.wholesales.exception.DataException;
import com.wholesales.exception.InstanceNotFoundException;

public class MarcaDAOImpl implements MarcaDAO {
	private static Logger logger = LogManager.getLogger(MarcaDAOImpl.class);

	public MarcaDAOImpl() {
	}

	@Override
	public Marca findById(Connection c, long id) throws InstanceNotFoundException, DataException {
		if (logger.isDebugEnabled())
			logger.debug("id = {} ", id);

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Marca marca = null;
		try {

			// SQL
			String sql = "SELECT id, nombre" + " FROM marca" + " WHERE id = ? ";
			logger.debug(sql);

			// create prepared statement
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			JDBCUtils.setParameter(preparedStatement, 1, id);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				marca = loadNext(rs);
				marca.setId(rs.getLong(1));
			}

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);

		}
		return marca;
	}

	public Marca create(Connection c, Marca m) throws DataException {

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {

			// SQL

			String sql = " INSERT INTO MARCA( nombre) " + " VALUES (?) ";
			if (logger.isDebugEnabled())
				logger.debug(sql);

			// create prepared statement
			preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			int i = 1;

			JDBCUtils.setParameter(preparedStatement, i++, m.getNombre());

			int insertedRows = preparedStatement.executeUpdate();
			if (insertedRows == 1) {
				rs = preparedStatement.getGeneratedKeys();
				if (rs.next()) {
					m.setId(rs.getLong(1));
				}
			} else {
				// Lanzar una excepcion
				throw new DataException("No se ha podido crear" + m.getNombre());
			}

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(m.getNombre(), ex);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);

		}
		return m;
	}

	public void update(Connection c, Marca marca) throws InstanceNotFoundException, DataException {
		if (logger.isDebugEnabled())
			logger.debug("Marca = {}" + marca);
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int updatedRows = 0;
		try {

			// SQL

			String sql = " UPDATE MARCA "

					+ "    	 NOMBRE = ?" + "  ID = ? ";
			if (logger.isDebugEnabled())
				logger.debug(sql);
			// create prepared statement
			preparedStatement = c.prepareStatement(sql);

			int i = 1;

			JDBCUtils.setParameter(preparedStatement, i++, marca.getNombre());

			updatedRows = preparedStatement.executeUpdate();
			if (updatedRows != 1) {
				// Lanzar una excepcion
				throw new DataException("No se ha podido actualizar" + marca.getId());
			}

		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException("Marca: " + marca.getNombre(), e);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);

		}

	}

	private Marca loadNext(ResultSet rs) // puede ser static
			throws SQLException {
		Marca marca = new Marca();

		int i = 1;
		// HAY QUE CAMBIAR COSAS
		marca.setId(rs.getLong(i++));
		marca.setNombre(rs.getString(i++));

		return marca;

	}

}
