package com.jal.wholesales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jal.wholesales.dao.util.ConnectionManager;
import com.jal.wholesales.dao.util.JDBCUtils;
import com.jal.wholesales.model.Valoracion;
import com.wholesales.exception.DataException;
import com.wholesales.exception.InstanceNotFoundException;
 
public class ValoracionDAOImpl implements ValoracionDAO{
	private static Logger logger = LogManager.getLogger(ValoracionDAOImpl.class);
	
	 public ValoracionDAOImpl (){
	 }
	 @Override
	 
	
	 public Valoracion findById(Connection c, long id)
			 throws  InstanceNotFoundException,DataException {
		 if(logger.isDebugEnabled()) logger.debug("id = {} ", id);
		 
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Valoracion valoracion = null;
		try {

		 
			//SQL
			String sql = "SELECT id, valoracion, comentario, id_producto,id_empresa " 
					+ " FROM valoracion "
					+ " WHERE id = ?";
			 
			logger.debug(sql);
			//create prepared statement
			preparedStatement = c.prepareStatement(sql, 
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			JDBCUtils.setParameter(preparedStatement, 1, id);			
			rs = preparedStatement.executeQuery();
			
			if (rs.next()) {				
				valoracion = loadNext(c, rs);				
			} else {
				if(logger.isDebugEnabled()) logger.debug("No se encontró la valoracion{}", id);
			}
			if (rs.next()) {
				if(logger.isDebugEnabled()) logger.debug("Valoración {} duplicada" , id);
			}

			
		} catch (SQLException ex) {			
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);
			 
		}
		return valoracion;
	}

	
	public Long create(Connection c , Valoracion valoracion)
	throws DataException{
		 
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {

			 
			//SQL

			String sql = " INSERT INTO VALORACION(valoracion, comentario, id_producto,id_empresa) "
					+ " VALUES (?,?,?,?) ";
			if(logger.isDebugEnabled()) logger.debug(sql);

			//create prepared statement
			preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			int i  = 1;
		 
			JDBCUtils.setParameter(preparedStatement, i++, valoracion.getValoracion());
			JDBCUtils.setParameter(preparedStatement, i++, valoracion.getComentario());
			JDBCUtils.setParameter(preparedStatement, i++, valoracion.getIdProducto());
			JDBCUtils.setParameter(preparedStatement, i++, valoracion.getIdEmpresa());
		 
			int insertedRows = preparedStatement.executeUpdate();
			if (insertedRows==1) {
				rs = preparedStatement.getGeneratedKeys();
				if (rs.next()) {
					valoracion.setId(rs.getLong(1));
				} 
			} else {
				// Lanzar una excepcion
			}
		
		} catch (SQLException ex) {			
			logger.warn(ex.getMessage(), ex);
		 
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);
			 
		}
		return valoracion.getId();
	}

	
	public  void update(Connection c , Valoracion valoracion)	throws  InstanceNotFoundException, DataException  {
	 
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int updatedRows = 0;
		try {

		 
			//SQL

			String sql =" UPDATE VALORACION "
					+ " SET  VALORACION = ?,"
					+ "      COMENTARIO = ?,"
					+ "      ID_PRODUCTO = ?,"
					+ "      ID_EMPRESA = ?"
					+ "  ID = ? ";
			if(logger.isDebugEnabled()) logger.debug(sql);
			//create prepared statement
			preparedStatement = c.prepareStatement(sql);

			int i  = 1;
			 

			JDBCUtils.setParameter(preparedStatement, i++, valoracion.getValoracion());
			JDBCUtils.setParameter(preparedStatement, i++, valoracion.getComentario());
			JDBCUtils.setParameter(preparedStatement, i++, valoracion.getIdProducto());
			JDBCUtils.setParameter(preparedStatement, i++, valoracion.getIdEmpresa());
			 


			updatedRows = preparedStatement.executeUpdate();
			if (updatedRows!=1) {
				throw new DataException("No se ha podido actualizar"+valoracion.getId());
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
					+ " from valoracion"
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
	
	private Valoracion loadNext(Connection c, ResultSet rs) 
		throws SQLException { 
		Valoracion valoracion = new Valoracion();

		int i = 1;
		 //HAY QUE CAMBIAR COSAS
		valoracion.setId(rs.getLong(i++));
		valoracion.setValoracion(rs.getString(i++));
		valoracion.setComentario(rs.getString(i++));
		valoracion.setIdProducto(rs.getLong(i++));
		valoracion.setIdEmpresa(rs.getLong(i++));
		return valoracion;
		 
	}

 
}
