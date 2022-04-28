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
import com.jal.wholesales.model.LineaPedido;
import com.jal.wholesales.model.PedidoDTO;
import com.wholesales.exception.DataException;
import com.wholesales.exception.InstanceNotFoundException;
 
 
 

public class PedidoDAOImpl implements PedidoDAO {
	
	private LineaPedidoDAO lineaPedidoDAO = null;
	
	public PedidoDAOImpl(){
		lineaPedidoDAO = new LineaPedidoDAOImpl();
	}
	
	 @Override
	 public PedidoDTO findById(Connection c, long id)
			 throws  InstanceNotFoundException,DataException {
	
	 
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		PedidoDTO pedido = null;
		try {

		 
			//SQL
			String sql = "SELECT id, fecha_creacion, total, id_tipo_estado, id_empresa_comp, id_direccion" 
					+ " FROM pedido"
					+ " WHERE id = ?";
		 
			
			//create prepared statement
			preparedStatement = c.prepareStatement(sql, 
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			JDBCUtils.setParameter(preparedStatement, 1, id);			
			rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				pedido = loadNext(c, rs);
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);
			 
		}
		return pedido;
	}

 
	 
	public  List<PedidoDTO> findByAll (Connection c) throws DataException  {
	 
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		List<PedidoDTO> results = null;
		try {

		 
			//SQL
			String sql = " SELECT id, fecha_creacion, total, id_tipo_estado, id_empresa, id_direccion " 
				 
					+ " FROM pedido ";
 
			//create prepared statement
			preparedStatement = c.prepareStatement(sql, 
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = preparedStatement.executeQuery();


			results = new ArrayList <PedidoDTO>();

			PedidoDTO pedido = null;
			while (rs.next()) {
				pedido = loadNext(c, rs);
				results.add(pedido);
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

 
	public  PedidoDTO create(Connection c , PedidoDTO pedido) throws DataException {
		 
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {

		 
			//SQL

			String sql = " INSERT INTO PEDIDO( fecha_creacion, total, id_tipo_estado, id_empresa, id_direccion) "
					+ " VALUES (?,?,?,?,?,?,?,?) ";

			//create prepared statement
			preparedStatement = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			int i  = 1;
			
			JDBCUtils.setParameter(preparedStatement, i++, pedido.getFecha_creacion());
			JDBCUtils.setParameter(preparedStatement, i++, pedido.getTotal());
			JDBCUtils.setParameter(preparedStatement, i++, pedido.getIdTipo_Estado()); 
			JDBCUtils.setParameter(preparedStatement, i++, pedido.getIdEmpresa());				
			JDBCUtils.setParameter(preparedStatement, i++, pedido.getIdDireccion());
			int insertedRows = preparedStatement.executeUpdate();
			if (insertedRows==1) {
				rs = preparedStatement.getGeneratedKeys();
				if (rs.next()) {
					pedido.setId(rs.getLong(1));
				} 
			} else {
				// Lanzar una excepcion
				throw new DataException(""+pedido.getId());
			}
			List<LineaPedido> lineas = pedido.getLineas();
			for (LineaPedido lp: lineas) {
				lp.setIdPedido(pedido.getId());
				lineaPedidoDAO.create(c, lp);
			}
			
			return pedido;
		
		} catch (SQLException ex) {			
			ex.printStackTrace();
			throw new DataException(""+pedido.getId(), ex);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);
			 
		}
	 
	}

	
	public  void update(Connection c,PedidoDTO pedido) throws DataException {
	 
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int updatedRows = 0;
		try {
 
			//SQL

			String sql =" UPDATE PEDIDO "
					+ " SET  FECHA_CREACION = ?,"
				 
					+ "      TOTAL = ?,"
					+ "      ID_TIPO_ESTADO = ?,"
					+ "      ID_EMPRESA = ?,"
					+ "      ID_DIRECCION ? "
					+ "  ID = ? ";
			//create prepared statement
			preparedStatement = c.prepareStatement(sql);

			int i  = 1;
			 
			JDBCUtils.setParameter(preparedStatement, i++, pedido.getFecha_creacion());
			JDBCUtils.setParameter(preparedStatement, i++, pedido.getTotal());
			JDBCUtils.setParameter(preparedStatement, i++, pedido.getIdTipo_Estado()); 
			JDBCUtils.setParameter(preparedStatement, i++, pedido.getIdEmpresa());				
			JDBCUtils.setParameter(preparedStatement, i++, pedido.getIdDireccion());
			 

			updatedRows = preparedStatement.executeUpdate();
			if (updatedRows!=1) {
				throw new DataException("No se ha podido actualizar"+pedido.getId());
			}
		
		} catch (SQLException ex) {			
			ex.printStackTrace();
			throw new DataException("Pedido: "+pedido.getId(),ex);
		} finally {
			JDBCUtils.close(rs);
			JDBCUtils.close(preparedStatement);
		 
		}
		 
	}
	 

	
	private  PedidoDTO loadNext(Connection c, ResultSet rs) 
		throws SQLException, DataException { 
		PedidoDTO pedido = new PedidoDTO();

		int i = 1;
		 //HAY QUE CAMBIAR COSAS
		pedido.setId(rs.getLong(i++));
		pedido.setFecha_creacion(rs.getDate(i++));
		pedido.setTotal(rs.getDouble(i++));
		pedido.setIdTipo_Estado(rs.getLong(i++));
		pedido.setIdEmpresa(rs.getLong(i++));
	
		pedido.setIdDireccion(rs.getLong(i++));
		
		List<LineaPedido> lineas = lineaPedidoDAO.findByPedido(c, pedido.getId());
		pedido.setLineas(lineas);

		return pedido;
		 
	}
	
	
	
	public void deleteById (Connection c, Long id) 
			throws DataException, InstanceNotFoundException {
			
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			
			try {			
				String sql = " delete "
						+ " from pedido"
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
	
	
	
	
	
	
	
	
	
	
	protected void deleteLineasPedido(Connection c, Long IdPedido)
			throws SQLException, DataException {

			PreparedStatement preparedStatement = null;

			try {

				String queryString =	
						  "DELETE FROM PEDIDO " 
						+ "WHERE Pedido id = ? ";
				
				preparedStatement = c.prepareStatement(queryString);

				int i = 1;
				preparedStatement.setLong(i++, IdPedido);

				preparedStatement.executeUpdate();

			} catch (SQLException e) {
				throw new DataException(e);
			} finally {
				JDBCUtils.close(preparedStatement);
			}
		}
	
	protected void createLineasPedido(Connection connection, Long IdPedido,  List<LineaPedido> lineas)
			throws SQLException, DataException {

		PreparedStatement preparedStatement = null;
		try {          
			String queryString = null;
			int i;
			for (LineaPedido lp: lineas ) {
				queryString = "INSERT INTO PEDIDO(precio, unidades, total, fecha_creacion, id_pedido, id_producto)"
						+ " VALUES (?,?,?,?,?,?,?,?) ";
					
				preparedStatement = connection.prepareStatement(queryString);				

				i = 1;     
				preparedStatement.setLong(i++, IdPedido);
				preparedStatement.setLong(i++, lp.getIdProducto());
				preparedStatement.setDouble(i++, lp.getPrecio());
				preparedStatement.setLong(i++, lp.getUnidades());
				preparedStatement.setDouble(i++, lp.getTotal());
	 
				
				int insertedRows = preparedStatement.executeUpdate();

				if (insertedRows == 0) {
					throw new SQLException();
				}	

				JDBCUtils.close(preparedStatement);
			} 
		} catch (SQLException e) {
			throw new DataException(e);
		} finally {
			JDBCUtils.close(preparedStatement);
		}
	}

	
	
}