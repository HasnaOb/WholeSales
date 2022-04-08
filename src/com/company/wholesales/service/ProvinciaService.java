package com.company.wholesales.service;

import java.util.List;

import com.jal.wholesales.model.Provincia;
import com.wholesales.exception.DataException;
import com.wholesales.exception.ServiceException;

public interface ProvinciaService {

	Provincia findById(long id) throws DataException, ServiceException;
	public List<Provincia> findByPais(long id) throws DataException, ServiceException;

	Provincia create(Provincia p) throws DataException, ServiceException;

	void update(Provincia p) throws ServiceException;

}
