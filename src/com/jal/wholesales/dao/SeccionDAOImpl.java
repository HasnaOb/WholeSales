package com.jal.wholesales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.Seccion;
import com.wholesales.exception.DataException;

public class SeccionDAOImpl implements SeccionDAO{
	 public SeccionDAOImpl (){
	 }
	 @Override
	 
	
	 public Seccion findById(Connection c,long id) {
		 
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Seccion seccion = null;
		try {

			c = ConnectionManager.getConnection();
			//SQL
			String sql = "SELECT id, nombre " 
					+ " FROM seccion"
					+ " WHERE id = ?";
			System.out.println("SeccionDAO.findBy:SQL= "+sql);
			
			//create prepared statement
			preparedStatement = c.prepareStatement(sql, 
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			JDBCUtils.setParameter(preparedStatement, 1, id);			
			rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				seccion = loadNext(rs);
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);
			 
		}
		return seccion;
	}

	
	public Seccion create(Connection c, Seccion seccion) throws DataException {
		
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {

			c = ConnectionManager.getConnection();
			//SQL

			String sql = " INSERT INTO SECCION(id, nombre) "
					+ " VALUES (?,? ) ";

			//create prepared statement
			preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			int i  = 1;
			JDBCUtils.setParameter(preparedStatement, i++, seccion.getId());
			JDBCUtils.setParameter(preparedStatement, i++, seccion.getNombre());
 
		 
			int insertedRows = preparedStatement.executeUpdate();
			if (insertedRows==1) {
				rs = preparedStatement.getGeneratedKeys();
				 
			} else {
				// Lanzar una excepcion
				throw new DataException(seccion.getNombre());
			}
		
		} catch (SQLException e) {			
			e.printStackTrace();
			throw new DataException(seccion.getNombre(), e);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);
			 
		}
		return seccion;
	}

	
	public void update( Connection c, Seccion seccion) throws DataException {
		 
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int updatedRows = 0;
		try {

			c = ConnectionManager.getConnection();
			//SQL

			String sql =" UPDATE SECCION "
					+ " SET  ID = ?,"
					+ "    	 NOMBRE = ?"
					+ "  ID = ? ";

			//create prepared statement
			preparedStatement = c.prepareStatement(sql);

			int i  = 1;
			 
			JDBCUtils.setParameter(preparedStatement, i++, seccion.getId());
			JDBCUtils.setParameter(preparedStatement, i++, seccion.getNombre());
 
			 


			updatedRows = preparedStatement.executeUpdate();
			if (updatedRows!=1) {
				throw new DataException("No se ha podido actualizar"+seccion.getId());
			}
		
		} catch (SQLException ex) {			
			ex.printStackTrace();
			throw new DataException("Seccion: "+seccion.getId(),ex);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);
		 
		}
	}
	

 
	
	private Seccion loadNext(ResultSet rs) 
		throws SQLException { 
		Seccion seccion = new Seccion();

		int i = 1;
		 //HAY QUE CAMBIAR COSAS
		seccion.setId(rs.getLong(i++));
		seccion.setNombre(rs.getString(i++));
		 
		return seccion;
		 
	}

 
}
