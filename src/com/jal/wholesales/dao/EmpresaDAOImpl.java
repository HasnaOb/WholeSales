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
import com.jal.wholesales.dao.util.DAOUtils;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.Empresa;
import com.jal.wholesales.service.EmpresaCriteria;
import com.wholesales.exception.DataException;
import com.wholesales.exception.InstanceNotFoundException;

public class EmpresaDAOImpl implements EmpresaDAO {

	private static Logger logger = LogManager.getLogger(EmpresaDAOImpl.class);

	public EmpresaDAOImpl() {
	}

	@Override
	public Empresa findById(Connection c, long id) throws InstanceNotFoundException, DataException {

		if (logger.isDebugEnabled())
			logger.debug("id = {} ", id);

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Empresa empresa = null;

		try {

			// SQL
			String sql = "SELECT id, nombre, cif, id_tipo_empresa, nombre_usuario, email, contrasena " + " FROM empresa"
					+ " WHERE id = ?";

			logger.debug(sql);

			// create prepared statement
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			JDBCUtils.setParameter(preparedStatement, 1, id);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				empresa = loadNext(c, rs);
			} else {
				if (logger.isDebugEnabled())
					logger.debug("No se encontró la empresa {}", id);
			}
			if (rs.next()) {
				if (logger.isDebugEnabled())
					logger.debug("Empresa {} duplicada", id);
			}

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);

		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);

		}
		return empresa;
	}

	public Empresa findByEmail(Connection c, String email) throws DataException {

		if (logger.isDebugEnabled())
			logger.debug("Email = {}", (email == null));

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Empresa empresa = null;
		try {

			// SQL
			String sql = "SELECT id, nombre, cif, id_tipo_empresa, nombre_usuario, email, contrasena" + " FROM empresa"
					+ " WHERE upper(email) = upper(?)";

			if (logger.isDebugEnabled())
				logger.debug(sql);

			// create prepared statement
			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			JDBCUtils.setParameter(preparedStatement, 1, email);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				empresa = loadNext(c, rs);
			} else {
				if (logger.isDebugEnabled())
					logger.debug("No se encuentra la empresa {}", email);
			}
			if (rs.next()) {
				if (logger.isDebugEnabled())
					logger.debug("Empresa {} duplicada", email);
			}
		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);

		}
		return empresa;
	}

	public List<Empresa> findByNombre(Connection c, String nombre) throws DataException {

		if (logger.isDebugEnabled())
			logger.debug("nombre = {} ", nombre);

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {

			// SQL
			String sql = "SELECT id, nombre, cif, id_tipo_empresa, nombre_usuario, email, contrasena" + " FROM empresa"
					+ " WHERE upper(nombre) like upper (?)";

			// create prepared statement
			if (logger.isDebugEnabled())
				logger.debug(sql);

			preparedStatement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setString(i++, "%" + nombre.toUpperCase() + "%");
			rs = preparedStatement.executeQuery();

			List<Empresa> empresa = new ArrayList<Empresa>();
			Empresa e = null;
			while (rs.next()) {
				e = loadNext(c, rs);
				empresa.add(e);
			}
			return empresa;

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException("No se encontro la empresa " + nombre);

		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);

		}
	}

	public List<Empresa> findByCriteria(Connection c, EmpresaCriteria empresa) throws DataException {

		if (logger.isDebugEnabled())
			logger.debug("criteria = {}", empresa);

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		StringBuilder queryString = null;

		try {

			// SQL
			queryString = new StringBuilder(
					"SELECT id, nombre, cif, id_tipo_empresa, nombre_usuario, email, contrasena" + " FROM empresa");

			boolean first = true;
			if (empresa.getNombre() != null) {

				DAOUtils.addClause(queryString, first, " upper(nombre) LIKE upper(?) ");
				first = false;
			}

			if (empresa.getIdTipoEmpresa() != null) {

				DAOUtils.addClause(queryString, first, "  id_tipo_empresa = ?");
				first = false;
			}

			if (logger.isDebugEnabled())
				logger.debug(queryString);

			preparedStatement = c.prepareStatement(queryString.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			int i = 1;

			if (empresa.getNombre() != null)
				preparedStatement.setString(i++, "%" + empresa.getNombre() + "%");

			if (empresa.getIdTipoEmpresa() != null)
				preparedStatement.setString(i++, "%" + empresa.getIdTipoEmpresa() + "%");

			rs = preparedStatement.executeQuery();

			List<Empresa> empresas = new ArrayList<Empresa>();
			Empresa e = null;

			while (rs.next()) {
				e = loadNext(c, rs);
				empresas.add(e);
			}

			return empresas;

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException("No se encontro la empresa " + ex);

		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);

		}

	}

	public Empresa create(Connection c, Empresa e) throws DataException {
//		 
//			if(logger.isDebugEnabled()) logger.debug("Empresa = email: {}; id_pais: {}; pssw: {}; nombre: {}; apellido1: {}; apellido2: {}; ano_nacimiento: {}; fecha_subscripcion: {}; id_nivel: {}, id_genero: {}",
//					e.getEmail(), e.getCif(), e.getIdTipoEmpresa()==null, e.getNombre(), e.getNombreUsuario(), e.getContrasena() etIdGenero());
//		

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {

			// SQL

			String sql = " INSERT INTO EMPRESA(nombre, cif, id_tipo_empresa, nombre_usuario, email, contrasena) "
					+ " VALUES (?,?,?,?,?,?) ";
			if (logger.isDebugEnabled())
				logger.debug(sql);

			// create prepared statement
			preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			int i = 1;

			JDBCUtils.setParameter(preparedStatement, i++, e.getNombre());
			JDBCUtils.setParameter(preparedStatement, i++, e.getCif());
			JDBCUtils.setParameter(preparedStatement, i++, e.getIdTipoEmpresa());
			JDBCUtils.setParameter(preparedStatement, i++, e.getNombreUsuario());
			JDBCUtils.setParameter(preparedStatement, i++, e.getEmail());
			JDBCUtils.setParameter(preparedStatement, i++, e.getContrasenaEncriptada());

			int insertedRows = preparedStatement.executeUpdate();
			if (insertedRows == 1) {
				rs = preparedStatement.getGeneratedKeys();
				if (rs.next()) {
					e.setId(rs.getLong(1));
				}
			} else {
				// Lanzar una excepcion
				throw new DataException(e.getNombre());
			}

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(e.getNombre(), ex);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);

		}
		return e;
	}

	public void update(Connection c, Empresa e) throws InstanceNotFoundException, DataException {
		if (logger.isDebugEnabled())
			logger.debug("Empresa = {}" + e);

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int updatedRows = 0;
		try {

			// SQL

			String sql = " UPDATE Empresa " + " SET  NOMBRE = ?," + "      CIF = ?," + "      ID_TIPO_EMPRESA = ?,"
					+ "      NOMBRE_USUARIO = ?," + "      EMAIL = ?," + "      CONTRASENA = ?" + "  ID = ? ";
			if (logger.isDebugEnabled())
				logger.debug(sql);
			// create prepared statement
			preparedStatement = c.prepareStatement(sql);

			int i = 1;

			JDBCUtils.setParameter(preparedStatement, i++, e.getNombre());
			JDBCUtils.setParameter(preparedStatement, i++, e.getCif());
			JDBCUtils.setParameter(preparedStatement, i++, e.getIdTipoEmpresa());
			JDBCUtils.setParameter(preparedStatement, i++, e.getNombreUsuario());
			JDBCUtils.setParameter(preparedStatement, i++, e.getEmail());
			JDBCUtils.setParameter(preparedStatement, i++, e.getContrasenaEncriptada());

			updatedRows = preparedStatement.executeUpdate();
			if (updatedRows != 1) {
				throw new DataException("No se ha podido actualizar" + e.getId());
			}

		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException("Empresa: " + e.getId(), ex);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);

		}

	}

	@Override
	public void deleteById(Connection c, Long id) throws DataException, InstanceNotFoundException {

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			String sql = " delete " + " from empresa" + " where id = ?";
			preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			JDBCUtils.setParameter(preparedStatement, i++, id);

			int deletedRows = preparedStatement.executeUpdate();
			if (deletedRows!=1) {
				throw new InstanceNotFoundException(""+id);
			}

			if (logger.isInfoEnabled()) {
				logger.info("deleteById query = " + preparedStatement.toString());
			}

		} catch (SQLException e) {
			logger.error("deleteById: ", id, e.getMessage(), e);
			throw new DataException("deleteById: " + id + ": " + e.getMessage(), e);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);
		}
	}
//				preparedStatement = c.prepareStatement(sql,
//						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//				
//				JDBCUtils.setParameter(preparedStatement, 1, id); 
//				int deletedRows = preparedStatement.executeUpdate();
//				if (deletedRows!=1) {
//					throw new InstanceNotFoundException(""+id);
//				}
//			} catch (SQLException sqle) {
//				sqle.printStackTrace();
//				throw new DataException(""+id, sqle);
//			} catch (InstanceNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				throw new DataException();
//			} finally {
//				JDBCUtils.close(rs);
//				JDBCUtils.close(preparedStatement);
//			}
//		} 

	private Empresa loadNext(Connection c, ResultSet rs) throws SQLException {
		Empresa empresa = new Empresa();

		int i = 1;
		// HAY QUE CAMBIAR COSAS
		empresa.setId(rs.getLong(i++));
		empresa.setNombre(rs.getString(i++));
		empresa.setCif(rs.getString(i++));
		empresa.setIdTipoEmpresa(rs.getLong(i++));
		empresa.setNombreUsuario(rs.getString(i++));
		empresa.setEmail(rs.getString(i++));
		empresa.setContrasenaEncriptada(rs.getString(i++));

		return empresa;

	}

}
