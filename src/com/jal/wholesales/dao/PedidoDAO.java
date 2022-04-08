package com.jal.wholesales.dao;
import java.sql.Connection;
 
import com.jal.wholesales.model.PedidoDTO;
import com.wholesales.exception.DataException;
import com.wholesales.exception.InstanceNotFoundException;
 

	public interface PedidoDAO {

		 
		public PedidoDTO findById (Connection c, long id)
				throws  InstanceNotFoundException, DataException;

//			public List<LineaPedidoDAO>  findById(Connection c, LineaPedido lp)
//					throws DataException;
			
		//	public List<PedidoDTO> findByAll (Connection c, PedidoDTO p)
//				throws DataException;
			
			 
			
			/*public Boolean exists(Connection connection, PedidoDTO p) 
		    		throws DataException;*/
			
			public PedidoDTO create (Connection c, PedidoDTO p)
				throws   DataException;
			
		public void update (Connection c, PedidoDTO p)
			throws  InstanceNotFoundException, DataException;

		public void deleteById(Connection connection, Long id) throws InstanceNotFoundException, DataException;
	}


 