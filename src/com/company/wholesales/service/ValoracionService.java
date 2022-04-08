package com.company.wholesales.service;

import com.jal.wholesales.model.Valoracion;
import com.wholesales.exception.DataException;
import com.wholesales.exception.InstanceNotFoundException;
import com.wholesales.exception.ServiceException;

public interface ValoracionService {

	Valoracion findById(long id) throws DataException, ServiceException;

	Valoracion create(Valoracion v) throws DataException, ServiceException;

	void update(Valoracion v) throws ServiceException;

	void deleteById(Long id) throws DataException, InstanceNotFoundException;

}
