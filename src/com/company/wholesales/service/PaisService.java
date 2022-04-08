package com.company.wholesales.service;


import com.jal.wholesales.model.Pais;
import com.wholesales.exception.DataException;
import com.wholesales.exception.ServiceException;

public interface PaisService {

	Pais findById(long id) throws DataException, ServiceException;

	Pais create( Pais p) throws DataException, ServiceException;

	void update(Pais p) throws ServiceException;

}
