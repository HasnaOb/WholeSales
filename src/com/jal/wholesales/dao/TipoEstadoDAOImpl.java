package com.jal.wholesales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.TipoEstado;
import com.wholesales.exception.DataException;

public class TipoEstadoDAOImpl implements TipoEstadoDAO {
	private static Logger logger = LogManager.getLogger(TipoEstadoDAOImpl.class);

	public TipoEstadoDAOImpl() {
	}

	@Override

	public TipoEstado findById(Connection c, long id) throws DataException {

		if (logger.isDebugEnabled())
			logger.debug("id = {} ", id);

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		TipoEstado tipoEstado = null;
		try {

			// SQL
			String sql = "SELECT id, nombre" + " FROM tipo_estado" + " WHERE id = ?";
			logger.debug(sql);

			// create prepared statement
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			JDBCUtils.setParameter(preparedStatement, 1, id);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				tipoEstado = loadNext(rs);
			} else {
				if (logger.isDebugEnabled())
					logger.debug("No se encontró el tipo de estado {}", id);
			}
			if (rs.next()) {
				if (logger.isDebugEnabled())
					logger.debug("TipoEstado {} duplicado", id);
			}

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);

		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);

		}
		return tipoEstado;
	}

	public TipoEstado create(Connection c, TipoEstado tp) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {

			// SQL

			String sql = " INSERT INTO TIPO_ESTADO(id, nombre) " + " VALUES (?,?) ";
			if (logger.isDebugEnabled())
				logger.debug(sql);

			// create prepared statement
			preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			JDBCUtils.setParameter(preparedStatement, i++, tp.getId());
			JDBCUtils.setParameter(preparedStatement, i++, tp.getNombre());

			int insertedRows = preparedStatement.executeUpdate();
			if (insertedRows == 1) {
				rs = preparedStatement.getGeneratedKeys();
				if (rs.next()) {
					tp.setId(rs.getLong(1));
				}
			} else {
				// Lanzar una excepcion
				throw new DataException(tp.getNombre());
			}

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(tp.getNombre(), ex);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);

		}
		return tp;
	}

	public void update(Connection c, TipoEstado tipoEstado) throws DataException {
		if (logger.isDebugEnabled())
			logger.debug("TipoEstado = {}" + tipoEstado);

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int updatedRows = 0;
		try {

			// SQL

			String sql = " UPDATE TIPOS_ESTADO " + " SET  ID = ?," + "    	 NOMBRE = ?" + "  ID = ? ";
			if (logger.isDebugEnabled())
				logger.debug(sql);
			// create prepared statement
			preparedStatement = c.prepareStatement(sql);

			int i = 1;

			JDBCUtils.setParameter(preparedStatement, i++, tipoEstado.getId());
			JDBCUtils.setParameter(preparedStatement, i++, tipoEstado.getNombre());

			updatedRows = preparedStatement.executeUpdate();
			if (updatedRows != 1) {
				throw new DataException("No se ha podido actualizar" + tipoEstado.getId());
			}

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException("TipoEstado: " + tipoEstado.getId(), ex);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);

		}

	}

	private TipoEstado loadNext(ResultSet rs) throws SQLException {
		TipoEstado tipoEstado = new TipoEstado();

		int i = 1;
		// HAY QUE CAMBIAR COSAS
		tipoEstado.setId(rs.getLong(i++));
		tipoEstado.setNombre(rs.getString(i++));

		return tipoEstado;

	}

}
