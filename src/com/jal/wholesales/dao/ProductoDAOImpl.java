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

import com.jal.wholesales.dao.util.*;
import com.jal.wholesales.model.Producto;
import com.jal.wholesales.service.ProductoCriteria;
import com.wholesales.exception.DataException;
import com.wholesales.exception.InstanceNotFoundException;

public class ProductoDAOImpl implements ProductoDAO{
	private static Logger logger = LogManager.getLogger(ValoracionDAOImpl.class);
	
	public ProductoDAOImpl(){
		
	}
	

	public  Producto findById(Connection c, long id) 
			throws DataException  {
		 
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Producto producto = null;
		try {

			c = ConnectionManager.getConnection();
			//SQL
			String sql = "SELECT id, nombre, descripcion, precio, id_categoria, id_empresa, id_seccion, id_marca, id_pais" 
					+ " FROM producto"
					+ " WHERE id = ?";
			System.out.println("ProductoDAO.findBy:SQL= "+sql);
			
			//create prepared statement
			preparedStatement = c.prepareStatement(sql, 
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			JDBCUtils.setParameter(preparedStatement, 1, id);			
			rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				producto = loadNext(c, rs);
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);
			 
		}
		return producto;
	}

	public  List<Producto> findByAll( ) throws DataException {
		Connection c = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		List<Producto> results = null;
		try {

			c = ConnectionManager.getConnection();
			//SQL
			String sql = " SELECT nombre, descripcion, precio, id_categoria, id_empresa, id_seccion, id_marca, id_pais " 
				 
					+ " FROM producto ";

		 
			System.out.println("ProductoDAO.findBy:SQL= "+sql);

			//create prepared statement
			preparedStatement = c.prepareStatement(sql, 
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			rs = preparedStatement.executeQuery();

			results = new ArrayList <Producto>();

			Producto producto = null;
			while (rs.next()) {
				producto = loadNext(c, rs);
				results.add(producto);
			}

		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);
			 
		}
		return results;	
	}


	public List<Producto> findByNombre (Connection c,String nombre)
				throws  DataException {
	 
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
	
			c = ConnectionManager.getConnection();
			//SQL
			String sql = "SELECT id, nombre, descripcion, precio, id_categoria, id_empresa, id_seccion, id_marca, id_pais" 
					+ " FROM producto"
					+ " WHERE upper(nombre) like upper (?)";
			System.out.println("EmpresaDAO.findBy:SQL= "+sql);
			
			//create prepared statement
			preparedStatement = c.prepareStatement(sql, 
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	
			int i = 1;
			preparedStatement.setString(i++, "%" + nombre.toUpperCase() + "%");	
			rs = preparedStatement.executeQuery();
			
			List<Producto> producto = new ArrayList<Producto>();
			Producto p = null;
			while (rs.next()) {
				p = loadNext(c, rs);						
				producto.add(p);
			} 
			return producto;
	
		} catch (SQLException ex) {			
			ex.printStackTrace();
			throw new DataException("No se encontro la empresa " + nombre);
			
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);
			
			 
		}
	}
		public List<Producto> findByCriteria (Connection c,ProductoCriteria producto)
				throws  DataException {
		 
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			StringBuilder queryString = null;
			
			try {
		
				c = ConnectionManager.getConnection();
				//SQL
				queryString = new StringBuilder("SELECT id, nombre, descripcion, precio, id_categoria, id_empresa, id_seccion, id_marca, id_pais"
						+ "	FROM producto");
					
						 
			boolean first=true;
			if(producto.getNombre() !=null) {
				
				DAOUtils.addClause(queryString, first, " upper(nombre) LIKE upper(?) ");
				first = false;
			}
			if(producto.getDescripcion() !=null) {
				
				DAOUtils.addClause(queryString, first, " upper(descripcion) LIKE upper(?) ");
				first = false;
			}
			
			if(producto.getPrecio() !=0) {
				
				DAOUtils.addClause(queryString, first, "  precio = ?");
				first = false;
			}
			if(producto.getIdCategoria() !=null) {
				
				DAOUtils.addClause(queryString, first, " id_categoria = ?");
				first = false;
			}
			if(producto.getIdEmpresa() !=null) {
				
				DAOUtils.addClause(queryString, first, " id_empresa = ?");
				first = false;
			}
			if(producto.getIdSeccion() !=null) {
				
				DAOUtils.addClause(queryString, first, " id_seccion=? ");
				first = false;
			}
			if(producto.getIdMarca() !=null) {
				
				DAOUtils.addClause(queryString, first, " id_marca = ? ");
				first = false;
			}
			if(producto.getIdPais() !=null) {
				
				DAOUtils.addClause(queryString, first, " id_pais = ? ");
				first = false;
			}
			System.out.println(queryString);
			preparedStatement = c.prepareStatement(queryString.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			int i=1;
			
			if (producto.getNombre() != null)
				preparedStatement.setString(i++, "%" + producto.getNombre() + "%");
			
			if (producto.getDescripcion() != null)
				preparedStatement.setString(i++, "%" + producto.getDescripcion() + "%");
			
			if (producto.getPrecio() != 0)
				preparedStatement.setString(i++, "%" + producto.getPrecio() + "%");
			
			
			if (producto.getIdCategoria() != null)
				preparedStatement.setString(i++, "%" + producto.getIdCategoria() + "%");

			
			
			if (producto.getIdEmpresa() != null)
				preparedStatement.setString(i++, "%" + producto.getIdEmpresa() + "%");
			
			
			
			if (producto.getIdSeccion() != null)
				preparedStatement.setString(i++, "%" + producto.getIdSeccion() + "%");
			
			
			if (producto.getIdMarca() != null)
				preparedStatement.setString(i++, "%" + producto.getIdMarca() + "%");
		
			if (producto.getIdPais() != null)
				preparedStatement.setString(i++, "%" + producto.getIdPais() + "%");
			 
			
			rs = preparedStatement.executeQuery();
		
			List<Producto> productos = new ArrayList<Producto>();                        
			Producto p = null;
		
				while (rs.next()) {
					p = loadNext(c, rs);						
					productos.add(p);
				}
				return productos;
			
			
			} catch (SQLException ex) {			
				ex.printStackTrace();
				throw new DataException("No se encontro el producto " + ex);
				
			} finally {
				JDBCUtils.close(rs);
				JDBCUtils.close(preparedStatement);
				
				 
			}
			
		}
		
		




	public  Producto create(Connection c, Producto producto) 
			throws DataException{
		 
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {

			c = ConnectionManager.getConnection();
			//SQL

			String sql = " INSERT INTO PRODUCTO( nombre, descripcion, precio, id_categoria, id_empresa, id_seccion, id_marca, id_pais) "
					+ " VALUES (?,?,?,?,?,?,?,?) ";

			//create prepared statement
			preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			int i  = 1;
			JDBCUtils.setParameter(preparedStatement, i++, producto.getNombre());
			JDBCUtils.setParameter(preparedStatement, i++, producto.getDescripcion());
			JDBCUtils.setParameter(preparedStatement, i++, producto.getPrecio());
			JDBCUtils.setParameter(preparedStatement, i++, producto.getIdCategoria()); 
			JDBCUtils.setParameter(preparedStatement, i++, producto.getIdEmpresa());				
			JDBCUtils.setParameter(preparedStatement, i++, producto.getIdSeccion());
			JDBCUtils.setParameter(preparedStatement, i++, producto.getIdMarca());
			JDBCUtils.setParameter(preparedStatement, i++, producto.getIdPais());

			int insertedRows = preparedStatement.executeUpdate();
			if (insertedRows==1) {
				rs = preparedStatement.getGeneratedKeys();
				 
			} else {
				// Lanzar una excepcion
				throw new DataException(producto.getNombre());
			}
		

		} catch (SQLException ex) {			
			ex.printStackTrace();
			throw new DataException(producto.getNombre(), ex);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);
			 
		}
		return producto;
	}

	
	public  void update(Connection c,Producto producto) throws InstanceNotFoundException,DataException {
		 
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int updatedRows = 0;
		try {

			c = ConnectionManager.getConnection();
			//SQL

			String sql =" UPDATE PRODUCTO "
					+ " SET  NOMBRE = ?,"
					+ "    	 DESCRIPCION = ?,"
					+ "      PRECIO = ?,"
					+ "      ID_CATEGORIA = ?,"
					+ "      ID_EMPRESA = ?,"
					+ "      ID_SECCION= ? "
					+ "      ID_MARCA = ?,"
					+ "      ID_PAIS ? "
					+ "  ID = ? ";

			//create prepared statement
			preparedStatement = c.prepareStatement(sql);

			int i  = 1;
			
			JDBCUtils.setParameter(preparedStatement, i++, producto.getNombre());
			JDBCUtils.setParameter(preparedStatement, i++, producto.getDescripcion());
			JDBCUtils.setParameter(preparedStatement, i++, producto.getPrecio());
			JDBCUtils.setParameter(preparedStatement, i++, producto.getIdCategoria()); 
			JDBCUtils.setParameter(preparedStatement, i++, producto.getIdEmpresa());				
			JDBCUtils.setParameter(preparedStatement, i++, producto.getIdSeccion());
			JDBCUtils.setParameter(preparedStatement, i++, producto.getIdMarca());
			JDBCUtils.setParameter(preparedStatement, i++, producto.getIdPais());

			 


			updatedRows = preparedStatement.executeUpdate();
			if (updatedRows!=1) {
				// Lanzar una excepcion
				throw new DataException("No se ha podido actualizar"+producto.getId());
			}
		
		} catch (SQLException ex) {			
			logger.warn(ex.getMessage(), ex);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);
			
		}
	
	}
	
	@Override
	public void deleteById (Connection c, Long id) 
		throws DataException, InstanceNotFoundException {
		
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {			
			String sql = " delete "
					+ " from producto"
					+ " where id = ?";
			
			preparedStatement = c.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			JDBCUtils.setParameter(preparedStatement, 1, id); 
			int deletedRows = preparedStatement.executeUpdate();
			if (deletedRows!=1) {
				throw new InstanceNotFoundException(""+id);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataException(""+id, sqle);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);
		}

		
	}

	
	private static Producto loadNext(Connection c, ResultSet rs) 
		throws SQLException { 
		
		int i = 1;
		
	 
		
		Producto producto = new Producto();

		
		 
		producto.setId(rs.getLong(i++));
		producto.setNombre(rs.getString(i++));
		producto.setDescripcion(rs.getString(i++));
		producto.setPrecio(rs.getDouble(i++)); 
		producto.setIdCategoria(rs.getLong(i++));
		producto.setIdEmpresa(rs.getLong(i++));
		producto.setIdMarca(rs.getLong(i++));
		producto.setIdPais(rs.getLong(i++));
		producto.setIdSeccion(rs.getLong(i++));
	
		return producto;
		 
	}

 
	
}
