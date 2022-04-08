package com.company.wholesales.service;

 
import com.jal.wholesales.model.PedidoDTO;
import com.wholesales.exception.DataException;
import com.wholesales.exception.ServiceException;

public interface PedidoService {
	 
 

	PedidoDTO findById(long id) throws DataException, ServiceException;


	PedidoDTO create(PedidoDTO p) throws DataException, ServiceException;


	void update(PedidoDTO p) throws ServiceException;
	
	// ..
	
}




