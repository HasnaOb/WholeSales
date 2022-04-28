package com.jal.wholesales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.Localidad;
import com.wholesales.exception.DataException;
 

public class LocalidadDAOImpl implements LocalidadDAO{
	
	 public LocalidadDAOImpl (){
	 }
	 @Override
	 
	
	 public Localidad findById(Connection c,long  id) {
		 
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Localidad localidad = null;
		try {

			 
			//SQL
			String sql = "SELECT id, nombre, id_provincia" 
					+ " FROM localidad"
					+ " WHERE id = ?";
			System.out.println("LocalidadDAO.findBy:SQL= "+sql);
			
			//create prepared statement
			preparedStatement = c.prepareStatement(sql, 
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			JDBCUtils.setParameter(preparedStatement, 1, id);			
			rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				localidad = loadNext(rs);
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);
			 
		}
		return localidad;
	}

	
	public Localidad create(Connection c ,Localidad localidad) throws DataException {
	 
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {

		 

			String sql = " INSERT INTO LOCALIDAD( nombre, id_provincia) "
					+ " VALUES (?,?) ";

			//create prepared statement
			preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			int i  = 1;


			JDBCUtils.setParameter(preparedStatement, i++, localidad.getNombre());
			JDBCUtils.setParameter(preparedStatement, i++, localidad.getIdProvincia());
		 
			int insertedRows = preparedStatement.executeUpdate();
			if (insertedRows==1) {
				rs = preparedStatement.getGeneratedKeys();
				 
			} else {
				// Lanzar una excepcion
				throw new DataException(localidad.getNombre());
			}
		
		} catch (SQLException e) {			
			e.printStackTrace();
			throw new DataException(localidad.getNombre(), e);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);
			 
		}
		return localidad;
	}

	
	public void update(Connection c,Localidad localidad) throws DataException {
	 
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int updatedRows = 0;
		try {

		 
			//SQL

			String sql =" UPDATE LOCALIDAD "
			
					+ "    	 NOMBRE = ?,"
					+ "      ID_PROVINCIA = ?"
					+ "  ID = ? ";

			//create prepared statement
			preparedStatement = c.prepareStatement(sql);

			int i  = 1;
			 
		 
			JDBCUtils.setParameter(preparedStatement, i++, localidad.getNombre());
			JDBCUtils.setParameter(preparedStatement, i++, localidad.getIdProvincia());
			 


			updatedRows = preparedStatement.executeUpdate();
			if (updatedRows!=1) {
				throw new DataException("No se ha podido actualizar"+localidad.getId());
			}
		
		} catch (SQLException ex) {			
			ex.printStackTrace();
			throw new DataException("Localidad: "+localidad.getId(),ex);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);
		 
		}
	}
	

	
	private Localidad loadNext(ResultSet rs) 
		throws SQLException { 
		Localidad localidad = new Localidad();

		int i = 1;
		 //HAY QUE CAMBIAR COSAS
		localidad.setId(rs.getLong(i++));
		localidad.setNombre(rs.getString(i++));
		localidad.setIdProvincia(rs.getLong(i++));
		return localidad;
		 
	}


}
