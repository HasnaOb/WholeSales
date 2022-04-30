package com.company.wholesales.service;

import java.util.List;

import com.company.wholesales.service.impl.Results;
import com.jal.wholesales.model.Producto;
import com.jal.wholesales.service.ProductoCriteria;
import com.wholesales.exception.DataException;

import com.wholesales.exception.ServiceException;

public interface ProductoService {

	void update(Producto p) throws ServiceException;

	Producto findById(long id) throws DataException, ServiceException;

	public List<Producto> findByNombre(String nombre) throws DataException, ServiceException;

	Producto create(Producto p) throws DataException, ServiceException;

	public Results<Producto> findByCriteria(ProductoCriteria criteria, int startIndex, int pageSize)
			throws DataException;

	/**
	 * 
	 * @param email
	 * @param password
	 * @return
	 * @throws InvalidUserOrPasswordException
	 * @throws ServiceException
	 */
	/*
	 * public void Delete (Connection c,Producto p) throws ServiceException;
	 * 
	 * 
	 * public void update(Connection c,Producto p) throws ServiceException;
	 */

}
