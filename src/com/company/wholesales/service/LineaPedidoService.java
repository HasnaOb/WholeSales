package com.company.wholesales.service;

import com.jal.wholesales.model.LineaPedido;
import com.wholesales.exception.DataException;
import com.wholesales.exception.ServiceException;

public interface LineaPedidoService {

	LineaPedido findById(long id) throws DataException, ServiceException;

	LineaPedido create(LineaPedido lp) throws DataException, ServiceException;

//	void update(LineaPedido lp) throws ServiceException;

}
