package com.company.wholesales.service;

import java.sql.Connection;
import java.util.List;

import com.jal.wholesales.model.Direccion;
import com.wholesales.exception.DataException;
import com.wholesales.exception.InstanceNotFoundException;
import com.wholesales.exception.ServiceException;

public interface DireccionService {
	Direccion findById(long id) throws InstanceNotFoundException, DataException, ServiceException;

	List<Direccion> findByEmpresa(long idEmpresa) throws DataException, ServiceException;

	Direccion create(Direccion d) throws DataException, ServiceException;

	void update(Direccion d) throws ServiceException;
}
