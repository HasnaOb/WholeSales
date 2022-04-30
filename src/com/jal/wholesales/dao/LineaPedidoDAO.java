package com.jal.wholesales.dao;

import java.sql.Connection;
import java.util.List;

import com.jal.wholesales.model.LineaPedido;

import com.wholesales.exception.DataException;
import com.wholesales.exception.InstanceNotFoundException;

public interface LineaPedidoDAO {

	public LineaPedido findById(Connection c, long id) throws InstanceNotFoundException, DataException;

	/*
	 * public List<Producto> findByAll (Connection c, String nombre) throws
	 * DataException;
	 * 
	 * public List<LineaPedido> findByAll (Connection c, LineaPedido lp) throws
	 * DataException;
	 * 
	 * public Boolean exists(Connection connection, String email) throws
	 * DataException;
	 * 
	 */
	public List<LineaPedido> findByPedido(Connection c, Long idPedido) throws DataException;

	public LineaPedido create(Connection c, LineaPedido lp) throws DataException;

}
