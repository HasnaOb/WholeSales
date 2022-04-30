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
import com.jal.wholesales.model.Direccion;
import com.wholesales.exception.DataException;
import com.wholesales.exception.InstanceNotFoundException;

public class DireccionDAOImpl implements DireccionDAO {
	private static Logger logger = LogManager.getLogger(DireccionDAOImpl.class);

	public DireccionDAOImpl() {
	}

	@Override
	public Direccion findById(Connection c, long id) throws InstanceNotFoundException, DataException {

		if (logger.isDebugEnabled())
			logger.debug("id = {} ", id);

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Direccion direccion = null;
		try {

			// SQL
			String sql = "SELECT id, id_empresa, codigo_postal, id_localidad, calle_num " + " FROM direccion "
					+ " WHERE id = ? ";
			logger.debug(sql);

			// create prepared statement
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			JDBCUtils.setParameter(preparedStatement, 1, id);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				direccion = loadNext(rs);
			} else {
				if (logger.isDebugEnabled())
					logger.debug("No se encontró la direccion {}", id);
			}

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);

		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);

		}
		return direccion;
	}

	public List<Direccion> findByEmpresa(Connection c, Long idEmpresa) throws DataException {

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		List<Direccion> results = null;
		try {

			// SQL
			String sql = "SELECT id, id_empresa, codigo_postal, id_localidad, calle_num " + " FROM direccion ";

			if (logger.isDebugEnabled())
				logger.debug(sql);

			// create prepared statement
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			rs = preparedStatement.executeQuery();

			results = new ArrayList<Direccion>();

			Direccion direccion = null;
			while (rs.next()) {
				direccion = loadNext(rs);
				results.add(direccion);
			}

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException("No se encontro la direccion " + idEmpresa);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);

		}
		return results;
	}

	public Direccion create(Connection c, Direccion direccion) throws DataException {

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {

			// SQL

			String sql = " INSERT INTO DIRECCION(id_empresa, codigo_postal, id_localidad, calle_num) "
					+ " VALUES (?,?,?,?) ";
			if (logger.isDebugEnabled())
				logger.debug(sql);

			// create prepared statement
			preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			int i = 1;

			JDBCUtils.setParameter(preparedStatement, i++, direccion.getIdEmpresa());
			JDBCUtils.setParameter(preparedStatement, i++, direccion.getCodigoPostal());
			JDBCUtils.setParameter(preparedStatement, i++, direccion.getIdLocalidad());
			JDBCUtils.setParameter(preparedStatement, i++, direccion.getCalleNum());

			int insertedRows = preparedStatement.executeUpdate();
			if (insertedRows == 1) {
				rs = preparedStatement.getGeneratedKeys();
				if (rs.next()) {
					direccion.setId(rs.getLong(1));
				}
			} else {
				// Lanzar una excepcion
				throw new DataException("" + direccion.getId());
			}

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);

		}
		return direccion;
	}

	public void update(Connection c, Direccion direccion) throws DataException {
		if (logger.isDebugEnabled())
			logger.debug("Direccion = {}" + direccion);

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int updatedRows = 0;
		try {

			// SQL

			String sql = " UPDATE Direccion " + "    	 ID_EMPRESA= ?," + "      CODIGO_POSTAL = ?,"
					+ "      ID_LOCALIDAD = ?," + "      CALLE_NUM = ?" + "  ID = ? ";
			if (logger.isDebugEnabled())
				logger.debug(sql);
			// create prepared statement
			preparedStatement = c.prepareStatement(sql);

			int i = 1;
			JDBCUtils.setParameter(preparedStatement, i++, direccion.getIdEmpresa());
			JDBCUtils.setParameter(preparedStatement, i++, direccion.getCodigoPostal());
			JDBCUtils.setParameter(preparedStatement, i++, direccion.getIdLocalidad());
			JDBCUtils.setParameter(preparedStatement, i++, direccion.getCalleNum());

			updatedRows = preparedStatement.executeUpdate();
			if (updatedRows != 1) {
				throw new DataException("No se ha podido actualizar" + direccion.getId());
			}

		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);

		}

	}

	private Direccion loadNext(ResultSet rs) throws SQLException {
		Direccion direccion = new Direccion();

		int i = 1;
		// HAY QUE CAMBIAR COSAS
		direccion.setId(rs.getLong(i++));
		direccion.setIdEmpresa(rs.getLong(i++));
		direccion.setCodigoPostal(rs.getLong(i++));
		direccion.setIdLocalidad(rs.getLong(i++));
		direccion.setCalleNum(rs.getString(i++));
		return direccion;

	}

}
