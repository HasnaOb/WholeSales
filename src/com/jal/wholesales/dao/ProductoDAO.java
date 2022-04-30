package com.jal.wholesales.dao;

import java.sql.Connection;
import java.util.List;

import com.company.wholesales.service.impl.Results;
import com.jal.wholesales.model.Producto;
import com.jal.wholesales.service.ProductoCriteria;
import com.wholesales.exception.DataException;
import com.wholesales.exception.InstanceNotFoundException;

public interface ProductoDAO {

	public Producto findById(Connection c, long id) throws InstanceNotFoundException, DataException;

	public List<Producto> findByNombre(Connection c, String nombre) throws DataException;

	/*
	 * public Boolean exists(Connection connection, String email) throws
	 * DataException;
	 */

	public Producto create(Connection c, Producto p) throws DataException;

	public void update(Connection c, Producto p) throws InstanceNotFoundException, DataException;

	void deleteById(Connection c, Long id) throws InstanceNotFoundException, DataException;

	public Results<Producto> findByCriteria(Connection c, ProductoCriteria criteria, int startIndex, int pageSize)
			throws DataException;

	 

}
