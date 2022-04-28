package com.jal.wholesales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.Direccion;
import com.wholesales.exception.DataException;
import com.wholesales.exception.InstanceNotFoundException;
 
 

public  class DireccionDAOImpl implements DireccionDAO  { 
	public DireccionDAOImpl(){
	}
	
	@Override
	public   Direccion findById(Connection c ,long id) 	throws  InstanceNotFoundException,DataException{
		 
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Direccion direccion = null;
		try {

		 
			//SQL
			String sql ="SELECT id, id_empresa, codigo_postal, id_localidad, calle_num " 
					+ " FROM direccion "
					+ " WHERE id = ? ";
			
			
			//create prepared statement
			preparedStatement = c.prepareStatement(sql, 
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			JDBCUtils.setParameter(preparedStatement, 1, id);			
			rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				direccion = loadNext(rs);
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);
			 
		}
		return direccion;
	}

	public  List<Direccion> findByEmpresa( Connection c,Long idEmpresa) throws DataException {
		 
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		List<Direccion> results = null;
		try {

		 
			//SQL
			String sql ="SELECT id, id_empresa, codigo_postal, id_localidad, calle_num " 
					+ " FROM direccion ";
		
 
			 

			System.out.println("DireccionDAO.findBy:SQL= "+sql);

			//create prepared statement
			preparedStatement = c.prepareStatement(sql, 
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			rs = preparedStatement.executeQuery();

			results = new ArrayList <Direccion>();

			Direccion direccion = null;
			while (rs.next()) {
				direccion = loadNext(rs);
				results.add(direccion);
			}

		} catch (SQLException e) {			
			e.printStackTrace();
			throw new DataException(e);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);
			 
		}
		return results;	
	}

	
	public Direccion create(Connection c,Direccion direccion) throws DataException {
		
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {

			 
			//SQL

			String sql = " INSERT INTO DIRECCION(id_empresa, codigo_postal, id_localidad, calle_num) "
					+ " VALUES (?,?,?,?) ";

			//create prepared statement
			preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			int i  = 1;
			 
			JDBCUtils.setParameter(preparedStatement, i++, direccion.getIdEmpresa());
			JDBCUtils.setParameter(preparedStatement, i++, direccion.getCodigoPostal());
			JDBCUtils.setParameter(preparedStatement, i++, direccion.getIdLocalidad()); 
			JDBCUtils.setParameter(preparedStatement, i++, direccion.getCalleNum());				
			 
		 

			int insertedRows = preparedStatement.executeUpdate();
			if (insertedRows==1) {
				rs = preparedStatement.getGeneratedKeys();
				if (rs.next()) {
					direccion.setId(rs.getLong(1));
				} 
			} else {
				// Lanzar una excepcion
				throw new DataException(""+direccion.getId());
			}
		
		} catch (SQLException e) {			
			e.printStackTrace();
			throw new DataException(e);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);
			 
		}
		return direccion;
	}

	
	public void update(Connection c,Direccion direccion) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int updatedRows = 0;
		try {

		 
			//SQL

			String sql =" UPDATE Direccion "
					+ "    	 ID_EMPRESA= ?,"
					+ "      CODIGO_POSTAL = ?,"
					+ "      ID_LOCALIDAD = ?,"
					+ "      CALLE_NUM = ?"
					+ "  ID = ? ";

			//create prepared statement
			preparedStatement = c.prepareStatement(sql);

			int i  = 1;
			JDBCUtils.setParameter(preparedStatement, i++, direccion.getIdEmpresa());
			JDBCUtils.setParameter(preparedStatement, i++, direccion.getCodigoPostal());
			JDBCUtils.setParameter(preparedStatement, i++, direccion.getIdLocalidad()); 
			JDBCUtils.setParameter(preparedStatement, i++, direccion.getCalleNum());				

			updatedRows = preparedStatement.executeUpdate();
			if (updatedRows!=1) {
				throw new DataException("No se ha podido actualizar"+direccion.getId());
			}
		
		
		} catch (SQLException e) {			
			e.printStackTrace();
			throw new DataException(e);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);
			 
		}
		 
	}
	
 
	
	private Direccion loadNext(ResultSet rs) 
		throws SQLException { 
		Direccion  direccion = new Direccion();

		int i = 1;
		 //HAY QUE CAMBIAR COSAS
		direccion.setId(rs.getLong(i++));
		direccion.setIdEmpresa(rs.getLong(i++));
		direccion.setCodigoPostal(rs.getLong(i++));
		direccion.setIdLocalidad(rs.getLong(i++)); 
		direccion.setCalleNum(rs.getString(i++));
		return direccion;
		 
	}

	@Override
	public List<Direccion> findByEmpresa(Connection c, long idEmpresa) throws InstanceNotFoundException, DataException {
		// TODO Auto-generated method stub
		return null;
	}
 
}
