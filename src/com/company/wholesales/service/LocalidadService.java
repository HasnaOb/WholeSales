package com.company.wholesales.service;

import com.jal.wholesales.model.Localidad;
import com.wholesales.exception.DataException;
import com.wholesales.exception.ServiceException;

public interface LocalidadService {

	Localidad findById(long id) throws DataException, ServiceException;

	Localidad create(Localidad l) throws DataException, ServiceException;

	void update(Localidad l) throws ServiceException;

}
