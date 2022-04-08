package com.company.wholesales.service;

 

import com.jal.wholesales.model.Marca;
import com.wholesales.exception.DataException;
import com.wholesales.exception.ServiceException;

public interface MarcaService {

	Marca findById(long id) throws DataException, ServiceException;

	Marca create(Marca m) throws DataException, ServiceException;

	void update( Marca m) throws ServiceException;

}
