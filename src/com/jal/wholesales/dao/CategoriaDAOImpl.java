package com.jal.wholesales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.DAOUtils;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.Categoria;
import com.jal.wholesales.service.CategoriaCriteria;
import com.wholesales.exception.DataException;
import com.wholesales.exception.InstanceNotFoundException;
 
public class CategoriaDAOImpl implements CategoriaDAO{
	
	 public CategoriaDAOImpl (){
	 }
	 @Override
	 
	
	 public Categoria findById (Connection c, long id)
				throws  InstanceNotFoundException,DataException {
	 
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Categoria categoria = null;
		try {

			c = ConnectionManager.getConnection();
			//SQL
			String sql = "SELECT id, nombre, id_categoria_padre" 
					+ " FROM categoria"
					+ " WHERE id = ?";
			System.out.println("CategoriaDAO.findBy:SQL= "+sql);
			
			//create prepared statement
			preparedStatement = c.prepareStatement(sql, 
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			JDBCUtils.setParameter(preparedStatement, 1, id);			
			rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				categoria = loadNext(c,rs);
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);
			 
		}
		return categoria;
	}

	 public List<Categoria> findByNombre (Connection c,String nombre)
				throws  DataException {
		 
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			
			try {

				c = ConnectionManager.getConnection();
				//SQL
				String sql = "SELECT id, nombre, id_categoria_padre" 
						+ " FROM categoria"
						+ " WHERE upper(nombre) like upper (?)";
				System.out.println("CategoriaDAO.findBy:SQL= "+sql);
				
				//create prepared statement
				preparedStatement = c.prepareStatement(sql, 
							ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

				int i = 1;
				preparedStatement.setString(i++, "%" + nombre.toUpperCase() + "%");	
				rs = preparedStatement.executeQuery();
				
				List<Categoria> categoria = new ArrayList<Categoria>();
				Categoria ca = null;
				while (rs.next()) {
					ca = loadNext(c, rs);						
					categoria.add(ca);
				} 
				return categoria;

			} catch (SQLException ex) {			
				ex.printStackTrace();
				throw new DataException("No se encontro la categoria " + nombre);
				
			} finally {
				JDBCUtils.close(rs);
				JDBCUtils.close(preparedStatement);
				
				 
			}
		}
	 
	 
	 public List<Categoria> findByCriteria (Connection c,CategoriaCriteria categoria) throws DataException {
		 
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			StringBuilder queryString = null;
			
			try {

				c = ConnectionManager.getConnection();
				//SQL
				queryString = new StringBuilder("SELECT id, nombre, id_categoria_padre" 
						+ " FROM categoria");
						 
			boolean first=true;
			if(categoria.getNombre() !=null) {
				
				DAOUtils.addClause(queryString, first, " upper(nombre) LIKE upper(?) ");
				first = false;
			}
			 
			
			if(categoria.getIdCategoriaPadre() !=null) {
				
				DAOUtils.addClause(queryString, first, "  id_categoria_padre = ?");
				first = false;
			}
		
			 
			
			preparedStatement = c.prepareStatement(queryString.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			int i=1;
			
			if (categoria.getNombre() != null)
				preparedStatement.setString(i++, "%" + categoria.getNombre() + "%");
			 
			
			if (categoria.getIdCategoriaPadre() != null)
				preparedStatement.setString(i++, "%" + categoria.getIdCategoriaPadre() + "%");
			
			
			 

			rs = preparedStatement.executeQuery();

			List<Categoria> categorias = new ArrayList<Categoria>();                        
			Categoria e = null;

				while (rs.next()) {
					e = loadNext(c, rs);						
					categorias.add(e);
				}
				return categorias;
			
			
			} catch (SQLException ex) {			
				ex.printStackTrace();
				throw new DataException("No se encontro la categoria " + ex);
				
			} finally {
				JDBCUtils.close(rs);
				JDBCUtils.close(preparedStatement);
				
				 
			}
			
		}
	 
	 

	public Categoria create (Connection c, Categoria C) 
			throws  DataException {
	 
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {

			c = ConnectionManager.getConnection();
			//SQL

			String sql = " INSERT INTO CATEGORIA(nombre, id_categoria_padre) "
					+ " VALUES (?,?) ";

			//create prepared statement
			preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			int i  = 1;
		 
			JDBCUtils.setParameter(preparedStatement, i++, C.getNombre());
			JDBCUtils.setParameter(preparedStatement, i++, C.getIdCategoriaPadre(), true);
		 
			int insertedRows = preparedStatement.executeUpdate();
			if (insertedRows==1) {
				rs = preparedStatement.getGeneratedKeys();
				if (rs.next()) {
					C.setId(rs.getLong(1));
				} 
			} else {
				// Lanzar una excepcion
				throw new DataException(C.getNombre());
			}
		
		} catch (SQLException ex) {			
			ex.printStackTrace();
			throw new DataException(C.getNombre(), ex);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);
			 
		}
		return C;
	}
	
	public void  update(Connection c, Categoria C) 
			throws  InstanceNotFoundException, DataException  {
	 
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int updatedRows = 0;
		try {

			c = ConnectionManager.getConnection();
			//SQL

			String sql =" UPDATE CATEGORIA "
					+ " SET  ID = ?,"
					+ "    	 NOMBRE = ?,"
					+ "      ID_CATEGORIA_PADRE = ?"
					+ "  ID = ? ";

			//create prepared statement
			preparedStatement = c.prepareStatement(sql);

			int i  = 1;
			 
			JDBCUtils.setParameter(preparedStatement, i++, C.getId());
			JDBCUtils.setParameter(preparedStatement, i++, C.getNombre());
			JDBCUtils.setParameter(preparedStatement, i++, C.getIdCategoriaPadre());
			 


			updatedRows = preparedStatement.executeUpdate();
			if (updatedRows!=1) {
				throw new DataException(C.getNombre());
			}
		
		} catch (SQLException ex) {			
			ex.printStackTrace();
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);
		 
		}
		 
	}
 
 
	
	private Categoria loadNext(Connection c, ResultSet rs) 
		throws SQLException { 
		Categoria categoria = new Categoria();

		int i = 1;
		 //HAY QUE CAMBIAR COSAS
		categoria.setId(rs.getLong(i++));
		categoria.setNombre(rs.getString(i++));
		categoria.setIdCategoriaPadre(rs.getLong(i++));
		return categoria;
		 
	}
	 
 
}
