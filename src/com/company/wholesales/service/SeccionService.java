package com.company.wholesales.service;


import com.jal.wholesales.model.Seccion;
import com.wholesales.exception.DataException;
import com.wholesales.exception.ServiceException;

public interface SeccionService {
	void update( Seccion s) throws ServiceException;


	Seccion findById(long id) throws DataException, ServiceException;





	Seccion create(Seccion p) throws DataException, ServiceException;



}
