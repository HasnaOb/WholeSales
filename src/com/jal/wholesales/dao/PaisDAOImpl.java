package com.jal.wholesales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.Pais;
import com.wholesales.exception.DataException;
 

public class PaisDAOImpl implements PaisDAO {
	 public PaisDAOImpl (){
	 }
	 @Override
	 
	
	 public Pais findById(Connection c,long  id) {
		 
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Pais pais = null;
		try {

			c = ConnectionManager.getConnection();
			//SQL
			String sql = "SELECT id, nombre" 
					+ " FROM pais"
					+ " WHERE id = ?";
			System.out.println("PaisDAO.findBy:SQL= "+sql);
			
			//create prepared statement
			preparedStatement = c.prepareStatement(sql, 
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			JDBCUtils.setParameter(preparedStatement, 1, id);			
			rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				pais = loadNext(rs);
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);
			 
		}
		return pais;
	}

	
	public Pais create(Connection c ,Pais p) throws DataException {
	 
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {

			c = ConnectionManager.getConnection();
			//SQL

			String sql = " INSERT INTO PAIS( nombre) "
					+ " VALUES (?) ";

			//create prepared statement
			preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			int i  = 1;


			JDBCUtils.setParameter(preparedStatement, i++, p.getNombre());

		 
			int insertedRows = preparedStatement.executeUpdate();
			if (insertedRows==1) {
				rs = preparedStatement.getGeneratedKeys();
				 
			} else {
				// Lanzar una excepcion
				throw new DataException(p.getNombre());
			}
		
		} catch (SQLException e) {			
			e.printStackTrace();
			throw new DataException(p.getNombre(), e);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);
			 
		}
		return p;
	}

	
	public void update(Connection c,Pais pais) throws DataException {
	 
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int updatedRows = 0;
		try {

			c = ConnectionManager.getConnection();
			//SQL

			String sql =" UPDATE PAIS "
			
					+ "    	 NOMBRE = ?,"		
					+ "  ID = ? ";

			//create prepared statement
			preparedStatement = c.prepareStatement(sql);

			int i  = 1;
			 
		 
			JDBCUtils.setParameter(preparedStatement, i++, pais.getNombre());
		
			 


			updatedRows = preparedStatement.executeUpdate();
			if (updatedRows!=1) {
				throw new DataException("No se ha podido actualizar"+pais.getId());
			}
		
		} catch (SQLException ex) {			
			ex.printStackTrace();
			throw new DataException("Pais: "+pais.getId(),ex);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);
		 
		}
	}
	

	
	private Pais loadNext(ResultSet rs) 
		throws SQLException { 
		Pais pais = new Pais();

		int i = 1;
		 //HAY QUE CAMBIAR COSAS
		pais.setId(rs.getLong(i++));
		pais.setNombre(rs.getString(i++));
	 
		return pais;
		 
	}


}
