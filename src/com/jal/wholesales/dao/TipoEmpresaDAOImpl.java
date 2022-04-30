package com.jal.wholesales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.TipoEmpresa;
import com.wholesales.exception.DataException;

public class TipoEmpresaDAOImpl implements TipoEmpresaDAO {
	private static Logger logger = LogManager.getLogger(TipoEmpresaDAOImpl.class);

	public TipoEmpresaDAOImpl() {
	}

	@Override
	public TipoEmpresa findById(Connection c, long id) throws DataException {
		if (logger.isDebugEnabled())
			logger.debug("id = {} ", id);

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		TipoEmpresa tipoEmpresa = null;
		try {

			// SQL
			String sql = "SELECT id, nombre" + " FROM tipo_empresa" + " WHERE id = ? ";
			logger.debug(sql);
			// create prepared statement
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			JDBCUtils.setParameter(preparedStatement, 1, id);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				tipoEmpresa = loadNext(rs);
			} else {
				if (logger.isDebugEnabled())
					logger.debug("No se encontró el tipo de empresa {}", id);
			}
			if (rs.next()) {
				if (logger.isDebugEnabled())
					logger.debug("Tipo de empresa {} duplicado", id);
			}

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);

		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);

		}
		return tipoEmpresa;
	}

	/*
	 * public Long create(TipoEmpresa tipoEmpresa) { Connection con = null;
	 * PreparedStatement preparedStatement = null; ResultSet rs = null;
	 * 
	 * try {
	 * 
	 * 
	 * 
	 * String sql = " INSERT INTO TIPOEMPRESA(id, nombre) " + " VALUES (?,?) ";
	 * 
	 * //create prepared statement preparedStatement = con.prepareStatement(sql,
	 * Statement.RETURN_GENERATED_KEYS);
	 * 
	 * int i = 1; JDBCUtils.setParameter(preparedStatement, i++,
	 * tipoEmpresa.getId()); JDBCUtils.setParameter(preparedStatement, i++,
	 * tipoEmpresa.getNombre());
	 * 
	 * 
	 * int insertedRows = preparedStatement.executeUpdate(); if (insertedRows==1) {
	 * rs = preparedStatement.getGeneratedKeys();
	 * 
	 * } else { // Lanzar una excepcion }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } finally {
	 * JDBCUtils.close(rs); JDBCUtils.close(preparedStatement);
	 * JDBCUtils.close(con); } return tipoEmpresa.getId(); }
	 * 
	 * 
	 * public int update (TipoEmpresa tipoEmpresa) { Connection con = null;
	 * PreparedStatement preparedStatement = null; ResultSet rs = null; int
	 * updatedRows = 0; try {
	 * 
	 * 
	 * //SQL
	 * 
	 * String sql =" UPDATE TIPOEMPRESA " + " SET  ID = ?," + "    	 NOMBRE = ?" +
	 * "  ID = ? ";
	 * 
	 * //create prepared statement preparedStatement = con.prepareStatement(sql);
	 * 
	 * int i = 1;
	 * 
	 * JDBCUtils.setParameter(preparedStatement, i++, tipoEmpresa.getId());
	 * JDBCUtils.setParameter(preparedStatement, i++, tipoEmpresa.getNombre());
	 * 
	 * 
	 * updatedRows = preparedStatement.executeUpdate(); if (updatedRows!=1) { //
	 * Lanzar una excepcion }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } finally {
	 * JDBCUtils.close(rs); JDBCUtils.close(preparedStatement);
	 * JDBCUtils.close(con); } return updatedRows; }
	 * 
	 */

	private TipoEmpresa loadNext(ResultSet rs) // puede ser static
			throws SQLException {
		TipoEmpresa tipoEmpresa = new TipoEmpresa();

		int i = 1;
		// HAY QUE CAMBIAR COSAS
		tipoEmpresa.setId(rs.getLong(i++));
		tipoEmpresa.setNombre(rs.getString(i++));

		return tipoEmpresa;

	}

}
